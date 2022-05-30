package com.example.map_k0.data.repository

import com.example.map_k0.data.datasource.EventRemoteDataSource
import com.example.map_k0.data.datasource.LocationRemoteDataSource
import com.example.map_k0.domain.entities.EventBO

class EventRepository(private val eventRemoteDataSource : EventRemoteDataSource) {

    suspend fun getEvents() : List<EventBO>{
        return eventRemoteDataSource.getRemoteEvents()
    }

}