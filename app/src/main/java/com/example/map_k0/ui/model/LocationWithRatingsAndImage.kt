package com.example.map_k0.ui.model

import com.example.map_k0.domain.entities.LocationImageBO
import com.example.map_k0.domain.entities.UserRatingLocationBO

data class LocationWithRatingsAndImage (
    val id: Int,
    val name: String,
    val description : String,
    val latitude : Double,
    val longitude : Double,
    var creatorId : String,
    var ratings : List<UserRatingLocationBO>,
    var images : List<LocationImageBO>
)