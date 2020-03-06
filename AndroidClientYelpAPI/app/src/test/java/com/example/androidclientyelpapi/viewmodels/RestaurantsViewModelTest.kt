package com.example.androidclientyelpapi.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidclientyelpapi.service.RestaurantsRepository
import com.example.androidclientyelpapi.service.models.Restaurant
import com.example.androidclientyelpapi.service.models.Review
import getOrAwaitValue
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test


class RestaurantsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun updateFavourite_addFavourite() {
        // Given
        val viewModel = RestaurantsViewModel()
        val restaurant = Restaurant(
            "1",
            "name",
            "address"
        )

        // When
        viewModel.updateFavourite(restaurant, true)

        // Then
        assertTrue(restaurant.isFavourite)
    }

    @Test
    fun updateFavourite_removeFavourite() {
        // Given
        val viewModel = RestaurantsViewModel()
        val restaurant = Restaurant(
            "1",
            "name",
            "address",
            isFavourite = true
        )

        // When
        viewModel.updateFavourite(restaurant, false)

        // Then
        assertFalse(restaurant.isFavourite)
    }

    @Test
    fun reverseRestaurantList_checkReversed() {
        // Given
        val viewModel = RestaurantsViewModel()
        viewModel.updateRestaurantList("", "")
        val actualList = RestaurantsRepository.restaurants
        val reversedList = mutableListOf(
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
                "1",
                "A place",
                "30 abc dr. Pickering, ON",
                isFavourite = true,
                review = Review(
                    "Rahul",
                    "Fantastic place",
                    5
                )
            )
        )
        viewModel.restaurants.getOrAwaitValue()

        // When
        viewModel.reverseRestaurantList()

        // Then
        val value = viewModel.restaurants.getOrAwaitValue()
        assertEquals(reversedList, value)
    }
}