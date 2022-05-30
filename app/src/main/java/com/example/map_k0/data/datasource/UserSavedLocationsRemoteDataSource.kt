package com.example.map_k0.data.datasource

import com.example.map_k0.domain.entities.UserSavedLocationsBO

interface UserSavedLocationsRemoteDataSource {
    suspend fun getRemoteUserSavedLocationsSource(): List<UserSavedLocationsBO>
}