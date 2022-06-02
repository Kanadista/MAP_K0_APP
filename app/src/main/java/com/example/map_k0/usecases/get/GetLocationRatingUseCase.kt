package com.example.map_k0.usecases.get

import com.example.map_k0.data.repository.UserRatingLocationRepository
import com.example.map_k0.domain.entities.UserRatingLocationBO

class GetLocationRatingUseCase(private val userRatingLocationRepository : UserRatingLocationRepository) {
    suspend operator fun invoke(id: Int): List<UserRatingLocationBO> = userRatingLocationRepository.getUserRatingLocationById(id)
}