package com.example.pataventura.domain.useCase.ubicacionUseCase

import com.example.pataventura.data.network.repository.UbicacionRepository
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.domain.model.Ubicacion
import com.example.pataventura.domain.model.toModel
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class UbicacionRegisterUseCase @Inject constructor(
    private val ubicacionRepository: UbicacionRepository,
    private val tokenGetUseCase: TokenGetUseCase
) {
    suspend fun registroUbicacionCuidador(ubicacion: Ubicacion): CustomResponse {
        val token = tokenGetUseCase.getToken().token
        val response = ubicacionRepository.registerUbicacionCuidadorFromApi(token, ubicacion.toModel())
        return response
    }

    suspend fun registroUbicacionTutor(ubicacion: Ubicacion): CustomResponse {
        val token = tokenGetUseCase.getToken().token
        val response = ubicacionRepository.registerUbicacionTutorFromApi(token, ubicacion.toModel())
        return response
    }
}