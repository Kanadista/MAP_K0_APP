package com.example.map_k0.domain.entities

data class UserRatingLocationBO(
     val idUser: String,
     val idLocation: Int,
     val stars: Int,
     val comment: String
)