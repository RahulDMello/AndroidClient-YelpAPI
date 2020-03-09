package com.example.androidclientyelpapi.tools

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidclientyelpapi.R
import com.example.androidclientyelpapi.databinding.RestaurantItemViewBinding
import com.example.androidclientyelpapi.service.models.Restaurant

class RestaurantsAdapter(private val onItemClick: (Int) -> Unit) :
    ListAdapter<Restaurant, RestaurantViewHolder>(RestaurantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick, position)
    }
}

class RestaurantDiffCallback : DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem == newItem
    }
}

class RestaurantViewHolder(private val binding: RestaurantItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Restaurant, onItemClick: (Int) -> Unit, position: Int) {
        binding.item = item
        binding.card.setOnClickListener {
            onItemClick(position)
        }

        if(item.isFavourite)
            binding.title.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_star_filled, 0)
        else
            binding.title.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0)
    }

    companion object {
        fun from(parent: ViewGroup): RestaurantViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RestaurantItemViewBinding.inflate(layoutInflater, parent, false)
            return RestaurantViewHolder(binding)
        }
    }
}