package com.example.map_k0.data.remote.entity.lists

import com.example.map_k0.data.remote.entity.EventAssistanceDTO
import com.example.map_k0.data.remote.entity.EventDTO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventListDTO (
    @Json(name = "ArrayOfclsEvent") val eventList : List<EventDTO>
)