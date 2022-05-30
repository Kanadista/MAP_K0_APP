package com.example.map_k0.data.datasource

import com.example.map_k0.domain.entities.UserRatingLocationBO

interface UserRatingLocationsRemoteDataSource{
    suspend fun getRemoteUserRatingLocations(): List<UserRatingLocationBO>
}