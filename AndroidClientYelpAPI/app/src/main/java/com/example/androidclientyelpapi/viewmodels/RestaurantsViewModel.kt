package com.example.androidclientyelpapi.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidclientyelpapi.service.RestaurantsRepository
import com.example.androidclientyelpapi.service.models.Restaurant
import com.example.androidclientyelpapi.tools.SavedLocation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantsViewModel: ViewModel() {
    private val scope = CoroutineScope(Dispatchers.IO)

    private var isSortAscending = true

    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>>
        get() = _restaurants

    val selectedRestaurantIndex = MutableLiveData<Int>()

    private val _selectedRestaurant = MutableLiveData<Restaurant>()
    val selectedRestaurant: LiveData<Restaurant>
        get() = _selectedRestaurant

    fun updateRestaurantList(authorization: String, keyWord: String) {
        scope.launch {
            _restaurants.postValue(
                RestaurantsRepository.getRestaurants(
                    authorization,
                    SavedLocation.currentLatitude,
                    SavedLocation.currentLongitude,
                    keyWord
                ).let{list ->
                    if (isSortAscending) list.sortedBy { it.name } else list.sortedByDescending { it.name }
                })
        }
    }

    fun fetchReview(authorization: String, index: Int) {
        scope.launch {
            _restaurants.value?.let {list ->
                RestaurantsRepository.updateRestaurantDetails(authorization, list[index])
                list[index].review = RestaurantsRepository.getReview(authorization, list[index])
                _selectedRestaurant.postValue(list[index])
            }
        }
    }

    fun fetchFavourites() {
        _restaurants.postValue(RestaurantsRepository
            .getFavouriteRestaurants().let{list ->
                if (isSortAscending) list.sortedBy { it.name } else list.sortedByDescending { it.name }
            })
    }

    fun updateFavourite(restaurant: Restaurant?, isFavourite: Boolean) {
        restaurant?.let {
            it.isFavourite = isFavourite
            RestaurantsRepository.updateFavourite(it)
        }
    }

    fun reverseRestaurantList() {
        _restaurants.postValue(_restaurants.value?.reversed())
        isSortAscending = !isSortAscending
    }

}