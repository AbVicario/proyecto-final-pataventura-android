package com.example.pataventura.data.network.service

import android.util.Log
import com.example.pataventura.data.model.TutorModel
import com.example.pataventura.data.network.ApiClient
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.response.TutorResponse
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
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

    suspend fun updateTutorFromApi(token: String, tutorModel: TutorModel): TutorResponse {
        return try {
            withContext(Dispatchers.IO) {
                tutorApi.updateTutor(token, tutorModel.idUsuario, tutorModel)
            }
        } catch (e: Exception) {
            throw e
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

    suspend fun getTutorFromApi(token: String): TutorModel {
        return try {
            withContext(Dispatchers.IO) {
                Log.e("LOOK AT ME", "Entra")
                val response = tutorApi.getTutor(token)
                Log.e("LOOK AT ME", response.data.toString())
                TutorModel(
                    response.data.idUsuario,
                    response.data.email,
                    response.data.password,
                    response.data.telefono,
                    response.data.nombre,
                    response.data.apellido,
                    response.data.imagen,
                    response.data.alias,
                    response.data.direccion
                )

            }
        } catch (e: Exception) {
            Log.e("LOOK AT ME", "${e.message}")
            throw e
        }
    }


}