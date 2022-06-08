package com.example.map_k0.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.map_k0.R
import com.example.map_k0.databinding.ImageRecyclerBinding
import com.example.map_k0.databinding.LocationRecyclerBinding
import com.example.map_k0.databinding.RatingLocationRowBinding
import com.example.map_k0.domain.entities.LocationBO
import com.example.map_k0.domain.entities.LocationImageBO
import com.example.map_k0.domain.entities.UserRatingLocationBO

class LocationAdapter(private val locations : List<LocationBO>, private val onClickListener : (LocationBO) -> Unit) : RecyclerView.Adapter<LocationViewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LocationViewholder(layoutInflater.inflate(R.layout.location_recycler,parent, false), onClickListener)
    }

    override fun onBindViewHolder(holder: LocationViewholder, position: Int) {
        holder.render(locations[position])
    }

    override fun getItemCount(): Int { return locations.size }

}



class LocationViewholder(view : View, private val onClickListener: (LocationBO) -> Unit) : RecyclerView.ViewHolder(view) {
    private val binding = LocationRecyclerBinding.bind(view)

    fun render(location : LocationBO){
        binding.apply{
            locationRecyclerTitle.text = location.name
            locationRecyclerDescription.text = location.description
            locationSavedButton.setOnClickListener {
                onClickListener(location)
            }
        }
    }
}