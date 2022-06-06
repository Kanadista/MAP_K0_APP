package com.example.map_k0.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.map_k0.data.remote.datasource.LocationImageDataSourceImpl
import com.example.map_k0.data.remote.datasource.LocationRemoteDataSourceImpl
import com.example.map_k0.data.remote.datasource.UserRatingLocationDataSourceImpl
import com.example.map_k0.data.repository.LocationImageRepository
import com.example.map_k0.data.repository.LocationRepository
import com.example.map_k0.data.repository.UserRatingLocationRepository
import com.example.map_k0.domain.entities.LocationBO
import com.example.map_k0.domain.entities.LocationImageBO
import com.example.map_k0.usecases.create.CreateLocationImageUseCase
import com.example.map_k0.usecases.create.CreateLocationUseCase
import com.example.map_k0.usecases.get.GetLastLocationCreatedByUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddLocationVM : ViewModel() {

    private val userRatingLocationRepository = UserRatingLocationRepository(
        UserRatingLocationDataSourceImpl()
    )

    private val locationImageLocationRepository = LocationImageRepository(
        LocationImageDataSourceImpl()
    )

    private val locationImageRepository = LocationImageRepository(LocationImageDataSourceImpl())

    val lastLocation = MutableLiveData<LocationBO>()

    private val locationRepository = LocationRepository(LocationRemoteDataSourceImpl(), userRatingLocationRepository, locationImageLocationRepository)

    private val lastLocationByUserUseCase = GetLastLocationCreatedByUserUseCase(locationRepository)

    private val createLocationImageUseCase = CreateLocationImageUseCase(locationImageRepository)

    private val createLocationUseCase = CreateLocationUseCase(locationRepository)

    fun createLocation(locationBO: LocationBO){
        viewModelScope.launch(Dispatchers.IO) { //Dispatchers.IO se usa para llamada largas o API
           createLocationUseCase.invoke(locationBO)
            lastLocation.postValue(lastLocationByUserUseCase.invoke(locationBO.creatorId))
        }
    }

    fun createLocationImage(locationImage: LocationImageBO){
        viewModelScope.launch(Dispatchers.IO) { //Dispatchers.IO se usa para llamada largas o API
            createLocationImageUseCase.invoke(locationImage)
        }
    }
}