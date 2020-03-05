package com.example.androidclientyelpapi.service

import com.example.androidclientyelpapi.service.models.Restaurant
import com.example.androidclientyelpapi.service.models.Review
import com.example.androidclientyelpapi.service.network.RestaurantRestAPIClient
import retrofit2.awaitResponse

object RestaurantsRepository {

    /*
    * fun getRestaurants(
        @Header("Authorization") authorization: String,
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("keyword") keyword: String,
        @Query("limit") limit: Int = 10
    ): Call<BusinessDto>
    * */
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

    fun getFavouriteRestaurants() = MockedBackend.getFavouriteRestaurants()

    suspend fun getReview(authorization: String, restaurant: Restaurant): Review? {
        return Review.get(RestaurantRestAPIClient.RestaurantService
            .getReview(authorization, restaurant.id)
            .awaitResponse().body()?.reviews?.getOrNull(0))
    }

    fun setFavourite(restaurant: Restaurant) = Unit

}