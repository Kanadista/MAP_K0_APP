package com.example.map_k0.data.remote.datasource

import com.example.map_k0.data.datasource.UserRatingLocationsRemoteDataSource
import com.example.map_k0.data.remote.api.MapK0APIService
import com.example.map_k0.data.remote.mapper.toBO
import com.example.map_k0.data.remote.mapper.toDTO
import com.example.map_k0.domain.entities.LocationBO
import com.example.map_k0.domain.entities.UserRatingLocationBO

class UserRatingLocationDataSourceImpl : UserRatingLocationsRemoteDataSource {
    private val mapK0APIService : MapK0APIService = MapK0APIService.getAPIService()
    override suspend fun getUserRatingLocationById(id: Int): List<UserRatingLocationBO> {
        val locationRatingCall = mapK0APIService.getUserRatingLocationById(id)
        return if(locationRatingCall.isSuccessful) locationRatingCall.body()?.map {it.toBO()} ?: emptyList() else emptyList()
    }

    override suspend fun createUserRatingLocation(userRatingLocationBO: UserRatingLocationBO) {
        mapK0APIService.createUserRatingLocation(userRatingLocationBO.toDTO())
    }
}
