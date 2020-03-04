package com.example.androidclientyelpapi.service

import com.example.androidclientyelpapi.service.models.Restaurant
import com.example.androidclientyelpapi.service.models.Review
import com.example.androidclientyelpapi.service.network.RestaurantRestAPIClient
import retrofit2.awaitResponse

object RestaurantsRepository {

    fun getRestaurants(authorization: String, keyword: String) = MockedBackend.getRestaurants()

    fun getFavouriteRestaurants() = MockedBackend.getFavouriteRestaurants()

    suspend fun getReview(authorization: String, restaurant: Restaurant): Review? {
        return Review.get(RestaurantRestAPIClient.RestaurantService
            .getReview(authorization,"GYmdm_Vy7sf9tji7WUcohg")
            .awaitResponse().body()?.reviews?.getOrNull(0))
    }

    fun setFavourite(restaurant: Restaurant) = Unit

}