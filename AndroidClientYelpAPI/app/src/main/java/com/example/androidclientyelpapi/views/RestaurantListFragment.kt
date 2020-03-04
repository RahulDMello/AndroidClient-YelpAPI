package com.example.androidclientyelpapi.views

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidclientyelpapi.R
import com.example.androidclientyelpapi.databinding.FragmentRestaurantListBinding
import com.example.androidclientyelpapi.tools.RestaurantsAdapter
import com.example.androidclientyelpapi.viewmodels.RestaurantsViewModel


class RestaurantListFragment : Fragment() {

    private val viewModel: RestaurantsViewModel by activityViewModels()
    private lateinit var searchTextView: TextView
    private lateinit var restaurantsRecyclerView: RecyclerView

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
                viewModel.fetchReview(
                    getString(R.string.yelp_fusion_api_key),
                    index)
                findNavController().navigate(RestaurantListFragmentDirections.actionRestaurantListFragmentToRestaurantDetailsFragment())
            }
        })

        binding.searchBtn.setOnClickListener {
            binding.searchTxt.text.takeIf { it.isNotBlank() }?.let {
                viewModel.updateRestaurantList(getString(R.string.yelp_fusion_api_key), it.toString())
            }
        }

        searchTextView = binding.searchTxt

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.restaurant_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.favourites -> {
                searchTextView.text = ""
                viewModel.fetchFavourites()
                true
            }
            R.id.sort -> {
                viewModel.reverseRestaurantList()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.restaurants.removeObservers(viewLifecycleOwner)
        viewModel.selectedRestaurantIndex.removeObservers(viewLifecycleOwner)
    }

}
