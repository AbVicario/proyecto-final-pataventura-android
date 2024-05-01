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

    @POST("/{id_cuidador}")
    suspend fun guardarServicio(
        @Header("Authorization") token: String,
        @Body servicioModel: ServicioModel,
        @Path("id_cuidador") id: Int
    ): CustomResponse
    @GET("/byTipo/{tipo}")
    suspend fun getServiciosByTipo(
        @Header("Authorization") token: String,
        @Path("tipo") tipo: String
    ): List<ServicioModel>
    @GET("/all")
    suspend fun getServicios(
        @Header("Authorization") token: String
    ): List<ServicioModel>

    @PUT("/{tipo}")
    suspend fun updateServicio(
        @Header("Authorization") token: String,
        @Path("tipo") idServicio: String
    ): CustomResponse

    @DELETE("/{tipo}")
    suspend fun deleteServicio(
        @Header("Authorization") token: String,
        @Path("tipo") idServicio: String
    ): CustomResponse
}