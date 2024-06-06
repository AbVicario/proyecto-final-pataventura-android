package com.example.pataventura.data.network.service

import com.example.pataventura.data.network.ApiServicio
import com.example.pataventura.domain.model.DemandaAceptada
import javax.inject.Inject

class DemandaAceptadaService @Inject constructor(
    private val apiServicio: ApiServicio
) {
    suspend fun getDemandasAceptadas(token: String, rol: String): List<DemandaAceptada> {
        return try {
            val response = apiServicio.getDemandasAceptadas(token, rol)
            val demandaAceptadas = mutableListOf<DemandaAceptada>()
            for (demandaResponse in response.data) {
                val demanda = DemandaAceptada(
                    demandaResponse.idDemanda,
                    demandaResponse.fechaFin,
                    demandaResponse.fechaInicio,
                    demandaResponse.descripcion,
                    demandaResponse.precio,
                    demandaResponse.estado,
                    demandaResponse.oferta,
                    demandaResponse.mascota,
                    demandaResponse.tutor,
                    demandaResponse.cuidador
                )
                demandaAceptadas.add(demanda)
            }
            demandaAceptadas

        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getDemandasRealizadas(token: String, rol: String): List<DemandaAceptada> {
        return try {
            val response = apiServicio.getDemandasRealizadas(token, rol)
            val demandaAceptadas = mutableListOf<DemandaAceptada>()
            for (demandaResponse in response.data) {
                val demanda = DemandaAceptada(
                    demandaResponse.idDemanda,
                    demandaResponse.fechaFin,
                    demandaResponse.fechaInicio,
                    demandaResponse.descripcion,
                    demandaResponse.precio,
                    demandaResponse.estado,
                    demandaResponse.oferta,
                    demandaResponse.mascota,
                    demandaResponse.tutor,
                    demandaResponse.cuidador
                )
                demandaAceptadas.add(demanda)
            }
            demandaAceptadas

        } catch (e: Exception) {
            throw e
        }
    }

}
