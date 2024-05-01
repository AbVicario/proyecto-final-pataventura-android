package com.example.pataventura.data.network

import com.example.pataventura.data.model.CuidadorModel
import com.example.pataventura.data.model.LoginModel
import com.example.pataventura.data.model.TokenModel
import com.example.pataventura.data.model.TutorModel
import com.example.pataventura.data.network.response.CuidadorResponse
import com.example.pataventura.data.network.response.CustomResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiClient {
    @POST("/loginTutor")
    suspend fun doLogin(@Body login: LoginModel): Response<TokenModel>

    @POST("/registroTutor")
    suspend fun registerTutor(@Body tutorModel: TutorModel): CustomResponse

    @POST("/registroCuidador")
    suspend fun registerCuidador(@Body cuidadorModel: CuidadorModel): CustomResponse

    @PUT("/updateCuidador/{id_cuidador}")
    suspend fun updateCuidador(
        @Header("Authorization") token: String,
        @Path("id_cuidador") idCuidador: Int,
        @Body cuidadorModel: CuidadorModel
    ): CustomResponse

    @PUT("/updateTutor/{id_tutor}")
    suspend fun updateTutor(
        @Header("Authorization") token: String,
        @Path("id_tutor") idTutor: Int,
        @Body tutorModel: TutorModel
    ): CustomResponse

    @GET("/mostrarCuidadores/{tipo}")
    suspend fun getCuidadores(
        @Header("Authorization") token: String,
        @Path("tipo") tipo: String
    ): List<CuidadorModel>
}