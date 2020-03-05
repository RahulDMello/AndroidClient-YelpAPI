package com.example.androidclientyelpapi.service.models

import com.example.androidclientyelpapi.service.dtos.RestaurantDto

data class Restaurant (
    val id: String,
    val name: String,
    val imageUrl: String,
    val address: String,
    val rating: Float,
    var review: Review? = null,
    val favourite: Boolean = false
) {
    companion object {
        fun get(dto: RestaurantDto): Restaurant {
            return Restaurant(
                dto.id,
                dto.name,
                dto.imageUrl,
                dto.location.displayAddress.joinToString(separator = " "),
                dto.rating
            )
        }
    }
}