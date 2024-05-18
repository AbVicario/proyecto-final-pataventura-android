package com.example.pataventura.data.network

import com.example.pataventura.data.model.ServicioModel
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.response.ServicioResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiServicio {

    @POST("/api/cliente/oferta")
    suspend fun guardarServicio(
        @Header("Authorization") token: String,
        @Body servicioModel: ServicioModel
    ): CustomResponse
    @GET("/byTipo/{tipo}")
    suspend fun getServiciosByTipo(
        @Header("Authorization") token: String,
        @Path("tipo") tipo: String
    ): List<ServicioModel>
    @GET("api/cliente/oferta/all")
    suspend fun getServicios(
        @Header("Authorization") token: String
    ): ServicioResponse

    @PUT("updateServicio/{id_servicio}")
    suspend fun updateServicio(
        @Header("Authorization") token: String,
        @Path("id") idServicio: Int,
        @Body servicioModel: ServicioModel
    ): ServicioResponse

    @DELETE("/{id}")
    suspend fun deleteServicio(
        @Header("Authorization") token: String,
        @Path("id") idServicio: Int
    ): CustomResponse
}