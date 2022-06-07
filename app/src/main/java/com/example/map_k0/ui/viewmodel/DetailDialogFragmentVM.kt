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
import com.example.map_k0.domain.entities.UserRatingLocationBO
import com.example.map_k0.domain.entities.UserSavedLocationsBO
import com.example.map_k0.ui.model.LocationWithRatingsAndImage
import com.example.map_k0.usecases.create.CreateUserRatingLocationUseCase
import com.example.map_k0.usecases.create.CreateUserSavingLocationUseCase
import com.example.map_k0.usecases.get.GetLocationByIdUseCase
import com.example.map_k0.usecases.get.GetLocationWithRatingUseCase
import com.example.map_k0.usecases.get.GetUserSavedLocationsByUserIdUseCase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailDialogFragmentVM : ViewModel() {

    private val userRatingLocationRepository = UserRatingLocationRepository(
        UserRatingLocationDataSourceImpl()
    )

    private val locationImageLocationRepository = LocationImageRepository(
        LocationImageDataSourceImpl()
    )
    val locationWithRating = MutableLiveData<LocationWithRatingsAndImage>()

    val userSavedLocations = MutableLiveData<List<UserSavedLocationsBO>>()

    val ratings = MutableLiveData<List<UserRatingLocationBO>>()

    private val locationRepository = LocationRepository(LocationRemoteDataSourceImpl(), userRatingLocationRepository, locationImageLocationRepository)

    private val getLocationWithRatingUseCase = GetLocationWithRatingUseCase(locationRepository)

    private val createUserRatingLocationUseCase = CreateUserRatingLocationUseCase(userRatingLocationRepository)

    private val userSavedRepository = UserSavedLocationRepository(UserSavedLocationsDataSourceImpl())

    private val createUserSavingLocationUseCase = CreateUserSavingLocationUseCase(userSavedRepository)

    private val getUserSavedLocationsByUserIdUseCase = GetUserSavedLocationsByUserIdUseCase(userSavedRepository)


    fun getLocationWithRating(id: Int){
        viewModelScope.launch(Dispatchers.IO) { //Dispatchers.IO se usa para llamada largas o API
            locationWithRating.postValue(getLocationWithRatingUseCase.invoke(id))
        }
    }

    fun getRatings(id: Int) {
        viewModelScope.launch(Dispatchers.IO) { //Dispatchers.IO se usa para llamada largas o API
            ratings.postValue(getLocationWithRatingUseCase.invoke(id).ratings)
        }

    }

    fun createUserRatingLocation(userRatingLocationBO: UserRatingLocationBO) {
            viewModelScope.launch(Dispatchers.IO) { //Dispatchers.IO se usa para llamada largas o API
                createUserRatingLocationUseCase.invoke(userRatingLocationBO)
            }
        }


    fun createUserSavingLocation(userSavedLocationsBO: UserSavedLocationsBO){
        viewModelScope.launch(Dispatchers.IO) { //Dispatchers.IO se usa para llamada largas o API
            createUserSavingLocationUseCase.invoke(userSavedLocationsBO)
        }
    }

    fun getUserSavedLocationsByUserId(idUser:String){
        viewModelScope.launch(Dispatchers.IO) { //Dispatchers.IO se usa para llamada largas o API
            userSavedLocations.postValue(getUserSavedLocationsByUserIdUseCase.invoke(idUser))
        }
    }

}


