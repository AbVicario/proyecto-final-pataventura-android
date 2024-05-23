package com.example.pataventura.data.network.service

import android.util.Log
import com.example.pataventura.data.model.UbicacionModel
import com.example.pataventura.data.network.ApiUbicacion
import com.example.pataventura.data.network.response.CustomResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UbicacionService @Inject constructor(
    private val ubicacionApi: ApiUbicacion
) {

    suspend fun registerUbicacionCuidadorFromApi(token: String, ubicacionModel: UbicacionModel): CustomResponse {
        return try {
            withContext(Dispatchers.IO) {
                ubicacionApi.registerUbicacionCuidador(token, ubicacionModel)
            }
        } catch (e: Exception) {
            Log.e("Prueba", "${e.message}")
            CustomResponse(data = "Error", status = 500, false)
        }
    }

    suspend fun registerUbicacionTutorFromApi(token: String, ubicacionModel: UbicacionModel): CustomResponse {
        return try {
            withContext(Dispatchers.IO) {
                ubicacionApi.registerUbicacionTutor(token, ubicacionModel)
            }
        } catch (e: Exception) {
            Log.e("Prueba", "${e.message}")
            CustomResponse(data = "Error", status = 500, false)
        }
    }
}