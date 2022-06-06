package com.example.map_k0.domain.entities

import android.graphics.Bitmap

data class LocationImageBO(
    val idLocationImage: Int = 0,
    val idLocation: Int,
    val image: Bitmap,
)