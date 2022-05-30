package com.example.map_k0.data.remote.entity.lists


import com.example.map_k0.data.remote.entity.UserSavedLocationsDTO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserSavedLocationsListDTO (
    @Json(name = "ArrayOfclsUserSavedLocations") val userSavedLocationsList : List<UserSavedLocationsDTO>
)