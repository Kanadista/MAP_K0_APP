package com.example.map_k0.data.remote.api

import com.example.map_k0.data.remote.entity.*
import com.example.map_k0.data.remote.entity.lists.*
import com.example.map_k0.data.remote.mapper.CustomDateAdapter
import com.squareup.moshi.Moshi
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import okhttp3.OkHttpClient
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*


interface MapK0APIService {


    //region Locations
    @GET("Locations")
    suspend fun getAllLocations() : Response<List<LocationDTO>>

    @GET("Locations/{id}")
    suspend fun getLocationById(@Path("id") id: Int) : Response<LocationDTO>

    @GET("LocationUser/{id}")
    suspend fun getLastLocationCreatedByUser(@Path("id") id: String) : Response<LocationDTO>

    @POST("Locations")
    suspend fun createLocation(@Body location: LocationDTO)

    //endregion

    // region Events
    @GET("Events")
    suspend fun getAllEvents() : Response<List<EventDTO>>

    @GET("Events/{id}")
    suspend fun getEventById(): Response<EventDTO>

    //endregion

    //region Users

    @GET("Users")
    suspend fun getAllUsers() : Response<List<UserDTO>>

    @GET("Users/{id}")
    suspend fun getUserById() : Response<EventDTO>
    //endregion

    //region Event_Assistance

    @GET("EventAssistance")
    suspend fun getAllEventAssistance() : Response<List<EventAssistanceDTO>>

    @GET("EventAssistance/{id}")
    suspend fun  getEventAssistanceById(@Path("id") id: Int) : Response<EventAssistanceDTO>

    @POST("EventAssistance")
    suspend fun createEventAssistance(@Body eventAssistanceDTO: EventAssistanceDTO)

    //endregion

    //region Location_Image

    @GET("LocationImages/{id}")
    suspend fun getAllLocationImage(@Path("id") id: Int) : Response<List<LocationImageRecievingDTO>>

    @POST("LocationImages")
    suspend fun createLocationImage(@Body locationImageDTO: LocationImageSendingDTO) : Response<Int>

    //endregion

    //region User_Rating_Location
    @GET("UserRatingLocation/{idLocation}")
    suspend fun getUserRatingLocationById(@Path("idLocation") idLocation: Int) : Response<List<UserRatingLocationDTO>>

    @POST("UserRatingLocation")
    suspend fun createUserRatingLocation(@Body userRatingLocationDTO: UserRatingLocationDTO) : Response<Int>
    //endregion

    //region User_Saved_Locations
    @GET("UserSavedLocations")
    suspend fun getAllUserSavedLocations() : Response<List<UserSavedLocationsDTO>>

    //endregion


    companion object {

        private fun getUnsafeOkHttpClient(): OkHttpClient {
            return try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts = arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) {
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate?>? {
                            return arrayOf()
                        }
                    }
                )

                // Install the all-trusting trust manager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())
                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext.socketFactory
                val trustManagerFactory: TrustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
                trustManagerFactory.init(null as KeyStore?)
                val trustManagers: Array<TrustManager> =
                    trustManagerFactory.trustManagers
                check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
                    "Unexpected default trust managers:" + trustManagers.contentToString()
                }

                val trustManager =
                    trustManagers[0] as X509TrustManager


                val builder = OkHttpClient.Builder()
                builder.sslSocketFactory(sslSocketFactory, trustManager)
                builder.hostnameVerifier(HostnameVerifier { _, _ -> true })
                builder.build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }


        private const val MAPK0_API_BASE_URL = "https://mapk0api.azurewebsites.net/api/"

        fun getAPIService(): MapK0APIService =
            getRetrofit().create(MapK0APIService::class.java)

        private val moshiBuilder = Moshi.Builder().add(CustomDateAdapter())

        private fun getRetrofit(): Retrofit =
            Retrofit.Builder()
                .baseUrl(MAPK0_API_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshiBuilder.build()))
                //.client(getUnsafeOkHttpClient())
                .build()
    }


}