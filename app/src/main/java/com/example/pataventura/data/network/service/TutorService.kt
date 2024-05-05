package com.example.pataventura.data.network.service

import android.util.Log
import com.example.pataventura.data.database.entity.TutorEntity
import com.example.pataventura.data.model.LoginModel
import com.example.pataventura.data.model.TokenModel
import com.example.pataventura.data.model.TutorModel
import com.example.pataventura.data.network.ApiClient
import com.example.pataventura.data.network.response.CuidadorResponse
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TutorService @Inject constructor(
    private val tutorApi: ApiClient
) {

    suspend fun registerTutorFromApi(tutorModel: TutorModel): CustomResponse {
        return try {
            withContext(Dispatchers.IO) {
                tutorApi.registerTutor(tutorModel)
            }
        } catch (e: Exception) {
            Log.e("LOOK AT ME", "${e.message}")
            CustomResponse(data = "Error", status = 500, false)
        }
    }

    suspend fun updateTutorFromApi(token: String, tutorModel: TutorModel): CustomResponse {
        return try {
            withContext(Dispatchers.IO) {
                tutorApi.updateTutor(token, tutorModel.idUsuario, tutorModel)
            }
        } catch (e: Exception) {
            Log.e("LOOK AT ME", "${e.message}")
            CustomResponse(data = "Error", status = 500, false)
        }
    }


    suspend fun getCuidadoresFromApi(token: String, tipo: String): List<Cuidador> {
        return try {
            withContext(Dispatchers.IO) {
                var result = tutorApi.getCuidadores(token, tipo)
                result.map { it.toDomain() }
            }
        } catch (e: Exception) {
            Log.e("LOOK AT ME", "${e.message}")
            emptyList()
        }
    }
}