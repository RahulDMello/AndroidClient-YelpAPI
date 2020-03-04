package com.example.androidclientyelpapi.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidclientyelpapi.databinding.FragmentRestaurantListBinding
import com.example.androidclientyelpapi.tools.RestaurantsAdapter
import com.example.androidclientyelpapi.viewmodels.RestaurantsViewModel

class RestaurantListFragment : Fragment() {

    private val viewModel: RestaurantsViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentRestaurantListBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel.apply {
            selectedRestaurantIndex.value = -1
        }

        binding.restaurantsList.layoutManager = GridLayoutManager(requireActivity(), 2)

        val adapter = RestaurantsAdapter{index: Int ->
            viewModel.selectedRestaurantIndex.postValue(index)
        }
        binding.restaurantsList.adapter = adapter

        viewModel.restaurants.observe(viewLifecycleOwner, Observer { restaurants ->
            restaurants?.let {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
        })

        viewModel.selectedRestaurantIndex.observe(viewLifecycleOwner, Observer {index ->
            index.takeIf { it != -1 }?.let {
                viewModel.fetchReview(index)
                findNavController().navigate(RestaurantListFragmentDirections.actionRestaurantListFragmentToRestaurantDetailsFragment())
            }
        })

        binding.searchBtn.setOnClickListener {
            binding.searchTxt.text.takeIf { it.isNotBlank() }?.let {
                viewModel.updateRestaurantList(it.toString())
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.restaurants.removeObservers(viewLifecycleOwner)
        viewModel.selectedRestaurantIndex.removeObservers(viewLifecycleOwner)
    }

}
