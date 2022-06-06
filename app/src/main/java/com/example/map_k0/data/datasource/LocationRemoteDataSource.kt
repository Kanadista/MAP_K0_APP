package com.example.map_k0.data.datasource

import android.location.Location
import com.example.map_k0.data.remote.entity.LocationDTO
import com.example.map_k0.domain.entities.LocationBO

interface LocationRemoteDataSource {
    suspend fun getRemoteLocations(): List<LocationBO>

    suspend fun getRemoteLocationById(id: Int): LocationBO

    suspend fun getLastLocationCreatedByUser(id: String): LocationBO

    suspend fun createLocation(location: LocationBO)
}