package com.example.pataventura.data.network

import com.example.pataventura.data.model.ServicioModel
import com.example.pataventura.data.network.response.ValoracionResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiValoracion {
    @GET("/api/cliente/valoracion/{id_cuidador}")
    suspend fun getValoraciones(
        @Header("Authorization") token: String,
        @Path("id_cuidador") idCuidador: Int
    ): ValoracionResponse
}