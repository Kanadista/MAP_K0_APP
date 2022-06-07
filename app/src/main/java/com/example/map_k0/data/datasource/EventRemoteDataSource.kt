package com.example.map_k0.data.datasource

import com.example.map_k0.domain.entities.EventBO

interface EventRemoteDataSource {
    suspend fun getRemoteEvents(): List<EventBO>

    suspend fun createEvent(eventBO: EventBO)
}