package com.example.map_k0.usecases.create

import com.example.map_k0.data.repository.UserSavedLocationRepository
import com.example.map_k0.domain.entities.UserRatingLocationBO
import com.example.map_k0.domain.entities.UserSavedLocationsBO

class CreateUserSavingLocationUseCase(private val userSavedLocationRepository: UserSavedLocationRepository) {
    suspend operator fun invoke(userSavedLocationBO : UserSavedLocationsBO) = userSavedLocationRepository.createUserSavingLocation(userSavedLocationBO)
}