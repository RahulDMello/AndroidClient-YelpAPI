package com.example.androidclientyelpapi.service.dtos

import com.google.gson.annotations.SerializedName

data class ReviewsDto (
    @SerializedName("reviews")
    val reviews: List<ReviewDto>
)

data class ReviewDto (
    @SerializedName("text")
    val comment: String,

    @SerializedName("rating")
    val rating: Int,

    @SerializedName("user")
    val user: User
)

data class User (
    @SerializedName("name")
    val name: String
)