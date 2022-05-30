package com.example.map_k0.data.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventAssistanceDTO (
    @Json(name = "idEvent") val idEvent: Int?,
    @Json(name = "idUser") val idUser: Int?
)