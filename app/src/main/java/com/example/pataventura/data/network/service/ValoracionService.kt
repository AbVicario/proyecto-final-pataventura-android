package com.example.pataventura.data.network.service

import com.example.pataventura.data.model.ValoracionModel
import com.example.pataventura.data.network.ApiValoracion
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.domain.model.Valoracion
import com.example.pataventura.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ValoracionService @Inject constructor(
    private val apiValoracion: ApiValoracion
) {
    suspend fun getValoraciones(token: String, idCuidador: Int): List<Valoracion> {
        return try {
            withContext(Dispatchers.IO) {
                val valoraciones = mutableListOf<Valoracion>()
                val response = apiValoracion.getValoraciones(token, idCuidador)
                for (valoracionResponse in response.data) {
                    val valoracion = ValoracionModel(
                        valoracionResponse.idValoracion,
                        valoracionResponse.puntuacion,
                        valoracionResponse.descripcion,
                        valoracionResponse.aliasTutor,
                        valoracionResponse.imagenTutor
                    )
                    valoraciones.add(valoracion.toDomain())
                }
                valoraciones
            }

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
            withContext(Dispatchers.IO) {
                val valoracionRequest = ValoracionRequest(idDemanda, valoracion, comentario)
                val response = apiValoracion.registerValoracion( token, valoracionRequest)
                response
            }
        } catch (e: Exception) {
            throw e
        }
    }
}

data class ValoracionRequest(
    val id_demanda: Int,
    val puntuacion: Int,
    val descripcion: String,
)