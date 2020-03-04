package com.example.androidclientyelpapi.service

import com.example.androidclientyelpapi.service.models.Restaurant

object RestaurantsRepository {

    fun getRestaurants(keyword: String) = MockedBackend.getRestaurants()

    fun getFavouriteRestaurants() = MockedBackend.getFavouriteRestaurants()

    fun getReview(restaurant: Restaurant) = MockedBackend.getReview()

    fun setFavourite(restaurant: Restaurant) = Unit

}