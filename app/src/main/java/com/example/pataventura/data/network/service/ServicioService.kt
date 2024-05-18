package com.example.pataventura.data.network.service

import android.util.Log
import com.example.pataventura.data.model.MascotaModel
import com.example.pataventura.data.model.ServicioModel
import com.example.pataventura.data.network.ApiServicio
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.response.ServicioResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ServicioService @Inject constructor(
    private val servicioApi: ApiServicio
) {
    suspend fun registerServicioFromApi(token : String, servicioModel: ServicioModel): CustomResponse {
        return try {
            withContext(Dispatchers.IO) {
                Log.d("Prueba", servicioModel.toString())
                servicioApi.guardarServicio(token , servicioModel)
            }
        } catch (e: Exception) {
            Log.e("Prueba", "${e.message}")
            CustomResponse(data = e.toString(), status = 500, false)
        }
    }

    suspend fun updateServicioFromApi(token: String, servicioModel: ServicioModel): ServicioResponse {
        return try {
            withContext(Dispatchers.IO) {
                servicioApi.updateServicio(token, servicioModel.idOferta, servicioModel)
            }
        } catch (e: Exception) {
            throw e
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
                val servicios = mutableListOf<ServicioModel>()
                val response = servicioApi.getServicios(token)
                Log.e("LOOK AT ME", "${response}")
                for (servicioResponse in response.data) {
                    val servicio = ServicioModel(
                        servicioResponse.idOferta,
                        servicioResponse.tipo,
                        servicioResponse.descripcion,
                        servicioResponse.precio,
                        servicioResponse.radio
                    )
                    servicios.add(servicio)
                }
                servicios
            }
        } catch (e: Exception) {
            Log.e("LOOK AT ME", "${e.message}")
            throw e
        }
    }

}
