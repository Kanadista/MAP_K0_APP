package com.example.map_k0.data.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class EventDTO (
     @Json(name = "id") val id: Int?,
     @Json(name = "name") val name: String?,
     @Json(name = "description") val description: String?,
     @Json(name = "creatorId") val creatorId: Int?
)
