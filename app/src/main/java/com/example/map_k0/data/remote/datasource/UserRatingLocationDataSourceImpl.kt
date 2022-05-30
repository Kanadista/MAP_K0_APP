package com.example.map_k0.data.remote.datasource

import com.example.map_k0.data.datasource.UserRatingLocationsRemoteDataSource
import com.example.map_k0.data.remote.api.MapK0APIService
import com.example.map_k0.data.remote.mapper.toBO
import com.example.map_k0.domain.entities.UserRatingLocationBO

class UserRatingLocationDataSourceImpl : UserRatingLocationsRemoteDataSource {
    private val mapK0APIService : MapK0APIService = MapK0APIService.getAPIService()
    override suspend fun getRemoteUserRatingLocations(): List<UserRatingLocationBO> {
        val userRatingsCall = mapK0APIService.getAllUserRatingLocation()
        return if(userRatingsCall.isSuccessful) userRatingsCall.body()?.userRatingLocationList?.map {it.toBO()} ?: emptyList() else emptyList()
    }
}