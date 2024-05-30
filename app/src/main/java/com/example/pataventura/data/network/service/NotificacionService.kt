package com.example.pataventura.data.network.service

import android.util.Log
import com.example.pataventura.data.network.ApiValoracion
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.domain.model.Notificacion
import com.example.pataventura.domain.model.toModel
import javax.inject.Inject

class NotificacionService @Inject constructor(
    private val apiValoracion: ApiValoracion
) {
    suspend fun getNotificaciones(token: String, rol: String): List<Notificacion> {
        return try {
            val response = apiValoracion.getNotificaciones(token, rol)
            Log.d("Notificaciones", rol)
            val notificaciones = mutableListOf<Notificacion>()
            if (response.ok) {
                for (notificacion in response.data) {
                    val notificacion = Notificacion(
                        idAlerta = notificacion.idAlerta,
                        fechaCreacion = notificacion.fechaCreacion,
                        estado = notificacion.estado,
                        descripcion = notificacion.descripcion,
                        demanda = notificacion.demanda,
                        tipo = notificacion.tipoOferta,
                        direccion = notificacion.direccion,
                        mascotaName = notificacion.mascotaName,
                    )
                    notificaciones.add(notificacion)
                }
            }
            notificaciones
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun updateNotificacion(token: String, notificacion: Notificacion): CustomResponse {
        return try {
            val response =
                apiValoracion.updateNotificacion(token, notificacion.toModel())
            response
        } catch (e: Exception) {
            throw e
        }
    }
}
