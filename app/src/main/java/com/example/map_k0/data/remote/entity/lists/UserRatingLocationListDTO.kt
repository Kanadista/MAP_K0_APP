package com.example.map_k0.data.remote.entity.lists


import com.example.map_k0.data.remote.entity.UserRatingLocationDTO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRatingLocationListDTO (
    @Json(name = "ArrayOfclsUserRatingLocation") val userRatingLocationList : List<UserRatingLocationDTO>
)