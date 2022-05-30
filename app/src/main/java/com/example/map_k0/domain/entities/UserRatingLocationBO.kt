package com.example.map_k0.domain.entities

import com.squareup.moshi.Json

data class UserRatingLocationBO(
     val idUser: Int,
     val idLocation: Int,
     val stars: Int,
     val comment: String
)