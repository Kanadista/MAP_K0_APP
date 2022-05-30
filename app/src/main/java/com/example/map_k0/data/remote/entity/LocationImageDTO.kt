package com.example.map_k0.data.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class LocationImageDTO (
    @Json(name = "idLocation") val idLocation: Int?,
    @Json(name="image") val image: ByteArray,
)