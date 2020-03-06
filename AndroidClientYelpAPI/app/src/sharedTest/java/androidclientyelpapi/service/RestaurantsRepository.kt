package com.example.androidclientyelpapi.service

import com.example.androidclientyelpapi.service.models.Restaurant
import com.example.androidclientyelpapi.service.models.Review

object RestaurantsRepository {

    private var favouritesMap: MutableMap<String, Restaurant>? = null

    val restaurants = mutableListOf(
        Restaurant(
            "1",
            "A place",
            "30 abc dr. Pickering, ON",
            isFavourite = true,
            review = Review(
                "Rahul",
                "Fantastic place",
                5
            )
        ),
        Restaurant(
            "2",
            "B place",
            "30 abc dr. Pickering, ON",
            isFavourite = true,
            review = Review(
                "Rahul",
                "Fantastic place",
                5
            )
        ),
        Restaurant(
            "3",
            "C place",
            "30 abc dr. Pickering, ON",
            isFavourite = true,
            review = Review(
                "Rahul",
                "Fantastic place",
                5
            )
        )
    )

    suspend fun getRestaurants(
        authorization: String,
        latitude: Double,
        longitude: Double,
        keyword: String
    ): List<Restaurant> {
        return restaurants
    }

    fun getFavouriteRestaurants(): List<Restaurant> {
        return mutableListOf(
            Restaurant(
                "1",
                "That place",
                "30 abc dr. Pickering, ON",
                isFavourite = true,
                review = Review(
                    "Rahul",
                    "Fantastic place",
                    5
                )
            )
        )
    }

    suspend fun getReview(authorization: String, restaurant: Restaurant): Review? {
        return Review("", "", 5)
    }

    suspend fun updateRestaurantDetails(authorization: String, restaurant: Restaurant) {

    }

    fun updateFavourite(restaurant: Restaurant) {
        restaurant.run {
            if (isFavourite) addFavourite() else removeFavourite()
        }
    }

    private fun Restaurant.addFavourite() {

        favouritesMap?.put(id, this)
    }

    private fun Restaurant.removeFavourite() {

        favouritesMap?.remove(id)
    }
}