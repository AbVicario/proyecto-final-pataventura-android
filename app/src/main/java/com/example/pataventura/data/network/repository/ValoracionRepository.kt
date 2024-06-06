package com.example.pataventura.data.network.repository

import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.service.ValoracionService
import com.example.pataventura.domain.model.Valoracion
import javax.inject.Inject

class ValoracionRepository @Inject constructor(
    private val valoracionService: ValoracionService
) {
    suspend fun getValoraciones(token: String, idCuidador: Int): List<Valoracion> {
        return try {
            val listaValoraciones = valoracionService.getValoraciones(token, idCuidador)
            listaValoraciones
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun registerValoracion(
        comentario: String,
        valoracion: Int,
        token: String,
        idDemanda: Int
    ): CustomResponse {
        return try {
            val response = valoracionService.registerValoracion(comentario, valoracion, token, idDemanda)
            response
        } catch (e: Exception) {
            throw e
        }
    }

}