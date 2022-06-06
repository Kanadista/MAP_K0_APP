package com.example.map_k0.usecases.get

import com.example.map_k0.data.repository.LocationRepository
import com.example.map_k0.data.repository.UserRatingLocationRepository
import com.example.map_k0.domain.entities.UserRatingLocationBO
import com.example.map_k0.ui.model.LocationWithRatingsAndImage

class GetLocationWithRatingUseCase(private val locationRepository: LocationRepository) {
    suspend operator fun invoke(id: Int): LocationWithRatingsAndImage= locationRepository.getLocationWithRatingsAndImage(id)
}