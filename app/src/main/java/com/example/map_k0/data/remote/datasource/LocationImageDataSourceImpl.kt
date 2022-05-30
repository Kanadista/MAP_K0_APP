package com.example.map_k0.data.remote.datasource

import com.example.map_k0.data.datasource.LocationImageRemoteDataSource
import com.example.map_k0.data.remote.api.MapK0APIService
import com.example.map_k0.data.remote.mapper.toBO
import com.example.map_k0.domain.entities.LocationImageBO

class LocationImageDataSourceImpl : LocationImageRemoteDataSource {
    private val mapK0APIService : MapK0APIService = MapK0APIService.getAPIService()
    override suspend fun getRemoteLocationImageDataSource(): List<LocationImageBO> {
        val locationImageCall = mapK0APIService.getAllLocationImage()
        return if(locationImageCall.isSuccessful) locationImageCall.body()?.locationImageList?.map {it.toBO()} ?: emptyList() else emptyList()
    }
}