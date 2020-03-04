package com.example.androidclientyelpapi.service.models

import com.example.androidclientyelpapi.service.dtos.ReviewDto

data class Review (
    val userName: String,
    val comment: String,
    val rating: Int
) {
    companion object {
        fun get(dto: ReviewDto?): Review? {
            dto?.let {
                return Review(it.user.name, it.comment, it.rating)
            }
            return null
        }
    }
}