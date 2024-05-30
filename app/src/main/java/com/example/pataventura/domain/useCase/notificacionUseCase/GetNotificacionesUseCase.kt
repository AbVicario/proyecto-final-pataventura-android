package com.example.pataventura.domain.useCase.notificacionUseCase

import com.example.pataventura.data.network.repository.NotificacionRepository
import com.example.pataventura.domain.model.Notificacion
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class GetNotificacionesUseCase @Inject constructor(
    private val notificacionRepository: NotificacionRepository,
    private val tokenGetUseCase: TokenGetUseCase
) {
    suspend fun getNotificaciones(rol:String): List<Notificacion> {
        return try {
            val token = tokenGetUseCase.getToken().token
            val notificaciones = notificacionRepository.getNotificaciones(token, rol)
            notificaciones
        } catch (e: Exception) {
            throw e
        }


    }
}