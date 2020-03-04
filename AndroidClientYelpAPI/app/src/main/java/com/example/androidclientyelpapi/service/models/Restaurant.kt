package com.example.androidclientyelpapi.service.models

data class Restaurant (
    val _id: Int,
    val name: String,
    val imgUrl: String,
    val address: String,
    val rating: Float,
    var review: Review? = null,
    val favourite: Boolean = false
)