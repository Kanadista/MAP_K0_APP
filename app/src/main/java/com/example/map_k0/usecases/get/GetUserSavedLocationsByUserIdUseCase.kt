package com.example.map_k0.usecases.get

import com.example.map_k0.data.repository.EventRepository
import com.example.map_k0.data.repository.UserSavedLocationRepository
import com.example.map_k0.domain.entities.UserSavedLocationsBO
import com.example.map_k0.ui.model.LocationWithRatingsAndImage

class GetUserSavedLocationsByUserIdUseCase(private val userSavedLocation: UserSavedLocationRepository) {
    suspend operator fun invoke(idUser: String): List<UserSavedLocationsBO> = userSavedLocation.getUserSavedLocationByUserId(idUser)

}