package com.example.pataventura.data.network.service

import android.util.Log
import com.example.pataventura.data.model.ServicioModel
import com.example.pataventura.data.network.ApiServicio
import com.example.pataventura.data.network.response.CustomResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ServicioService @Inject constructor(
    private val servicioApi: ApiServicio
) {
    suspend fun registerServicioFromApi(token : String, servicioModel: ServicioModel,
                                        idCuidador:Int): CustomResponse {
        return try {
            withContext(Dispatchers.IO) {
                Log.d("Prueba", servicioModel.toString())
                servicioApi.guardarServicio(token , servicioModel, idCuidador)
            }
        } catch (e: Exception) {
            Log.e("Prueba", "${e.message}")
            CustomResponse(data = e.toString(), status = 500, false)
        }
    }

    suspend fun updateServicioFromApi(token: String, servicioModel: ServicioModel): CustomResponse {
        return try {
            withContext(Dispatchers.IO) {
                servicioApi.updateServicio(token, servicioModel.idOferta)
            }
        } catch (e: Exception) {
            Log.e("LOOK AT ME", "${e.message}")
            CustomResponse(data = e.toString(), status = 500, false)
        }
    }

    suspend fun deleteServicioFromApi(token: String, servicioModel: ServicioModel): CustomResponse {
        return try {
            withContext(Dispatchers.IO) {
                servicioApi.deleteServicio(token, servicioModel.idOferta)
            }
        } catch (e: Exception) {
            Log.e("LOOK AT ME", "${e.message}")
            CustomResponse(data = e.toString(), status = 500, false)
        }
    }

    suspend fun getServiciosByTipo(token: String, servicioModel: ServicioModel): List<ServicioModel> {
        return try {
            withContext(Dispatchers.IO) {
                servicioApi.getServiciosByTipo(token, servicioModel.tipo)
            }
        } catch (e: Exception) {
            Log.e("LOOK AT ME", "${e.message}")
            emptyList()
        }
    }

    suspend fun getServicios(token: String): List<ServicioModel> {
        return try {
            withContext(Dispatchers.IO) {
                servicioApi.getServicios(token)
            }
        } catch (e: Exception) {
            Log.e("LOOK AT ME", "${e.message}")
            emptyList()
        }
    }

}
