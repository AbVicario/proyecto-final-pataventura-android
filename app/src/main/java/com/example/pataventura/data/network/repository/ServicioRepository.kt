package com.example.pataventura.data.network.repository

import android.util.Log
import com.example.pataventura.data.database.dao.ServicioDao
import com.example.pataventura.data.database.entity.CuidadorEntity
import com.example.pataventura.data.database.entity.ServicioEntity
import com.example.pataventura.data.model.ServicioModel
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.response.ServicioResponse
import com.example.pataventura.data.network.service.ServicioService
import com.example.pataventura.domain.model.Servicio
import com.example.pataventura.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ServicioRepository @Inject constructor(
    private val servicioService: ServicioService,
    private val servicioDao: ServicioDao
) {
    suspend fun registerServicioFromApi(
        token: String, servicio: ServicioModel
    ): CustomResponse {
        return servicioService.registerServicioFromApi(token, servicio)
    }

    suspend fun insertServicioToDatabase(servicio: ServicioEntity) {
        return withContext(Dispatchers.IO) {
            try {
                servicioDao.insertServicio(servicio)

            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
            }
        }
    }

    suspend fun getServiciosByTipoFromApi(
        token: String,
        servicioModel: ServicioModel
    ): List<ServicioModel> {
        return withContext(Dispatchers.IO) {
            try {
                servicioService.getServiciosByTipo(token, servicioModel)
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
                emptyList()
            }
        }
    }

    suspend fun getServiciosFromApi(token: String): List<Servicio> {
        return withContext(Dispatchers.IO) {
            try {
                Log.e("LOOK AT ME", "LLEGOOO")
                val servicios = servicioService.getServicios(token)
                Log.e("LOOK AT ME", servicios.toString())
                servicios.map{it.toDomain()
                }
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
                throw e
            }
        }
    }

    suspend fun updateServicioFromApi(token: String, servicioModel: ServicioModel): CustomResponse {
        return withContext(Dispatchers.IO) {
            try {
                val response = servicioService.updateServicioFromApi(token, servicioModel)
                response
            } catch (e: Exception) {
                throw e
            }
        }
    }

    suspend fun deleteServicioFromApi(token: String, servicioModel: ServicioModel): CustomResponse {
        return withContext(Dispatchers.IO) {
            try {
               val response = servicioService.deleteServicioFromApi(token, servicioModel)
                response
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
                CustomResponse(e.message!!, 500, false)
            }
        }
    }

    suspend fun getServiciosFromDatabase(): List<Servicio> {
        return withContext(Dispatchers.IO) {
            try {
                val servicios = servicioDao.getServicios()
                servicios.map { it.toDomain() }
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
               emptyList()
            }
        }
    }

    suspend fun updateServicioFromDatabase(servicio: ServicioEntity) {
        return withContext(Dispatchers.IO) {
            try {
                servicioDao.updateServicio(servicio)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    suspend fun deleteServicioFromDatabase(id:Int) {
        return withContext(Dispatchers.IO) {
            try {
                servicioDao.deleteServicio(id)
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
            }
        }
    }

    suspend fun insertServicioFromDatabase(servicio: ServicioEntity) {
        return withContext(Dispatchers.IO) {
            try {
                servicioDao.insertServicio(servicio)
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
            }
        }
    }


}