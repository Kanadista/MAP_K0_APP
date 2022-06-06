package com.example.map_k0.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.map_k0.R
import com.example.map_k0.databinding.RatingLocationRowBinding
import com.example.map_k0.domain.entities.UserRatingLocationBO

class RatingAdapter() : ListAdapter<UserRatingLocationBO, RatingViewHolder>(DiffUtilCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RatingViewHolder(layoutInflater.inflate(R.layout.rating_location_row, parent, false))
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

private object DiffUtilCallBack : DiffUtil.ItemCallback<UserRatingLocationBO>() {

    override fun areItemsTheSame(oldItem: UserRatingLocationBO, newItem: UserRatingLocationBO): Boolean =
        oldItem.idLocation == newItem.idLocation && oldItem.idUser == newItem.idUser

    override fun areContentsTheSame(oldItem: UserRatingLocationBO, newItem: UserRatingLocationBO): Boolean =
        oldItem == newItem
}

class RatingViewHolder(view : View) : RecyclerView.ViewHolder(view){
    private val binding = RatingLocationRowBinding.bind(view)

    fun bind(userRatingLocationBO: UserRatingLocationBO){
        binding.apply {
            ratingLocationRowText.text = userRatingLocationBO.comment
        }
    }
}