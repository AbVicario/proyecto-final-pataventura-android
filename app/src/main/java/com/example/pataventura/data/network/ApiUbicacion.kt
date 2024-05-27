package com.example.pataventura.data.network

import com.example.pataventura.data.model.UbicacionModel
import com.example.pataventura.data.network.response.CustomResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiUbicacion {
    @POST("/api/cliente/ubicacion/cuidador")
    suspend fun registerUbicacionCuidador(
        @Header("Authorization") token: String,
        @Body ubicacion: UbicacionModel,
    ): CustomResponse

    @POST("/api/cliente/ubicacion/tutor")
    suspend fun registerUbicacionTutor(
        @Header("Authorization") token: String,
        @Body ubicacion: UbicacionModel,
    ): CustomResponse
}