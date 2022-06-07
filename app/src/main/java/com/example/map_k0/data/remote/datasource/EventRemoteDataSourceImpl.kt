package com.example.map_k0.data.remote.datasource

import com.example.map_k0.data.datasource.EventRemoteDataSource
import com.example.map_k0.data.remote.api.MapK0APIService
import com.example.map_k0.data.remote.mapper.toBO
import com.example.map_k0.data.remote.mapper.toDTO
import com.example.map_k0.domain.entities.EventBO

class EventRemoteDataSourceImpl : EventRemoteDataSource {
    private val mapK0APIService : MapK0APIService = MapK0APIService.getAPIService()
    override suspend fun getRemoteEvents(): List<EventBO> {
            val eventCall = mapK0APIService.getAllEvents()
            return if(eventCall.isSuccessful) eventCall.body()?.map {it.toBO()} ?: emptyList() else emptyList()
        }

    override suspend fun createEvent(eventBO: EventBO) {
        mapK0APIService.createEvent(eventBO.toDTO())
    }
}
