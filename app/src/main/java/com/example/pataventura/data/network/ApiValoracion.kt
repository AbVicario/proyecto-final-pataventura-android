package com.example.pataventura.data.network

import com.example.pataventura.data.model.NotificacionModel
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.response.NotificacionResponse
import com.example.pataventura.data.network.response.ValoracionResponse
import com.example.pataventura.data.network.service.ValoracionRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiValoracion {
    @GET("/api/cliente/valoracion/{id_cuidador}")
    suspend fun getValoraciones(
        @Header("Authorization") token: String,
        @Path("id_cuidador") idCuidador: Int
    ): ValoracionResponse

    @GET("/api/cliente/notificacion/all/{rol}")
    suspend fun getNotificaciones(
        @Header("Authorization") token: String,
        @Path("rol") rol: String
    ): NotificacionResponse

    @PUT("/api/cliente/notificacion")
    suspend fun updateNotificacion(
        @Header("Authorization") token: String,
        @Body notificacion: NotificacionModel
    ): CustomResponse

    @POST("/api/cliente/valoracion")
    suspend fun registerValoracion(
        @Header("Authorization") token: String,
        @Body valoracionRequest: ValoracionRequest,
    ): CustomResponse


}