package com.example.map_k0.data.repository

import com.example.map_k0.data.datasource.EventAssistanceRemoteDataSource
import com.example.map_k0.data.datasource.UserRemoteDataSource
import com.example.map_k0.domain.entities.EventAssistanceBO

class EventAssistanceRepository(private val eventAssistanceDataSource : EventAssistanceRemoteDataSource) {
    suspend fun getEventAssistance() : List<EventAssistanceBO>{
        return eventAssistanceDataSource.getRemoteEventsAssistance()
    }
}