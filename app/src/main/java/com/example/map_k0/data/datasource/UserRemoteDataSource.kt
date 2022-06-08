package com.example.map_k0.data.datasource

import com.example.map_k0.domain.entities.UserBO

interface UserRemoteDataSource {
    suspend fun getRemoteUsers(): List<UserBO>

    suspend fun createUser(userBO: UserBO)
}