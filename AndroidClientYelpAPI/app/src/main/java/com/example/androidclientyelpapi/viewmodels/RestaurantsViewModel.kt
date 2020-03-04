package com.example.androidclientyelpapi.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidclientyelpapi.service.RestaurantsRepository
import com.example.androidclientyelpapi.service.models.Restaurant

class RestaurantsViewModel: ViewModel() {
    private val _restaurants = MutableLiveData<List<Restaurant>>()

    val selectedRestaurantIndex = MutableLiveData<Int>()

    val selectedRestaurant: Restaurant?
        get() = _restaurants.value?.getOrNull(selectedRestaurantIndex.value ?: 0)

    val restaurants: LiveData<List<Restaurant>>
        get() = _restaurants

    fun updateRestaurantList(keyWord: String) {
        _restaurants.postValue(RestaurantsRepository.getRestaurants(keyWord))
    }

    fun fetchReview(index: Int) {
        _restaurants.value?.let {list ->
            list[index].review = RestaurantsRepository.getReview(list[index])
        }
    }

}