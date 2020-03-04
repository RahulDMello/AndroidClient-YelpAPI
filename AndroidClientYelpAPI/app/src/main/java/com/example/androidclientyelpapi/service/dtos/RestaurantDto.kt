package com.example.androidclientyelpapi.service.dtos

import com.google.gson.annotations.SerializedName

data class BusinessDto (
    @SerializedName("Businesses")
    val restaurants: List<RestaurantDto>
)

data class RestaurantDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("rating")
    val rating: Float,

    @SerializedName("location")
    val location: LocationDto
)

data class LocationDto(
    @SerializedName("display_address")
    val displayAddress: List<String>
)