package com.example.map_k0.data.remote.mapper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.example.map_k0.data.remote.entity.*
import com.example.map_k0.domain.entities.*
import com.example.map_k0.ui.model.LocationWithRatingsAndImage
import java.io.ByteArrayOutputStream
import java.util.*


//MAPPER FOR EVENTS
fun EventDTO.toBO(): EventBO = EventBO(
    id ?: -1,
    name ?: "",
    description ?: "",
    address ?: "",
    type ?: -1,
    creatorId ?: "-1",
    date = date
)

fun EventBO.toDTO(): EventDTO = EventDTO(
    id,
    name,
    description,
    address,
    type,
    creatorId,
    date
)
//MAPPER FOR EVENT ASSISTANCE
fun EventAssistanceDTO.toBO(): EventAssistanceBO = EventAssistanceBO(
    idEvent ?: -1,
    idUser ?: "-1"
)

fun EventAssistanceBO.toDTO(): EventAssistanceDTO = EventAssistanceDTO(
    idEvent,
    idUser
)

//MAPPER FOR LOCATION
fun LocationDTO.toBO(): LocationBO = LocationBO(
    id ?: -1,
    name ?: "",
    description ?: "",
    latitude ?: 0.0,
    longitude ?: 0.0,
    creatorId ?: "-1"
)

fun LocationBO.toDTO(): LocationDTO = LocationDTO(
    id,
    name,
    description,
    latitude,
    longitude,
    creatorId
)

fun LocationBO.toLocationWithRatingsAndImage(userRatingLocationBO: List<UserRatingLocationBO>, locationImageBO: List<LocationImageBO>) : LocationWithRatingsAndImage = LocationWithRatingsAndImage(
    id,
    name,
    description,
    latitude,
    longitude,
    creatorId,
    userRatingLocationBO,
    locationImageBO
)

//MAPPER FOR LOCATION IMAGE
fun LocationImageRecievingDTO.toBO(): LocationImageBO = LocationImageBO(
    idLocationImage ?: -1,
    idLocation ?: -1,
    image = stringToBitMap(image)
    //image ?:
)

fun LocationImageBO.toDTO(): LocationImageSendingDTO = LocationImageSendingDTO(
    idLocationImage ?: -1,
    idLocation ?: -1,
    image = bitMapToString(image)
    //image ?:
)

//MAPPER FOR USERS
fun UserDTO.toBO(): UserBO = UserBO(
    id ?:  "-1",
    nickName ?: "",
    firstName ?: "",
    lastName ?: "",
    address ?: "",
    (profilePic ?: null)!!,
    level ?: -1,
    levelxp ?: -1
)

//MAPPER FOR USER RATING LOCATIONS

fun UserRatingLocationDTO.toBO(): UserRatingLocationBO = UserRatingLocationBO(
    idUser ?:  "-1",
    idLocation ?: -1,
    stars ?: -1,
    comment ?: ""
)

fun UserRatingLocationBO.toDTO(): UserRatingLocationDTO = UserRatingLocationDTO(
    idUser,
    idLocation,
    stars,
    comment
)


//MAPPER FOR SAVED LOCATIONS

fun UserSavedLocationsDTO.toBO(): UserSavedLocationsBO = UserSavedLocationsBO(
    idUser ?:  "-1",
    idLocation ?: -1
)

fun stringToBitMap(string: String): Bitmap {
    val decodedString: ByteArray = Base64.decode(string, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
}

fun bitMapToString(bitmap: Bitmap): ByteArray {
    val stream = ByteArrayOutputStream()
     bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
    return stream.toByteArray()
}
