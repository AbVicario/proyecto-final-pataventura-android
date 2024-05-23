package com.example.pataventura.domain.useCase.demandaUseCase

import com.example.pataventura.data.network.repository.DemandaRepository
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.domain.model.Demanda
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class DemandaRegisterUseCase @Inject constructor(
    private val demandaRepository: DemandaRepository,
    private val getTokenGetUseCase: TokenGetUseCase
) {
    suspend fun registerDemanda(demanda: Demanda): CustomResponse {
        return try {
            val token = getTokenGetUseCase.getToken().token
            val response = demandaRepository.registerDemanda(token, demanda)
            response
        } catch (e: Exception) {
            throw e
        }
    }
}