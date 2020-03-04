package com.example.androidclientyelpapi.service.network

import com.example.androidclientyelpapi.service.dtos.BusinessDto
import com.example.androidclientyelpapi.service.dtos.ReviewsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantService {

    /**
     *
     * Get restaurant suggestions near the given latitude and longitude filtered based on the keyword
     */
    @GET("v3/businesses/search?latitude={lat}&longitude={lon}&keyword={keyword}&limit={limit}")
    fun getRestaurants(
        @Header("Authorization") authorization: String,
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("keyword") keyword: String,
        @Query("limit") limit: Int = 10
    ): Call<BusinessDto>

    /**
     *
     * Get restaurant based on id
     * https://api.yelp.com/v3/businesses/r_BrIgzYcwo1NAuG9dLbpg
     */
    @GET("v3/businesses/{id}")
    fun getRestaurant(
        @Header("Authorization") authorization: String,
        @Path("id") restaurantId: String
    ): Call<BusinessDto>

    /**
     *
     * Get restaurant suggestions near the given latitude and longitude filtered based on the keyword
     * https://api.yelp.com/v3/businesses/GYmdm_Vy7sf9tji7WUcohg/reviews
     */
    @GET("v3/businesses/{id}/reviews")
    fun getReview(
        @Header("Authorization") authorization: String,
        @Path("id") restaurantId: String
    ): Call<ReviewsDto>
}