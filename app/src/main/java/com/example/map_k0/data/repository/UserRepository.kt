package com.example.map_k0.data.repository

import com.example.map_k0.data.datasource.UserRemoteDataSource
import com.example.map_k0.domain.entities.UserBO


class UserRepository (private val userRemoteDataSource : UserRemoteDataSource){
    suspend fun getUsers() : List<UserBO>{
        return userRemoteDataSource.getRemoteUsers()
    }

    suspend fun createUser(userBO: UserBO){
        userRemoteDataSource.createUser(userBO)
    }
}