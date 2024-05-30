package com.example.pataventura.domain.useCase.demandaUseCase

import com.example.pataventura.data.network.repository.DemandaRepository
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.domain.model.Demanda
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class DemandaUpdateUseCase@Inject constructor(
    private val getTokenUseCase: TokenGetUseCase,
    private val repository: DemandaRepository
){
    suspend fun updateDemanda( demanda: Demanda):CustomResponse{
        return try{
            val token = getTokenUseCase.getToken().token
            val response=repository.updateDemanda(token, demanda)
            response
        }catch (e:Exception){
            throw e
        }
    }
}