package com.example.androidclientyelpapi.service

import com.example.androidclientyelpapi.service.RestaurantsRepository.addFavourite
import com.example.androidclientyelpapi.service.models.Restaurant
import com.example.androidclientyelpapi.service.models.Review
import com.example.androidclientyelpapi.service.network.RestaurantRestAPIClient
import retrofit2.awaitResponse

object RestaurantsRepository {

    private var favouritesMap: MutableMap<String, Restaurant>? = null

    suspend fun getRestaurants(
        authorization: String,
        latitude: Double,
        longitude: Double,
        keyword: String): List<Restaurant> {
        val restaurants = mutableListOf<Restaurant>()
        val restaurantsDto = RestaurantRestAPIClient.RestaurantService
            .getRestaurants(authorization, latitude, longitude, keyword).awaitResponse().body()?.restaurants
        restaurantsDto?.forEach {
            restaurants.add(Restaurant.get(it))
        }
        return restaurants
    }

    fun getFavouriteRestaurants(): List<Restaurant> {
        if (favouritesMap == null)
            favouritesMap = SharedPreferenceManager.getRestaurants()
        return favouritesMap?.values?.toList() ?: listOf()
    }

    suspend fun getReview(authorization: String, restaurant: Restaurant): Review? {
        return Review.get(RestaurantRestAPIClient.RestaurantService
            .getReview(authorization, restaurant.id)
            .awaitResponse().body()?.reviews?.getOrNull(0))
    }

    fun updateFavourite(restaurant: Restaurant) {
        restaurant.run {
            if(isFavourite) addFavourite() else removeFavourite()
        }
    }

    private fun Restaurant.addFavourite() {
        SharedPreferenceManager.addRestaurant(this)
        favouritesMap?.put(id, this)
    }

    private fun Restaurant.removeFavourite() {
        SharedPreferenceManager.removeRestaurant(this)
        favouritesMap?.remove(id)
    }

}