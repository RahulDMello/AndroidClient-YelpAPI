package com.example.androidclientyelpapi.service

import com.example.androidclientyelpapi.service.models.Restaurant
import com.example.androidclientyelpapi.service.models.Review

object MockedBackend {
    fun getRestaurants(): List<Restaurant> = listOf(
        Restaurant(1, "That Restaurant", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f),
        Restaurant(2, "This Restaurant", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f),
        Restaurant(3, "Birthday Bash", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f),
        Restaurant(4, "Binging with Babish", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f),
        Restaurant(5, "Thai Restaurant", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f),
        Restaurant(6, "That pizza place", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f),
        Restaurant(7, "Drink and Bar", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f),
        Restaurant(8, "Date night Restaurant", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f),
        Restaurant(9, "Family much", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f),
        Restaurant(10, "Such wow", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f)
    )

    fun getReview(): Review = Review("anon", "Fantastic", 4)

    fun getFavouriteRestaurants(): List<Restaurant> = listOf(
        Restaurant(1, "Favourite Restaurant", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f),
        Restaurant(2, "This Restaurant", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f),
        Restaurant(3, "Birthday Bash", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f),
        Restaurant(4, "Binging with Babish", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f),
        Restaurant(5, "Thai Restaurant", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f),
        Restaurant(6, "That pizza place", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f),
        Restaurant(7, "Drink and Bar", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f),
        Restaurant(8, "Date night Restaurant", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f),
        Restaurant(9, "Family much", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f),
        Restaurant(10, "Such wow", "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg", "ADDRESS", 4f)
    )
}