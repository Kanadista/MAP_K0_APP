package com.example.map_k0.usecases.get

import com.example.map_k0.data.repository.LocationRepository
import com.example.map_k0.domain.entities.LocationBO

class GetAllLocationsUseCase(private val locationRepository: LocationRepository) {
    suspend operator fun invoke():List<LocationBO> = locationRepository.getLocations()
}