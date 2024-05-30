package com.example.pataventura.domain.useCase.notificacionUseCase

import com.example.pataventura.data.network.repository.NotificacionRepository
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.domain.model.Notificacion
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class UpdateNotificacionesUseCase @Inject constructor(
    private val notificacionRepository: NotificacionRepository,
    private val tokenGetUseCase: TokenGetUseCase
) {
    suspend fun updateNotificacion(notificacion: Notificacion): CustomResponse {
        return try {
            val token = tokenGetUseCase.getToken().token
            val response = notificacionRepository.updateNotificacion(token,notificacion)
            response
        } catch (e: Exception) {
            throw e
        }


    }
}