package com.example.androidclientyelpapi.views

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.androidclientyelpapi.R
import com.example.androidclientyelpapi.service.RestaurantsRepository
import org.junit.Test

@MediumTest
class RestaurantDetailsFragmentTest {

    @Test
    fun RestaurantList_Search_ResultsDisplayedInRecycler() {
        // GIVEN

        // WHEN -
        launchFragmentInContainer<RestaurantListFragment>(null, R.style.AppTheme)

        onView(withId(R.id.searchTxt)).perform(replaceText("Pizza"))
        onView(withId(R.id.searchBtn)).perform(click())

        // THEN -
        onView(withId(R.id.restaurantsList)).check{ view, _ ->
            assert(view is RecyclerView &&
                    view.adapter != null && view.adapter?.itemCount == RestaurantsRepository.restaurants.size)

        }
    }

}