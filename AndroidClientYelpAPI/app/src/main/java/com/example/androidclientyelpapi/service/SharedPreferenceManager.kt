package com.example.androidclientyelpapi.service

import android.content.Context
import android.content.SharedPreferences
import com.example.androidclientyelpapi.service.models.Restaurant
import com.google.gson.Gson

object SharedPreferenceManager {
    private val fileName = SharedPreferenceManager::class.java.canonicalName

    private var sharedPref: SharedPreferences? = null

    fun init(context: Context) {
        if(sharedPref == null)
            sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    }

    fun addRestaurant(restaurant: Restaurant) {
        sharedPref?.run {
            edit()
                .putString(restaurant.id, Gson().toJson(restaurant.getSimpleRestaurant()))
                .apply()
        }
    }

    fun removeRestaurant(restaurant: Restaurant) {
        sharedPref?.run{
            edit().remove(restaurant.id).apply()
        }
    }

    fun getRestaurants(): MutableMap<String, Restaurant>? {
        sharedPref?.run {
            val restaurantMap = mutableMapOf<String, Restaurant>()
            all.values.forEach {
                val restaurant = Gson().fromJson(it.toString(), Restaurant::class.java)
                restaurant.isFavourite = true
                restaurantMap[restaurant.id] = restaurant
            }
            return restaurantMap
        }
        return null
    }

    private fun Restaurant.getSimpleRestaurant(): Restaurant =
        Restaurant(id, name, address)

}