package com.example.pataventura.data.network.repository

import com.example.pataventura.data.model.TiposServicioModel
import com.example.pataventura.data.network.service.TiposOfertaService
import javax.inject.Inject

class TiposOfertaRepository @Inject constructor(
    private val ofertaService: TiposOfertaService
) {
    suspend fun getTiposOferta(token: String): List<TiposServicioModel> {
        return try {
            val servicios = ofertaService.getTiposOferta(token)
            servicios
        } catch (e: Exception) {
            throw e
        }
    }
}