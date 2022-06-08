package com.example.map_k0.usecases.create

import com.example.map_k0.data.repository.UserRepository
import com.example.map_k0.domain.entities.LocationBO
import com.example.map_k0.domain.entities.UserBO

class CreateUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: UserBO) = userRepository.createUser(user)
}