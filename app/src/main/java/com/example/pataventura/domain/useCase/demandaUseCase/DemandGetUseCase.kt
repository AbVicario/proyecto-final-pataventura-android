package com.example.pataventura.domain.useCase.demandaUseCase

import com.example.pataventura.data.network.repository.DemandaRepository
import com.example.pataventura.data.network.response.DemandaResponse
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class DemandGetUseCase @Inject constructor(
    private val demandaRepository: DemandaRepository,
    private val getTokenUseCase: TokenGetUseCase
) {

    suspend fun getDemandasByEstado(id_mascota:Int, estado: String): List<DemandaResponse> {
        return try {
            val token = getTokenUseCase.getToken().token
            val listaDemandas = demandaRepository.getDemandas(token, id_mascota, estado)
            listaDemandas
        } catch (e: Exception) {throw e}

    }
}