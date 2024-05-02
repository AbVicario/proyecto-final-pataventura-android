package com.example.pataventura.data.network

import androidx.room.Delete
import com.example.pataventura.data.model.MascotaModel
import com.example.pataventura.data.model.TutorModel
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.response.MascotaResponse
import com.example.pataventura.domain.model.Tutor
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiMascota {

    @POST("/{id_tutor}")
    suspend fun registerMascota(
        @Header("Authorization") token: String,
        @Body mascota: MascotaModel,
        @Path("id_tutor") idTutor: Int
    ): CustomResponse
    @GET("/one/{id_mascota}")
    suspend fun getOneMascota(
        @Header("Authorization") token: String,
        @Path("id_mascota") idMascota: Int
    ): MascotaResponse

    @GET("/all")
    suspend fun getMascotas(
        @Header("Authorization") token: String
    ): List<MascotaResponse>

    @PUT("/{id_mascota}")
    suspend fun updateMascota(
        @Header("Authorization") token: String,
        @Path("id_mascota") idMascota: Int,
        @Body mascota: MascotaModel
    ): CustomResponse

    @DELETE("/{id_mascota}")
    suspend fun deleteMascota(
        @Header("Authorization") token: String,
        @Path("id_mascota") idMascota: Int
    ): CustomResponse
}