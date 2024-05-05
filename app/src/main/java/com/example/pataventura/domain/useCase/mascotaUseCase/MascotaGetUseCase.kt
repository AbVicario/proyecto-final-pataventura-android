package com.example.pataventura.domain.useCase.mascotaUseCase

import com.example.pataventura.data.database.dao.MascotaDao
import com.example.pataventura.data.network.repository.MascotaRepository
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.domain.model.toDomain
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class MascotaGetUseCase@Inject constructor(
    private val mascotaDao: MascotaDao
) {

    fun getMascotas():List<Mascota>{
        val mascotas = mascotaDao.getMascotas()
        if(mascotas.isEmpty()){
            emptyList<Mascota>()
        }
        return mascotas.map { it.toDomain() }
    }

    fun getMascota(id:Int):Mascota{
        val mascota = mascotaDao.getMascota(id)
        return mascota.toDomain()
    }
}