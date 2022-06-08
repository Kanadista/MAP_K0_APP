package com.example.map_k0.data.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDTO (
    @Json(name = "id") val id: String?,
    @Json(name = "email") val email: String?,
    @Json(name = "firstName") val firstName: String?,
    @Json(name = "lastName") val lastName: String?,
    @Json(name = "address") val address: String?,
)