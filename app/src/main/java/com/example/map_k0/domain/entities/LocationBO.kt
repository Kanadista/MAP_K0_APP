package com.example.map_k0.domain.entities

data class LocationBO(
    val id: Int,
    val name: String,
    val description : String,
    val latitude : Double,
    val longitude : Double,
    var creatorId : Int
)