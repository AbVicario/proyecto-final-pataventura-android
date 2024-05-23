package com.example.pataventura.domain.useCase.demandaUseCase

import com.example.pataventura.data.network.repository.DemandaRepository
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class DemandaDeleteUseCase @Inject constructor(
    private val demandaRepository: DemandaRepository,
    private val tokenGetUseCase: TokenGetUseCase
) {
    suspend fun deleteDemanda(id: Int): CustomResponse {
        return try {
            val token = tokenGetUseCase.getToken().token
            val response = demandaRepository.deleteDemanda(token, id)
            response
        } catch (e: Exception) {
            throw e
        }
    }
}
