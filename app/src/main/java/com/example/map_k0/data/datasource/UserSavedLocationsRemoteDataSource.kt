package com.example.map_k0.data.datasource

import com.example.map_k0.domain.entities.UserRatingLocationBO
import com.example.map_k0.domain.entities.UserSavedLocationsBO

interface UserSavedLocationsRemoteDataSource {
    suspend fun getRemoteUserSavedLocationsSource(): List<UserSavedLocationsBO>

    suspend fun getRemoteUserSavedLocationsByIdSource(idUser: String): List<UserSavedLocationsBO>

    suspend fun createUserSavingLocation(userSavedLocationBO: UserSavedLocationsBO)
}