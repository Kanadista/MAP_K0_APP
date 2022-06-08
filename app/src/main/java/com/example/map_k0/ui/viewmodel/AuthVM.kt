package com.example.map_k0.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.map_k0.data.remote.datasource.UserRemoteDataSourceImpl
import com.example.map_k0.data.repository.UserRepository
import com.example.map_k0.domain.entities.UserBO
import com.example.map_k0.usecases.create.CreateUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthVM : ViewModel() {

    private val userRepository = UserRepository(UserRemoteDataSourceImpl())

    private val createUserUseCase = CreateUserUseCase(userRepository)

    fun createUser(userBO: UserBO){
        viewModelScope.launch(Dispatchers.IO) { //Dispatchers.IO se usa para llamada largas o API
            createUserUseCase.invoke(userBO)
        }
    }
}