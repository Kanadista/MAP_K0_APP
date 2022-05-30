package com.example.map_k0.data.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRatingLocationDTO (
    @Json(name = "idUser") val idUser: Int?,
    @Json(name = "idLocation") val idLocation: Int?,
    @Json(name = "stars") val stars: Int?,
    @Json(name = "comment") val comment: String?
)
