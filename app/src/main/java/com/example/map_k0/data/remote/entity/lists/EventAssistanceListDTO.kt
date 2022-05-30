package com.example.map_k0.data.remote.entity.lists

import com.example.map_k0.data.remote.entity.EventAssistanceDTO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventAssistanceListDTO (
    @Json(name = "ArrayOfclsEventAssistance") val eventAssistanceList : List<EventAssistanceDTO>
)