package com.example.map_k0.data.datasource

import com.example.map_k0.domain.entities.EventAssistanceBO

interface EventAssistanceRemoteDataSource {
    suspend fun getRemoteEventsAssistance(): List<EventAssistanceBO>
}