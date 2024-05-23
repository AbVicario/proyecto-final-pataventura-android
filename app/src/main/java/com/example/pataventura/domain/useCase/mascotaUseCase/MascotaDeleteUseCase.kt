package com.example.pataventura.domain.useCase.mascotaUseCase

import com.example.pataventura.data.database.dao.TokenDao
import com.example.pataventura.data.network.repository.MascotaRepository
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.domain.model.toEntity
import com.example.pataventura.domain.model.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MascotaDeleteUseCase  @Inject constructor(
    private val mascotaRepository: MascotaRepository,
    private val tokenDao: TokenDao
){

    suspend fun deleteMascota(idMascota: Int) {
        return withContext(Dispatchers.IO) {
            val token = tokenDao.getToken().token
            val response = mascotaRepository.deleteMascotasFromApi(token, idMascota)
            if (response.status == 200) {
                mascotaRepository.deleteMascotasFromDatabase(idMascota)
            }
        }

    }
}