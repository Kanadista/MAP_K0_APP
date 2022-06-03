package com.example.map_k0.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.map_k0.data.remote.datasource.LocationRemoteDataSourceImpl
import com.example.map_k0.data.remote.datasource.UserRatingLocationDataSourceImpl
import com.example.map_k0.data.repository.LocationRepository
import com.example.map_k0.data.repository.UserRatingLocationRepository
import com.example.map_k0.domain.entities.LocationBO
import com.example.map_k0.domain.entities.UserRatingLocationBO
import com.example.map_k0.usecases.create.CreateLocationUseCase
import com.example.map_k0.usecases.get.GetLocationWithRatingUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddLocationVM : ViewModel() {

    private val userRatingLocationRepository = UserRatingLocationRepository(
        UserRatingLocationDataSourceImpl()
    )

    private val locationRepository = LocationRepository(LocationRemoteDataSourceImpl(), userRatingLocationRepository)

    private val createLocationUseCase = CreateLocationUseCase(locationRepository)

    fun createLocation(locationBO: LocationBO){
        viewModelScope.launch(Dispatchers.IO) { //Dispatchers.IO se usa para llamada largas o API
           createLocationUseCase(locationBO)
        }
    }
}