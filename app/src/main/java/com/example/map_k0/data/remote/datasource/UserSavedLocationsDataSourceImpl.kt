package com.example.map_k0.data.remote.datasource

import com.example.map_k0.data.datasource.UserSavedLocationsRemoteDataSource
import com.example.map_k0.data.remote.api.MapK0APIService
import com.example.map_k0.data.remote.mapper.toBO
import com.example.map_k0.domain.entities.UserSavedLocationsBO

class UserSavedLocationsDataSourceImpl : UserSavedLocationsRemoteDataSource {
    private val mapK0APIService : MapK0APIService = MapK0APIService.getAPIService()
    override suspend fun getRemoteUserSavedLocationsSource(): List<UserSavedLocationsBO> {
        val userSavedLocationCall = mapK0APIService.getAllUserSavedLocations()
        return if(userSavedLocationCall.isSuccessful) userSavedLocationCall.body()?.map {it.toBO()} ?: emptyList() else emptyList()
    }
}