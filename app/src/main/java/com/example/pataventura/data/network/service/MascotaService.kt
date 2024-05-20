package com.example.pataventura.data.network.service

import android.util.Log
import com.example.pataventura.data.model.MascotaModel
import com.example.pataventura.data.network.ApiMascota
import com.example.pataventura.data.network.response.CustomResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MascotaService @Inject constructor(
    private val mascotaApi: ApiMascota
) {
    suspend fun registerMascotaFromApi(
        token: String,
        mascotaModel: MascotaModel,
    ): CustomResponse {
        return try {
            withContext(Dispatchers.IO) {
                mascotaApi.registerMascota(token, mascotaModel)
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

    suspend fun getOneMascota(token: String, id: Int): MascotaModel {
        return try {
            withContext(Dispatchers.IO) {
                mascotaApi.getOneMascota(token, id)
            }
        } catch (e: Exception) {
            Log.e("LOOK AT ME", "${e.message}")
            throw e
        }
    }

    suspend fun getMascotas(token: String): List<MascotaModel> {
        return try {
            withContext(Dispatchers.IO) {
                val mascotas = mutableListOf<MascotaModel>()
                val response = mascotaApi.getMascotas(token)
                Log.e("LOOK AT ME", "${response}")
                for (mascotaResponse in response.data) {
                    val mascota = MascotaModel(
                        mascotaResponse.idMascota,
                        mascotaResponse.nombre,
                        mascotaResponse.numChip,
                        mascotaResponse.edad,
                        mascotaResponse.imagen,
                        mascotaResponse.tamanyo,
                        mascotaResponse.peso,
                        mascotaResponse.tipo,
                        mascotaResponse.raza,
                        mascotaResponse.observacion,
                        mascotaResponse.color,
                        mascotaResponse.sexo
                    )
                    mascotas.add(mascota)
                }
                mascotas
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