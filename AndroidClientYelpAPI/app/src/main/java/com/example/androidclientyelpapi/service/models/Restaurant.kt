package com.example.androidclientyelpapi.service.models

import com.example.androidclientyelpapi.service.dtos.RestaurantDto
import com.google.gson.annotations.Expose

data class Restaurant(
    val id: String,
    val name: String,
    val address: String,
    val imageUrl: String? = null,
    val rating: Float? = null,
    @Expose(serialize = false, deserialize = false)
    var isFavourite: Boolean = false,
    var review: Review? = null
) {
    companion object {
        fun get(dto: RestaurantDto): Restaurant {
            return Restaurant(
                dto.id,
                dto.name,
                dto.location.displayAddress.joinToString(separator = " "),
                dto.imageUrl,
                dto.rating
            )
        }
    }
}