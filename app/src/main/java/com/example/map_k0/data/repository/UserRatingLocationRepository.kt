package com.example.map_k0.data.repository

import com.example.map_k0.data.datasource.UserRatingLocationsRemoteDataSource
import com.example.map_k0.domain.entities.UserRatingLocationBO

class UserRatingLocationRepository(private val userRatingLocationRemoteDataSource : UserRatingLocationsRemoteDataSource) {
    suspend fun getUserRatingLocationById(id: Int) : List<UserRatingLocationBO>{
        return userRatingLocationRemoteDataSource.getUserRatingLocationById(id)
    }

    suspend fun createUserRatingLocation(userRatingLocationBO: UserRatingLocationBO){
        userRatingLocationRemoteDataSource.createUserRatingLocation(userRatingLocationBO)
    }
}