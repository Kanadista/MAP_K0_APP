package com.example.map_k0.usecases.create

import com.example.map_k0.data.repository.LocationRepository
import com.example.map_k0.domain.entities.LocationBO

class CreateLocationUseCase(private val locationRepository: LocationRepository) {
    suspend operator fun invoke(location: LocationBO) = locationRepository.createLocation(location)
}