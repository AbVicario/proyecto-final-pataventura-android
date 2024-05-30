package com.example.pataventura.data.network.repository

import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.service.NotificacionService
import com.example.pataventura.domain.model.Notificacion
import javax.inject.Inject

class NotificacionRepository @Inject constructor(
    private val notificacionService: NotificacionService
) {
    suspend fun getNotificaciones(token: String, rol: String): List<Notificacion> {
        return try {
            val notificaciones = notificacionService.getNotificaciones(token, rol)
            notificaciones
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun updateNotificacion(token: String, notificacion: Notificacion): CustomResponse {
        return try {
            val response = notificacionService.updateNotificacion(token, notificacion)
            response
        } catch (e: Exception) {
            throw e
        }
    }
}
