package com.example.pataventura.domain.useCase.mascotaUseCase

import com.example.pataventura.data.database.dao.MascotaDao
import com.example.pataventura.data.network.repository.MascotaRepository
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.domain.model.toDomain
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MascotaGetUseCase@Inject constructor(
    private val mascotaDao: MascotaDao
) {

    suspend fun getMascotas():List<Mascota>{
        return withContext(Dispatchers.IO){
            val mascotas = mascotaDao.getMascotas()
            if(mascotas.isEmpty()){
                emptyList<Mascota>()
            }
            mascotas.map { it.toDomain() }
        }

    }

    suspend fun getMascota(id:Int):Mascota{
        return withContext(Dispatchers.IO){
            val mascota = mascotaDao.getMascota(id)
            mascota.toDomain()
        }

    }
}