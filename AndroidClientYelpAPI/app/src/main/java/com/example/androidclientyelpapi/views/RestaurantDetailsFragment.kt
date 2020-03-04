package com.example.androidclientyelpapi.views

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.androidclientyelpapi.databinding.FragmentRestaurantDetailsBinding
import com.example.androidclientyelpapi.viewmodels.RestaurantsViewModel

class RestaurantDetailsFragment : Fragment() {

    private val viewModel: RestaurantsViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentRestaurantDetailsBinding.inflate(inflater)

        binding.lifecycleOwner = this

        viewModel.selectedRestaurant.observe(viewLifecycleOwner, Observer { restaurant ->
            binding.item = restaurant
            Glide
                .with(this)
                .load(Uri.parse(restaurant.imgUrl))
                .into(binding.restaurantImage)
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.selectedRestaurant.removeObservers(viewLifecycleOwner)
    }

}
