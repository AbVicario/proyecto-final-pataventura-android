package com.example.pataventura.data.network.repository

import android.util.Log
import com.example.pataventura.data.database.dao.MascotaDao
import com.example.pataventura.data.database.entity.MascotaEntity
import com.example.pataventura.data.model.MascotaModel
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.response.MascotaResponse
import com.example.pataventura.data.network.service.MascotaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MascotaRepository @Inject constructor(
    private val mascotaService: MascotaService,
    private val mascotaDao: MascotaDao
) {
    suspend fun registerMascotaFromApi(token : String, mascotaModel: MascotaModel): CustomResponse {
        return mascotaService.registerMascotaFromApi(token, mascotaModel)
    }
    suspend fun updateMascotaFromApi(token: String, mascotaModel: MascotaModel) {
        return withContext(Dispatchers.IO) {
            try {
                mascotaService.updateMascotaFromApi(token, mascotaModel)
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
            }
        }
    }

    suspend fun getOneMascotaFromApi( token: String , mascotaModel: MascotaModel) : MascotaResponse{
        return withContext(Dispatchers.IO) {
            try {
                mascotaService.getOneMascota(token ,mascotaModel.idMascota)
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
                throw e
            }
        }
    }
    suspend fun insertMascotaToDatabase(mascotaEntity: MascotaEntity) {
        return withContext(Dispatchers.IO) {
            try {
                mascotaDao.insertMascota(mascotaEntity)

            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
            }
        }
    }
    suspend fun getMascotasFromDatabase(): List<MascotaEntity> {
        return withContext(Dispatchers.IO) {
            try {
                val mascota = mascotaDao.getMascotas()
                mascota
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
                emptyList()
            }
        }
    }


}