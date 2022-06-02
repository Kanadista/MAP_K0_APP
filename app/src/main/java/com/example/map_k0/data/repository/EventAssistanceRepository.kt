package com.example.map_k0.data.repository

import com.example.map_k0.data.datasource.EventAssistanceRemoteDataSource
import com.example.map_k0.data.datasource.UserRemoteDataSource
import com.example.map_k0.data.remote.datasource.EventAssistanceDataSourceImpl
import com.example.map_k0.domain.entities.EventAssistanceBO
import com.example.map_k0.domain.entities.LocationBO

class EventAssistanceRepository(private val eventAssistanceDataSource : EventAssistanceRemoteDataSource) {
    suspend fun getEventAssistance() : List<EventAssistanceBO>{
        return eventAssistanceDataSource.getRemoteEventsAssistance()
    }

    suspend fun createEventAssistance(eventAssistanceBO: EventAssistanceBO){
        eventAssistanceDataSource.createEventAssistance(eventAssistanceBO)
    }
}