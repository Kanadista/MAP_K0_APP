package com.example.map_k0.data.repository

import com.example.map_k0.data.datasource.LocationRemoteDataSource
import com.example.map_k0.data.remote.entity.LocationDTO
import com.example.map_k0.data.remote.mapper.toLocationWithRatings
import com.example.map_k0.domain.entities.LocationBO
import com.example.map_k0.ui.model.LocationWithRatings

class LocationRepository(private val locationRemoteDataSource : LocationRemoteDataSource, private val userRatingLocationRepository: UserRatingLocationRepository) {

    suspend fun getLocations() : List<LocationBO>{
        return locationRemoteDataSource.getRemoteLocations()
    }

    suspend fun getLocationById(id : Int) : LocationBO{
        return locationRemoteDataSource.getRemoteLocationById(id)
    }

    suspend fun createLocation(location: LocationBO) {
        locationRemoteDataSource.createLocation(location)
    }

    suspend fun getLocationWithRatings(id : Int) : LocationWithRatings{
        val location = locationRemoteDataSource.getRemoteLocationById(id)
        return location.toLocationWithRatings(userRatingLocationRepository.getUserRatingLocationById(location.id))
    }
}