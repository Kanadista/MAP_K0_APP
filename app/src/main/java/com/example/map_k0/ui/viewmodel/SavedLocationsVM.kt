package com.example.map_k0.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.map_k0.data.remote.datasource.LocationImageDataSourceImpl
import com.example.map_k0.data.remote.datasource.LocationRemoteDataSourceImpl
import com.example.map_k0.data.remote.datasource.UserRatingLocationDataSourceImpl
import com.example.map_k0.data.remote.datasource.UserSavedLocationsDataSourceImpl
import com.example.map_k0.data.repository.LocationImageRepository
import com.example.map_k0.data.repository.LocationRepository
import com.example.map_k0.data.repository.UserRatingLocationRepository
import com.example.map_k0.data.repository.UserSavedLocationRepository
import com.example.map_k0.domain.entities.LocationBO
import com.example.map_k0.domain.entities.UserSavedLocationsBO
import com.example.map_k0.usecases.create.CreateUserSavingLocationUseCase
import com.example.map_k0.usecases.get.GetLocationByIdUseCase
import com.example.map_k0.usecases.get.GetUserSavedLocationsByUserIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedLocationsVM : ViewModel() {


    private val userRatingLocationRepository = UserRatingLocationRepository(UserRatingLocationDataSourceImpl())
    private val locationImageLocationRepository = LocationImageRepository(LocationImageDataSourceImpl())
    private val locationRepository = LocationRepository(LocationRemoteDataSourceImpl(), userRatingLocationRepository, locationImageLocationRepository)
    private val userSavedLocationRepository = UserSavedLocationRepository(UserSavedLocationsDataSourceImpl())
    private val getLocationByIdUseCase = GetLocationByIdUseCase(locationRepository)
    private val getUserSavedLocationsByUserIdUseCase = GetUserSavedLocationsByUserIdUseCase(userSavedLocationRepository)
    public val locationList = MutableLiveData<List<LocationBO>>()
    private var savedLocationList = emptyList<UserSavedLocationsBO>()



    fun getSavedLocation(idUser: String){
        viewModelScope.launch {
            savedLocationList = getUserSavedLocationsByUserIdUseCase.invoke(idUser)
            getSavedLocationListConvertedToLocationList(savedLocationList)
        }
    }

    private fun getSavedLocationListConvertedToLocationList(userSavedLocationsBO: List<UserSavedLocationsBO>) {
        var list = mutableListOf<LocationBO>()
        viewModelScope.launch(Dispatchers.IO) {
            userSavedLocationsBO.forEach {
                list.add(getLocationByIdUseCase.invoke(it.idLocation))
            }
            locationList.postValue(list)
        }
    }

}