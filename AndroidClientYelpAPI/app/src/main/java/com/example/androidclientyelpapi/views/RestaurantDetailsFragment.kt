package com.example.androidclientyelpapi.views

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.androidclientyelpapi.databinding.FragmentRestaurantDetailsBinding
import com.example.androidclientyelpapi.viewmodels.RestaurantsViewModel
import kotlinx.android.synthetic.main.fragment_restaurant_details.*

class RestaurantDetailsFragment : Fragment() {

    private val viewModel: RestaurantsViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentRestaurantDetailsBinding.inflate(inflater)

        binding.lifecycleOwner = this

        viewModel.selectedRestaurant.observe(viewLifecycleOwner, Observer { restaurant ->
            binding.item = restaurant
            Glide
                .with(this)
                .load(Uri.parse(restaurant.imageUrl))
                .into(restaurantImage)
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.selectedRestaurant.removeObservers(viewLifecycleOwner)
        Glide.with(this).clear(restaurantImage)
    }

}
