package com.example.pataventura.domain.useCase

import com.example.pataventura.data.network.repository.DemandaAceptadaRepository
import com.example.pataventura.domain.model.DemandaAceptada
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class GetDemandasAceptadasUseCase@Inject constructor(
    private val demandasAceptadasrepository: DemandaAceptadaRepository,
    private val getTokenUseCase: TokenGetUseCase
){
    suspend fun getDemandasAceptadas(rol: String): List<DemandaAceptada>{
        return try{
            val token = getTokenUseCase.getToken().token
            val demandas = demandasAceptadasrepository.getDemandasAceptadas(token,rol)
            demandas
        }catch (e: Exception){
            throw e
        }
    }
}