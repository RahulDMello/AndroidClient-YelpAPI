package com.example.androidclientyelpapi.service.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestaurantRestAPIClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.yelp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val RestaurantService by lazy {
        retrofit.create(RestaurantService::class.java)
    }
}