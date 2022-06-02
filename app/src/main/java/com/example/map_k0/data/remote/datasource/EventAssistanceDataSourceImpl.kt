package com.example.map_k0.data.remote.datasource

import com.example.map_k0.data.datasource.EventAssistanceRemoteDataSource
import com.example.map_k0.data.remote.api.MapK0APIService
import com.example.map_k0.data.remote.entity.EventAssistanceDTO
import com.example.map_k0.data.remote.mapper.toBO
import com.example.map_k0.data.remote.mapper.toDTO
import com.example.map_k0.domain.entities.EventAssistanceBO
import com.example.map_k0.domain.entities.UserBO

class EventAssistanceDataSourceImpl : EventAssistanceRemoteDataSource {
    private val mapK0APIService : MapK0APIService = MapK0APIService.getAPIService()

    override suspend fun getRemoteEventsAssistance(): List<EventAssistanceBO> {
            val eventAssistanceCall = mapK0APIService.getAllEventAssistance()
            return if(eventAssistanceCall.isSuccessful) eventAssistanceCall.body()?.map {it.toBO()} ?: emptyList() else emptyList()

    }

    override suspend fun createEventAssistance(eventAssistanceBO: EventAssistanceBO) {
        mapK0APIService.createEventAssistance(eventAssistanceBO.toDTO())
    }
}