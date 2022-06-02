package com.example.map_k0.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.map_k0.data.remote.datasource.LocationRemoteDataSourceImpl
import com.example.map_k0.data.remote.datasource.UserRatingLocationDataSourceImpl
import com.example.map_k0.data.repository.LocationRepository
import com.example.map_k0.data.repository.UserRatingLocationRepository
import com.example.map_k0.domain.entities.LocationBO
import com.example.map_k0.domain.entities.UserRatingLocationBO
import com.example.map_k0.ui.model.LocationWithRatings
import com.example.map_k0.usecases.create.CreateUserRatingLocationUseCase
import com.example.map_k0.usecases.get.GetLocationByIdUseCase
import com.example.map_k0.usecases.get.GetLocationWithRatingUseCase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailDialogFragmentVM : ViewModel() {

    private val userRatingLocationRepository = UserRatingLocationRepository(
        UserRatingLocationDataSourceImpl()
    )

    val locationWithRating = MutableLiveData<LocationWithRatings>()


    private val locationRepository = LocationRepository(LocationRemoteDataSourceImpl(), userRatingLocationRepository)

    private val getLocationWithRatingUseCase = GetLocationWithRatingUseCase(locationRepository)

    private val createUserRatingLocationUseCase = CreateUserRatingLocationUseCase(userRatingLocationRepository)

    fun getLocationWithRating(id: Int){
        viewModelScope.launch(Dispatchers.IO) { //Dispatchers.IO se usa para llamada largas o API
            locationWithRating.postValue(getLocationWithRatingUseCase.invoke(id))
        }
    }

    fun createUserRatingLocation(userRatingLocationBO: UserRatingLocationBO){
        viewModelScope.launch(Dispatchers.IO) { //Dispatchers.IO se usa para llamada largas o API
            createUserRatingLocationUseCase.invoke(userRatingLocationBO)
        }
    }

}