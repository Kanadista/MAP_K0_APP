package com.example.map_k0.domain.entities

import com.squareup.moshi.Json

data class UserBO(
    val id: String,
    val nickName: String,
    val firstName: String,
    val lastName: String,
    val address: String,
    val profilePic: ByteArray,
    val level: Int,
    val levelxp: Int
)