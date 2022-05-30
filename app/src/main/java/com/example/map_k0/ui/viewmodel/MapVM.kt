package com.example.map_k0.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.map_k0.data.datasource.LocationRemoteDataSource
import com.example.map_k0.data.remote.datasource.LocationRemoteDataSourceImpl
import com.example.map_k0.data.repository.LocationRepository
import com.example.map_k0.domain.entities.LocationBO
import com.example.map_k0.usecases.GetAllLocationsUseCase
import androidx.lifecycle.viewModelScope
import com.example.map_k0.usecases.GetLocationByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapVM: ViewModel() {

    private val locationRepository = LocationRepository(LocationRemoteDataSourceImpl())

    private val getAllLocations = GetAllLocationsUseCase(locationRepository)

    private val getLocationByIdUseCase = GetLocationByIdUseCase(locationRepository)

    val locationList = MutableLiveData<List<LocationBO>>()

    fun loadAllLocations(){
        viewModelScope.launch(Dispatchers.IO) { //Dispatchers.IO se usa para llamada largas o API
            locationList.postValue(getAllLocations.invoke())
        }
    }
}