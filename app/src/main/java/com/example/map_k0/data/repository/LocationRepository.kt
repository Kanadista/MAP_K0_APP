package com.example.map_k0.data.repository

import com.example.map_k0.data.datasource.LocationRemoteDataSource
import com.example.map_k0.domain.entities.LocationBO

class LocationRepository(private val locationRemoteDataSource : LocationRemoteDataSource) {

    suspend fun getLocations() : List<LocationBO>{
        return locationRemoteDataSource.getRemoteLocations()
    }

    suspend fun getLocationById(id : Int) : LocationBO?{
        return locationRemoteDataSource.getRemoteLocationById(id)
    }
}