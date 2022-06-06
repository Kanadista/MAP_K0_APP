package com.example.map_k0.data.repository

import com.example.map_k0.data.datasource.LocationRemoteDataSource
import com.example.map_k0.data.remote.entity.LocationDTO
import com.example.map_k0.data.remote.mapper.toLocationWithRatingsAndImage
import com.example.map_k0.domain.entities.LocationBO
import com.example.map_k0.ui.model.LocationWithRatingsAndImage

class LocationRepository(private val locationRemoteDataSource : LocationRemoteDataSource, private val userRatingLocationRepository: UserRatingLocationRepository, private val locationImageRepository: LocationImageRepository) {

    suspend fun getLocations() : List<LocationBO>{
        return locationRemoteDataSource.getRemoteLocations()
    }

    suspend fun getLocationById(id : Int) : LocationBO{
        return locationRemoteDataSource.getRemoteLocationById(id)
    }

    suspend fun getLastLocationByUser(id : String) : LocationBO{
        return locationRemoteDataSource.getLastLocationCreatedByUser(id)
    }

    suspend fun createLocation(location: LocationBO) {
        locationRemoteDataSource.createLocation(location)
    }

    suspend fun getLocationWithRatingsAndImage(id : Int) : LocationWithRatingsAndImage{
        val location = locationRemoteDataSource.getRemoteLocationById(id)
        return location.toLocationWithRatingsAndImage(userRatingLocationRepository.getUserRatingLocationById(location.id), locationImageRepository.getLocationImages(location.id))
    }
}