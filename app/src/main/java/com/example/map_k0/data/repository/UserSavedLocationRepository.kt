package com.example.map_k0.data.repository

import com.example.map_k0.data.datasource.UserSavedLocationsRemoteDataSource
import com.example.map_k0.domain.entities.UserRatingLocationBO
import com.example.map_k0.domain.entities.UserSavedLocationsBO

class UserSavedLocationRepository(private val userSavedLocationRepository : UserSavedLocationsRemoteDataSource) {
    suspend fun getUserSavedLocation() : List<UserSavedLocationsBO>{
        return userSavedLocationRepository.getRemoteUserSavedLocationsSource()
    }

    suspend fun getUserSavedLocationByUserId(idUser:String) : List<UserSavedLocationsBO>{
        return userSavedLocationRepository.getRemoteUserSavedLocationsByIdSource(idUser)
    }

    suspend fun createUserSavingLocation(userSavedLocationBO: UserSavedLocationsBO){
        userSavedLocationRepository.createUserSavingLocation(userSavedLocationBO)
    }
}