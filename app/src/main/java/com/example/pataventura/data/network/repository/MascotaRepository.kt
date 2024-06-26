package com.example.pataventura.data.network.repository

import android.util.Log
import com.example.pataventura.data.database.dao.MascotaDao
import com.example.pataventura.data.database.entity.MascotaEntity
import com.example.pataventura.data.model.MascotaModel
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.response.MascotaResponse
import com.example.pataventura.data.network.service.MascotaService
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Response
import javax.inject.Inject

class MascotaRepository @Inject constructor(
    private val mascotaService: MascotaService,
    private val mascotaDao: MascotaDao
) {
    suspend fun registerMascotaFromApi(token: String, mascotaModel: MascotaModel): CustomResponse {
        return mascotaService.registerMascotaFromApi(token, mascotaModel)
    }

    suspend fun updateMascotaFromApi(token: String, mascotaModel: MascotaModel): CustomResponse {
        return withContext(Dispatchers.IO) {
            try {
                mascotaService.updateMascotaFromApi(token, mascotaModel)
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
                throw e
            }
        }
    }

    suspend fun getOneMascotaFromApi(token: String, idMascota: Int): Mascota? {
        return withContext(Dispatchers.IO) {
            try {
                val mascota = mascotaService.getOneMascota(token, idMascota)
                mascota?.toDomain()
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
                null
            }
        }
    }

    suspend fun getMascotasFromApi(token: String): List<Mascota> {
        return withContext(Dispatchers.IO) {
            try {
                val mascotas = mascotaService.getMascotas(token)
                mascotas.map {
                    it.toDomain()
                }
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
                throw e
            }
        }
    }

    suspend fun deleteMascotasFromApi(token: String, idMascota: Int): CustomResponse {
        return withContext(Dispatchers.IO) {
            try {
                mascotaService.deleteMascota(token, idMascota)
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
                throw e
            }
        }
    }

    suspend fun insertMascotaToDatabase(mascotaEntity: MascotaEntity) {
        return withContext(Dispatchers.IO) {
            try {
                Log.e("LOOK AT ME--", mascotaEntity.toString())
                mascotaDao.insertMascota(mascotaEntity)
            } catch (e: Exception) {
                Log.e("LOOK AT ME--", "${e.message}")

            }
        }
    }

    suspend fun updateMascotaToDataBase(mascotaEntity: MascotaEntity) {
        return withContext(Dispatchers.IO) {
            try {
                Log.e("LOOK AT ME--", mascotaEntity.toString())
                mascotaDao.updateMascota(mascotaEntity)
            } catch (e: Exception) {
                Log.e("LOOK AT ME--", "${e.message}")
            }
        }
    }

    suspend fun getMascotasFromDatabase(): List<Mascota> {
        return withContext(Dispatchers.IO) {
            try {
                val mascota = mascotaDao.getMascotas()
                mascota.map { it.toDomain() }
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
                emptyList()
            }
        }
    }

    suspend fun getOneMascotaFromDatabase(idMascota: Int): Mascota? {
        return withContext(Dispatchers.IO) {
            try {
                val mascota = mascotaDao.getMascota(idMascota)
                mascota.toDomain()
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
                null
            }
        }
    }

    suspend fun deleteMascotasFromDatabase(idMascota: Int) {
        return withContext(Dispatchers.IO) {
            try {
                mascotaDao.deleteMascota(idMascota)
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
                throw e
            }
        }
    }

}