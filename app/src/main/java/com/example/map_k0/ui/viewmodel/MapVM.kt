package com.example.map_k0.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.map_k0.data.remote.datasource.LocationRemoteDataSourceImpl
import com.example.map_k0.data.repository.LocationRepository
import com.example.map_k0.domain.entities.LocationBO
import com.example.map_k0.usecases.get.GetAllLocationsUseCase
import androidx.lifecycle.viewModelScope
import com.example.map_k0.data.remote.datasource.UserRatingLocationDataSourceImpl
import com.example.map_k0.data.repository.UserRatingLocationRepository
import com.example.map_k0.usecases.get.GetLocationByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapVM: ViewModel() {

    private val userRatingLocationRepository = UserRatingLocationRepository(UserRatingLocationDataSourceImpl())

    private val locationRepository = LocationRepository(LocationRemoteDataSourceImpl(), userRatingLocationRepository)

    private val getAllLocations = GetAllLocationsUseCase(locationRepository)

    private val getLocationByIdUseCase = GetLocationByIdUseCase(locationRepository)

    val locationList = MutableLiveData<List<LocationBO>>()

    fun loadAllLocations(){
        viewModelScope.launch(Dispatchers.IO) { //Dispatchers.IO se usa para llamada largas o API
            locationList.postValue(getAllLocations.invoke())
        }
    }
}