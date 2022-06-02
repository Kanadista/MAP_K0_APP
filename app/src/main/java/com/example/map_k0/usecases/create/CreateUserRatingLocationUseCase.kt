package com.example.map_k0.usecases.create

import com.example.map_k0.data.repository.UserRatingLocationRepository
import com.example.map_k0.domain.entities.UserRatingLocationBO

class CreateUserRatingLocationUseCase(private val userRatingLocationRepository: UserRatingLocationRepository) {
    suspend operator fun invoke(userRatingLocationBO : UserRatingLocationBO) = userRatingLocationRepository.createUserRatingLocation(userRatingLocationBO)
}