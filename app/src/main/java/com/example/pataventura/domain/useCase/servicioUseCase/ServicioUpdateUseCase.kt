package com.example.pataventura.domain.useCase.servicioUseCase

import com.example.pataventura.data.network.repository.ServicioRepository
import com.example.pataventura.data.network.repository.TokenRepository
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.response.ServicioResponse
import com.example.pataventura.domain.model.Servicio
import com.example.pataventura.domain.model.toEntity
import com.example.pataventura.domain.model.toModel
import javax.inject.Inject

class ServicioUpdateUseCase@Inject constructor(
    private val tokenRepository: TokenRepository,
    private val servicioRepository: ServicioRepository
) {
    suspend fun updateServicio(servicio: Servicio): CustomResponse {
        try {
            val token = tokenRepository.getTokenFromDatabase()
            val response = servicioRepository.updateServicioFromApi(token.token, servicio.toModel())
            if (response.status == 200) {
                servicioRepository.updateServicioFromDatabase(servicio.toEntity())
            }
            return response
        } catch (e: Exception) {
            throw e
        }
    }
}