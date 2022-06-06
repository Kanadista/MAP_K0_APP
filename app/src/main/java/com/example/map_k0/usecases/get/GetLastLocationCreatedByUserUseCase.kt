package com.example.map_k0.usecases.get

import com.example.map_k0.data.repository.LocationRepository
import com.example.map_k0.domain.entities.LocationBO

class GetLastLocationCreatedByUserUseCase(private val locationRepository: LocationRepository) {
    suspend operator fun invoke(id: String): LocationBO = locationRepository.getLastLocationByUser(id)
}