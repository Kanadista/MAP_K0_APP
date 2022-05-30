package com.example.map_k0.data.repository

import com.example.map_k0.data.datasource.UserSavedLocationsRemoteDataSource
import com.example.map_k0.domain.entities.UserSavedLocationsBO

class UserSavedLocationRepository(private val userSavedLocationRepository : UserSavedLocationsRemoteDataSource) {
    suspend fun getUserSavedLocation() : List<UserSavedLocationsBO>{
        return userSavedLocationRepository.getRemoteUserSavedLocationsSource()
    }
}