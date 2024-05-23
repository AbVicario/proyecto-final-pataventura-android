package com.example.pataventura.domain.useCase.mascotaUseCase

import com.example.pataventura.data.database.dao.MascotaDao
import com.example.pataventura.data.database.dao.TokenDao
import com.example.pataventura.data.network.repository.MascotaRepository
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.domain.model.toDomain
import com.example.pataventura.domain.model.toEntity
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MascotaGetUseCase@Inject constructor(
    private val mascotaRepository: MascotaRepository,
    private val tokenDao: TokenDao
) {

    suspend fun getMascotas(): List<Mascota> {
        return withContext(Dispatchers.IO) {
            var mascotas = mascotaRepository.getMascotasFromDatabase()
            if (mascotas.isEmpty()) {
                val token = tokenDao.getToken().token
                mascotas = mascotaRepository.getMascotasFromApi(token)
                if (mascotas.isNotEmpty()) {
                    for (mascota in mascotas) {
                        mascotaRepository.insertMascotaToDatabase(mascota.toEntity())
                    }
                }
            }
            mascotas.map { it }
        }
    }


    suspend fun getMascota(id:Int):Mascota?{
        return withContext(Dispatchers.IO){
            try {
                var mascota = mascotaRepository.getOneMascotaFromDatabase(id)
                if (mascota == null) {
                    val token = tokenDao.getToken().token
                    mascota = mascotaRepository.getOneMascotaFromApi(token, id)
                    if (mascota != null) {
                        mascotaRepository.insertMascotaToDatabase(mascota.toEntity())
                    }
                }
                mascota
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }

        }

    }
}