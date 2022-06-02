package com.example.map_k0.data.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class LocationDTO (
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "description") val description : String?,
    @Json(name = "latitud") val latitude : Double?,
    @Json(name = "longitude") val longitude : Double?,
    @Json(name = "creatorId") val creatorId : String?
) {

}