package com.example.pataventura.data.network.service

import android.util.Log
import com.example.pataventura.data.model.MascotaModel
import com.example.pataventura.data.network.ApiClient
import com.example.pataventura.data.network.ApiMascota
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.response.MascotaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MascotaService @Inject constructor(
    private val mascotaApi: ApiMascota
) {
    suspend fun registerMascotaFromApi(
        token: String,
        mascotaModel: MascotaModel,
        idTutor: Int
    ): CustomResponse {
        return try {
            withContext(Dispatchers.IO) {
                mascotaApi.registerMascota(token, mascotaModel, idTutor)
            }
        } catch (e: Exception) {
            Log.e("LOOK AT ME", "${e.message}")
            CustomResponse(data = "Error", status = 500, false)
        }
    }

    suspend fun updateMascotaFromApi(token: String, mascotaModel: MascotaModel): CustomResponse {
        return try {
            withContext(Dispatchers.IO) {
                mascotaApi.updateMascota(token, mascotaModel.idMascota, mascotaModel)
            }
        } catch (e: Exception) {
            Log.e("LOOK AT ME", "${e.message}")
            CustomResponse(data = "Error", status = 500, false)
        }
    }

    suspend fun getOneMascota(token: String, id: Int): MascotaResponse {
        return try {
            withContext(Dispatchers.IO) {
                mascotaApi.getOneMascota(token, id)
            }
        } catch (e: Exception) {
            Log.e("LOOK AT ME", "${e.message}")
            throw e
        }
    }

    suspend fun getMascotas(token: String): List<MascotaResponse> {
        return try {
            withContext(Dispatchers.IO) {
                mascotaApi.getMascotas(token)
            }
        } catch (e: Exception) {
            Log.e("LOOK AT ME", "${e.message}")
            throw e
        }
    }

    suspend fun deleteMascota(token: String, id: Int): CustomResponse {
        return try {
            withContext(Dispatchers.IO) {
                mascotaApi.deleteMascota(token, id)
            }
        } catch (e: Exception) {
            Log.e("LOOK AT ME", "${e.message}")
            CustomResponse(data = "Error", status = 500, false)
        }
    }

}