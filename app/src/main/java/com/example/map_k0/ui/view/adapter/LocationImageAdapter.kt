package com.example.map_k0.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.map_k0.R
import com.example.map_k0.databinding.ImageRecyclerBinding
import com.example.map_k0.domain.entities.LocationImageBO

class LocationImageAdapter(private val images : List<LocationImageBO>) : RecyclerView.Adapter<LocationImageViewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationImageViewholder {
       val layoutInflater = LayoutInflater.from(parent.context)
       return LocationImageViewholder(layoutInflater.inflate(R.layout.image_recycler,parent, false))
    }

    override fun onBindViewHolder(holder: LocationImageViewholder, position: Int) {
        holder.render(images[position])
    }

    override fun getItemCount(): Int { return images.size }

}

class LocationImageViewholder(view : View) : RecyclerView.ViewHolder(view) {
    private val binding = ImageRecyclerBinding.bind(view)

    fun render(image : LocationImageBO){
        binding.apply{
            recyclerImage.setImageBitmap(image.image)
        }
    }
}