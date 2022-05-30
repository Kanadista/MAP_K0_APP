package com.example.map_k0.data.remote.entity.lists


import com.example.map_k0.data.remote.entity.UserDTO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserListDTO (
    @Json(name = "ArrayOfclsUser") val userList : List<UserDTO>
)