package com.example.map_k0.data.remote.datasource

import com.example.map_k0.data.datasource.UserRemoteDataSource
import com.example.map_k0.data.remote.api.MapK0APIService
import com.example.map_k0.data.remote.mapper.toBO
import com.example.map_k0.data.remote.mapper.toDTO
import com.example.map_k0.domain.entities.UserBO

class UserRemoteDataSourceImpl : UserRemoteDataSource {
    private val mapK0APIService : MapK0APIService = MapK0APIService.getAPIService()

    override suspend fun getRemoteUsers(): List<UserBO> {
        val userCall = mapK0APIService.getAllUsers()
        return if(userCall.isSuccessful) userCall.body()?.map {it.toBO()} ?: emptyList() else emptyList()
    }

    override suspend fun createUser(userBO: UserBO) {
        mapK0APIService.createUser(userBO.toDTO())
    }
}