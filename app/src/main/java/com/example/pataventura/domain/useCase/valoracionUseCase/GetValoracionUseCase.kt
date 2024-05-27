package com.example.pataventura.domain.useCase.valoracionUseCase

import com.example.pataventura.data.network.repository.ValoracionRepository
import com.example.pataventura.domain.model.Valoracion
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class GetValoracionUseCase @Inject constructor(
    private val getTokenGetUseCase: TokenGetUseCase,
    private val valoracionRepository: ValoracionRepository
){
    suspend fun getValoraciones(idCuidador: Int) : List<Valoracion> {
        return try {
            val token = getTokenGetUseCase.getToken().token
            val list = valoracionRepository.getValoraciones(token, idCuidador)
            list
        } catch (e : Exception) {
            throw e
        }
    }
}