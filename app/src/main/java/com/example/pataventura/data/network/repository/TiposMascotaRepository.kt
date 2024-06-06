package com.example.pataventura.data.network.repository

import com.example.pataventura.data.model.TiposMascotaModel
import com.example.pataventura.data.network.service.TiposMascotaService
import javax.inject.Inject

class TiposMascotaRepository @Inject constructor(
    private val mascotaService: TiposMascotaService
) {
    suspend fun getTiposMascota(token: String): List<TiposMascotaModel> {
        return try {
            val tiposMascota = mascotaService.getTiposMascota(token)
            tiposMascota
        } catch (e: Exception) {
            throw e
        }
    }
}