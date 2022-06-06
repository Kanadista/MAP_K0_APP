package com.example.map_k0.usecases.create

import com.example.map_k0.data.repository.LocationImageRepository
import com.example.map_k0.domain.entities.LocationImageBO

class CreateLocationImageUseCase(private val locationImageRepository: LocationImageRepository) {
    suspend operator fun invoke(locationImage: LocationImageBO) = locationImageRepository.createLocation(locationImage)
}