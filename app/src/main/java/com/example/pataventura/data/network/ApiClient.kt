package com.example.pataventura.data.network

import com.example.pataventura.data.model.CuidadorModel
import com.example.pataventura.data.model.LoginModel
import com.example.pataventura.data.model.TutorModel
import com.example.pataventura.data.network.response.CuidadorResponse
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.response.LoginResponseCuidador
import com.example.pataventura.data.network.response.LoginResponseTutor
import com.example.pataventura.data.network.response.TutorResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiClient {
    @POST("/loginTutor")
    suspend fun doLoginTutor(@Body login: LoginModel): LoginResponseTutor
    @POST("/loginCuidador")
    suspend fun doLoginCuidador(@Body login: LoginModel): LoginResponseCuidador

    @POST("/registroTutor")
    suspend fun registerTutor(@Body tutorModel: TutorModel): CustomResponse

    @POST("/registroCuidador")
    suspend fun registerCuidador(@Body cuidadorModel: CuidadorModel): CustomResponse

    @PUT("/api/cliente/updateCuidador/{id_cuidador}")
    suspend fun updateCuidador(
        @Header("Authorization") token: String,
        @Path("id_cuidador") idCuidador: Int,
        @Body cuidadorModel: CuidadorModel
    ): CuidadorResponse

    @PUT("/api/cliente/updateTutor/{id_tutor}")
    suspend fun updateTutor(
        @Header("Authorization") token: String,
        @Path("id_tutor") idTutor: Int,
        @Body tutorModel: TutorModel
    ): TutorResponse

    @GET("/mostrarCuidadores/{tipo}")
    suspend fun getCuidadores(
        @Header("Authorization") token: String,
        @Path("tipo") tipo: String
    ): List<CuidadorModel>
    @GET("/api/cliente/cuidador")
    suspend fun getCuidador(
        @Header("Authorization") token: String,
    ): CuidadorResponse

    @GET("/api/cliente/tutor")
    suspend fun getTutor(
        @Header("Authorization") token: String,
    ): TutorResponse
    @DELETE("/{id_cuidador}")
    suspend fun deleteCuidador(
        @Header("Authorization") token: String,
        @Path("id_cuidador") idCuidador: Int
    ): CustomResponse
}