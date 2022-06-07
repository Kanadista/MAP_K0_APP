package com.example.map_k0.data.remote.mapper

import com.squareup.moshi.*
import java.text.SimpleDateFormat
import java.util.*

class CustomDateAdapter {

    private val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    @ToJson
    fun toJson(value: Date): String {
        return df.format(value)
    }

    @FromJson
    fun fromJson(source: String): Date {
        return df.parse(source)
    }

}