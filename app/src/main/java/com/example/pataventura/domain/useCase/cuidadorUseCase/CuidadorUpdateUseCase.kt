package com.example.pataventura.domain.useCase.cuidadorUseCase

import com.example.pataventura.data.database.dao.CuidadorDao
import com.example.pataventura.data.network.repository.CuidadorRepository
import com.example.pataventura.data.network.repository.TokenRepository
import com.example.pataventura.data.network.response.CuidadorResponse
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.toEntity
import com.example.pataventura.domain.model.toModel
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class CuidadorUpdateUseCase @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val cuidadorRepository: CuidadorRepository,
) {
    suspend fun updateCuidador(cuidador: Cuidador): CuidadorResponse {
        try {
            val token = tokenRepository.getTokenFromDatabase()
            val response = cuidadorRepository.updateCuidadorFromApi(token.token, cuidador.toModel())
            if (response.status == 200) {
               // cuidador.idUsuario = response.data.idUsuario
                cuidadorRepository.updateCuidadorFromDatabase(cuidador.toEntity())
            }
            return response
        } catch (e: Exception) {
            throw e
        }
    }
}