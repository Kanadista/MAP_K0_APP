package com.example.map_k0.domain.entities

import java.time.LocalDate
import java.util.*


data class EventBO (
    val id: Int,
    val name: String,
    val description: String,
    val address: String,
    val type: Int,
    val creatorId: String,
    val date : Date
    )