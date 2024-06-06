package com.example.pataventura.domain.useCase.valoracionUseCase

import com.example.pataventura.data.network.repository.ValoracionRepository
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class RegisterValoracionUseCase@Inject constructor(
    private val getTokenUseCase: TokenGetUseCase,
    private val valoracionRepository: ValoracionRepository
) {
    suspend fun registerValoracion(comentario: String, valoracion: Int, idDemanda: Int): CustomResponse{
        return try{
            val token = getTokenUseCase.getToken().token
            return valoracionRepository.registerValoracion(comentario, valoracion, token, idDemanda)
        }catch (e: Exception) {
            throw e
        }
    }
}
