package com.example.map_k0.data.datasource

import com.example.map_k0.domain.entities.LocationImageBO

interface LocationImageRemoteDataSource {
    suspend fun getRemoteLocationImageDataSource(): List<LocationImageBO>
}