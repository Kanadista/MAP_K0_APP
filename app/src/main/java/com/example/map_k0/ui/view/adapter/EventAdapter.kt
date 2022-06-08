package com.example.map_k0.ui.view.adapter

import android.media.metrics.Event
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.map_k0.R
import com.example.map_k0.databinding.*
import com.example.map_k0.domain.entities.EventAssistanceBO
import com.example.map_k0.domain.entities.EventBO
import com.example.map_k0.domain.entities.LocationBO
import com.example.map_k0.domain.entities.UserRatingLocationBO
import com.google.firebase.analytics.FirebaseAnalytics
import java.time.format.DateTimeFormatter

class EventAdapter(private val onClickListener : (EventBO) -> Unit) : ListAdapter<EventBO, RecyclerView.ViewHolder>(DiffUtilEventCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType){
            0 -> EventBeneficViewHolder(layoutInflater.inflate(R.layout.event_recycler_type_benefic, parent, false), onClickListener)
            1 -> EventSportViewHolder(layoutInflater.inflate(R.layout.event_recycler_type_sport, parent, false), onClickListener)
            2 -> EventCulturalViewHolder(layoutInflater.inflate(R.layout.event_recycler_type_cultural,parent, false), onClickListener)
            3 -> EventActivismViewHolder(layoutInflater.inflate(R.layout.event_recycler_type_activism, parent, false), onClickListener)
            else -> {
                EventBeneficViewHolder(layoutInflater.inflate(R.layout.event_recycler_type_benefic, parent, false), onClickListener) // Esto aqui no pinta nada
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is EventBeneficViewHolder -> {
                holder.bind(item)
            }
            is EventSportViewHolder -> {
                holder.bind(item)
            }
            is EventCulturalViewHolder -> {
                holder.bind(item)
            }
            is EventActivismViewHolder -> {
                holder.bind(item)
            }
        }
    }
}

    private object DiffUtilEventCallBack : DiffUtil.ItemCallback<EventBO>() {

    override fun areItemsTheSame(oldItem: EventBO, newItem: EventBO): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: EventBO, newItem: EventBO): Boolean =
        oldItem == newItem
    }

    class EventBeneficViewHolder(view : View, private val onClickListener : (EventBO) -> Unit) : RecyclerView.ViewHolder(view){
    private val binding = EventRecyclerTypeBeneficBinding.bind(view)

    fun bind(eventBO: EventBO){
        binding.apply {
           eventBeneficTitle.text = eventBO.name
            eventBeneficDescription.text = eventBO.description
            eventBeneficAddress.text = eventBO.address
            eventBeneficDate.text = eventBO.date.toString().substring(0,10)
            eventBeneficConfirmBtn.setOnClickListener { onClickListener(eventBO) }
        }
    }
    }

    class EventActivismViewHolder(view : View, private val onClickListener : (EventBO) -> Unit) : RecyclerView.ViewHolder(view){
    private val binding = EventRecyclerTypeActivismBinding.bind(view)

    fun bind(eventBO: EventBO){
        binding.apply {
            eventActivismTitle.text = eventBO.name
            eventActivismDescription.text = eventBO.description
            eventActivismAddress.text = eventBO.address
            eventActivismDate.text = eventBO.date.toString().substring(0,10)
            eventActivismConfirmBtn.setOnClickListener { onClickListener(eventBO) }
        }
    }
}

    class EventSportViewHolder(view : View, private val onClickListener : (EventBO) -> Unit) : RecyclerView.ViewHolder(view){
    private val binding = EventRecyclerTypeSportBinding.bind(view)

    fun bind(eventBO: EventBO){
        binding.apply {
            eventSportTitle.text = eventBO.name
            eventSportDescription.text = eventBO.description
            eventSportAddress.text = eventBO.address
            eventSportDate.text = eventBO.date.toString().substring(0,10)
            eventSportConfirmBtn.setOnClickListener { onClickListener(eventBO) }
        }
    }
}


    class EventCulturalViewHolder(view : View, private val onClickListener : (EventBO) -> Unit) : RecyclerView.ViewHolder(view){
    private val binding = EventRecyclerTypeCulturalBinding.bind(view)

    fun bind(eventBO: EventBO){
        binding.apply {
            eventCulturalTitle.text = eventBO.name
            eventCulturalDescription.text = eventBO.description
            eventCulturalAddress.text = eventBO.address
            eventCulturalDate.text = eventBO.date.toString().substring(0,10)
            eventCulturalConfirmBtn.setOnClickListener { onClickListener(eventBO) }
        }
    }
}


