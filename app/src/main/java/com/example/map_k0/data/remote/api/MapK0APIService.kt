package com.example.map_k0.data.remote.api

import com.example.map_k0.data.remote.entity.EventAssistanceDTO
import com.example.map_k0.data.remote.entity.EventDTO
import com.example.map_k0.data.remote.entity.LocationDTO
import com.example.map_k0.data.remote.entity.UserRatingLocationDTO
import com.example.map_k0.data.remote.entity.lists.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface MapK0APIService {


    //region Locations
    @GET("Locations")
    suspend fun getAllLocations() : Response<List<LocationDTO>>

    @GET("Locations/{id}")
    suspend fun getLocationById(@Path("id") id: Int) : Response<LocationDTO>

    //endregion

    // region Events
    @GET("Events")
    suspend fun getAllEvents() : Response<EventListDTO>

    @GET("Events/{id}")
    suspend fun getEventById(): Response<EventDTO>

    //endregion

    //region Users

    @GET("Users")
    suspend fun getAllUsers() : Response<UserListDTO>

    @GET("Users/{id}")
    suspend fun getUserById() : Response<EventDTO>
    //endregion

    //region Event_Assistance

    @GET("EventAssistance")
    suspend fun getAllEventAssistance() : Response<EventAssistanceListDTO>

    @GET("EventAssistance/{id}")
    suspend fun  getEventAssistanceById(@Path("id") id: Int) : Response<EventAssistanceDTO>

    //endregion

    //region Location_Image

    @GET("LocationImage")
    suspend fun getAllLocationImage() : Response<LocationImageListDTO>

    //endregion

    //region User_Rating_Location
    @GET("UserRatingLocation")
    suspend fun getAllUserRatingLocation() : Response<UserRatingLocationListDTO>

    @GET("UserRatingLocation/{idLocation}")
    suspend fun getUserRatingLocationById() : Response<UserRatingLocationDTO>
    //endregion

    //region User_Saved_Locations
    @GET("UserSavedLocations")
    suspend fun getAllUserSavedLocations() : Response<UserSavedLocationsListDTO>

    //endregion


    companion object {
        private const val MAPK0_API_BASE_URL = "https://mapk0api.azurewebsites.net/api/"

        fun getAPIService(): MapK0APIService =
            getRetrofit().create(MapK0APIService::class.java)

        private fun getRetrofit(): Retrofit =
            Retrofit.Builder()
                .baseUrl(MAPK0_API_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }
}