package com.example.pataventura.data.network

import com.example.pataventura.data.model.ServicioModel
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.response.DemandaAceptadaReponse
import com.example.pataventura.data.network.response.DemandaResponse
import com.example.pataventura.data.network.response.ServicioResponse
import com.example.pataventura.data.network.response.TiposOfertaResponse
import com.example.pataventura.domain.model.Demanda
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

    @PUT("/api/cliente/oferta/{id_oferta}")
    suspend fun updateServicio(
        @Header("Authorization") token: String,
        @Path("id_oferta") idServicio: Int,
        @Body servicioModel: ServicioModel
    ): CustomResponse

    @DELETE("/api/cliente/oferta/{id_oferta}")
    suspend fun deleteServicio(
        @Header("Authorization") token: String,
        @Path("id_oferta") idServicio: Int
    ): CustomResponse

    @POST("/api/cliente/demanda")
    suspend fun guardarDemanda(
        @Header("Authorization") token: String,
        @Body demandaModel: Demanda
    ): CustomResponse

    @GET("/{id_mascota}/{estado}")
    suspend fun getDemandasByEstado(
        @Header("Authorization") token: String,
        @Path("id_mascota") idMascota: Int,
        @Path("estado") estado: String
    ): List<DemandaResponse>

    @GET("/{id_Demanda}")
    suspend fun getDemanda(
        @Header("Authorization") token: String,
        @Path("id_demanda") idMascota: Int
    ): DemandaResponse


    @DELETE("/api/cliente/demanda/{id_demanda}")
    suspend fun deleteDemanda(
        @Header("Authorization") token: String,
        @Path("id_demanda") idDemanda: Int
    ): CustomResponse

    @PUT("/api/cliente/demanda")
    suspend fun updateDemanda(
        @Header("Authorization") token: String,
        @Body demanda: Demanda
    ): CustomResponse

    @GET("/api/cliente/demanda/aceptadas/{rol}")
    suspend fun getDemandasAceptadas(
        @Header("Authorization") token: String,
        @Path("rol") rol: String
    ): DemandaAceptadaReponse

    @GET("/api/cliente/demanda/realizadasMascota/{id_mascota}")
    suspend fun getDemandasRealizadas(
        @Header("Authorization") token: String,
        @Path("id_mascota") idMascota: Int
    ): DemandaAceptadaReponse

    @GET("api/cliente/tiposOferta")
    suspend fun getTiposOferta(
        @Header("Authorization") token: String
    ): TiposOfertaResponse
}