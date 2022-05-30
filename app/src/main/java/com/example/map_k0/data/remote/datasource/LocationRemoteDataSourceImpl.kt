package com.example.map_k0.data.remote.datasource

import android.location.Location
import com.example.map_k0.data.datasource.LocationRemoteDataSource
import com.example.map_k0.data.remote.api.MapK0APIService
import com.example.map_k0.data.remote.entity.LocationDTO
import com.example.map_k0.data.remote.mapper.toBO
import com.example.map_k0.domain.entities.LocationBO

class LocationRemoteDataSourceImpl : LocationRemoteDataSource {

    private val mapK0APIService : MapK0APIService = MapK0APIService.getAPIService()

    override suspend fun getRemoteLocations(): List<LocationBO> {
        val locationCall = mapK0APIService.getAllLocations()
        return if(locationCall.isSuccessful) locationCall.body()?.map {it.toBO()} ?: emptyList() else emptyList()
    }

    override suspend fun getRemoteLocationById(id: Int): LocationBO? {
        val locationCall = mapK0APIService.getLocationById(id)
        val locationBO : LocationBO? = if (locationCall.isSuccessful) {
            locationCall.body()?.toBO()
        }else{
            LocationBO(-1, "","",0.0,0.0,-1)
        }
                return locationBO;
        }
    }