package com.example.map_k0.data.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserSavedLocationsDTO(
    @Json(name = "idUser") val idUser: String?,
    @Json(name = "idLocation") val idLocation: Int?
)