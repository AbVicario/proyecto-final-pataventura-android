package com.example.pataventura.domain.useCase.demandaUseCase

import com.example.pataventura.data.network.repository.DemandaRepository
import com.example.pataventura.data.network.response.DemandaResponse
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class GetDemandaUseCase @Inject constructor(
    val demandaRepository: DemandaRepository,
    private val getTokenUseCase: TokenGetUseCase
) {

    suspend fun getDemanda(id:Int): DemandaResponse {
        return try {
            val token = getTokenUseCase.getToken().token
            val response = demandaRepository.getDemanda(token,id)
            response
        } catch (e: Exception) {
            throw e
        }

    }
}