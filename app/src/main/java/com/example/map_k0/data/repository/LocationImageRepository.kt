package com.example.map_k0.data.repository

import com.example.map_k0.data.datasource.LocationImageRemoteDataSource
import com.example.map_k0.domain.entities.LocationImageBO


class LocationImageRepository(private val locationImageRemoteDataSource : LocationImageRemoteDataSource) {
    suspend fun getLocationImages(id: Int) : List<LocationImageBO>{
        return locationImageRemoteDataSource.getRemoteLocationImageDataSource(id)
    }

    suspend fun createLocation(locationImage: LocationImageBO) {
        locationImageRemoteDataSource.createLocationImage(locationImage)
    }
}