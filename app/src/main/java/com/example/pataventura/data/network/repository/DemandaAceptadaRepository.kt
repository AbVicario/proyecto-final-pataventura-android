package com.example.pataventura.data.network.repository

import com.example.pataventura.data.network.service.DemandaAceptadaService
import com.example.pataventura.domain.model.DemandaAceptada
import javax.inject.Inject

class DemandaAceptadaRepository @Inject constructor(
    private val demandaAceptadaService: DemandaAceptadaService
) {
    suspend fun getDemandasAceptadas(token: String, rol: String): List<DemandaAceptada> {
        return try {
            val demandasAceptadas = demandaAceptadaService.getDemandasAceptadas(token, rol)
            demandasAceptadas
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getDemandasRealizadas(token: String, idMascota: Int): List<DemandaAceptada> {
        return try {
            val demandasAceptadas = demandaAceptadaService.getDemandasRealizadas(token, idMascota)
            demandasAceptadas
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getDemandasRealizadasCuidador(token: String): List<DemandaAceptada> {
        return try {
            val demandasAceptadas = demandaAceptadaService.getDemandasRealizadasCuidador(token)
            demandasAceptadas
        } catch (e: Exception) {
            emptyList()
        }
    }
}