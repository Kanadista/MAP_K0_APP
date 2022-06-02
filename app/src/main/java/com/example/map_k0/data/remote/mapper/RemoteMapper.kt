package com.example.map_k0.data.remote.mapper

import com.example.map_k0.data.remote.entity.*
import com.example.map_k0.domain.entities.*
import com.example.map_k0.ui.model.LocationWithRatings


//MAPPER FOR EVENTS
fun EventDTO.toBO(): EventBO = EventBO(
    id ?: -1,
    name ?: "",
    description ?: "",
    creatorId ?: "-1"
)

fun EventBO.toDTO(): EventDTO = EventDTO(
    id,
    name,
    description,
    creatorId
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

fun LocationBO.toLocationWithRatings(userRatingLocationBO: List<UserRatingLocationBO>) : LocationWithRatings = LocationWithRatings(
    id,
    name,
    description,
    latitude,
    longitude,
    creatorId,
    userRatingLocationBO
)

//MAPPER FOR LOCATION IMAGE
fun LocationImageDTO.toBO(): LocationImageBO = LocationImageBO(
    idLocation ?: -1,
    (image ?: null)!!
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
