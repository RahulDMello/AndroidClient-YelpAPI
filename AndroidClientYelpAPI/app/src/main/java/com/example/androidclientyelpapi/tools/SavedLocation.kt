package com.example.androidclientyelpapi.tools

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient

object SavedLocation {
    var currentLatitude: Double = 43.64784
    var currentLongitude: Double = -79.38872

    private val _isUpdating = MutableLiveData(false)

    // public live data for anyone who might be interested
    // in knowing if the update is taking place or not
    val isUpdating: LiveData<Boolean>
        get() = _isUpdating

    private val MaxRetry = 3

    fun updateSavedLocation(fusedLocationClient: FusedLocationProviderClient, counter: Int = 0) {
        _isUpdating.value = true
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                if (location == null && counter < MaxRetry)
                    updateSavedLocation(fusedLocationClient, counter+1)
                else {
                    location?.let {
                        currentLatitude = it.latitude
                        currentLongitude = it.longitude
                    }
                    _isUpdating.postValue(false)
                }
            }
    }

}