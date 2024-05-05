package com.example.pataventura.domain.useCase.cuidadorUseCase

import com.example.pataventura.data.database.dao.CuidadorDao
import com.example.pataventura.data.network.repository.CuidadorRepository
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.toEntity
import com.example.pataventura.domain.model.toModel
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class CuidadorUpdateUseCase @Inject constructor(

    private val tokenGetUseCase: TokenGetUseCase,
    private val cuidadorRepository: CuidadorRepository,
    private val cuidadorDao: CuidadorDao
) {
    suspend fun updateCuidador(cuidador: Cuidador): CustomResponse {
        val token = tokenGetUseCase.getToken().token
        val response = cuidadorRepository.updateCuidadorFromApi(token, cuidador.toModel())
        if(response.ok){
            cuidadorDao.updateCuidador(cuidador.toEntity())
        }
        return response
    }
}