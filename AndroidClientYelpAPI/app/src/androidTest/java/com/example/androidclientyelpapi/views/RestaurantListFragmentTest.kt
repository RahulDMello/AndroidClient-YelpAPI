package com.example.androidclientyelpapi.views

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.androidclientyelpapi.R
import com.example.androidclientyelpapi.service.RestaurantsRepository
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
class RestaurantListFragmentTest {

    @Test
    fun clickSearch_displayInRecycler() {
        // GIVEN
        launchFragmentInContainer<RestaurantListFragment>(null, R.style.AppTheme)

        // WHEN -
        onView(withId(R.id.searchTxt)).perform(replaceText("Pizza"))
        onView(withId(R.id.searchBtn)).perform(click())

        // THEN -
        onView(withId(R.id.restaurantsList)).check{ view, _ ->
            assert(view is RecyclerView &&
                    view.adapter != null && view.adapter?.itemCount == RestaurantsRepository.restaurants.size)

        }
    }

    @Test
    fun clickRestaurantCard_navigateToDetailsScreen() {
        // GIVEN
        val scenario = launchFragmentInContainer<RestaurantListFragment>(null, R.style.AppTheme)

        val navController = mock(NavController::class.java)

        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.view!!, navController)
        }

        onView(withId(R.id.searchTxt)).perform(replaceText("Pizza"))
        onView(withId(R.id.searchBtn)).perform(click())

        // WHEN -
        onView(withId(R.id.restaurantsList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0, click()))

        // THEN -
        verify(navController).navigate(RestaurantListFragmentDirections.actionRestaurantListFragmentToRestaurantDetailsFragment())
    }

}