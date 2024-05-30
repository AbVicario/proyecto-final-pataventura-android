package com.example.pataventura.data.network.service

import com.example.pataventura.data.network.ApiServicio
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.response.DemandaResponse
import com.example.pataventura.domain.model.Demanda
import javax.inject.Inject

class DemandaService @Inject constructor(
    private val api: ApiServicio
) {
    suspend fun getDemandasByEstado(
        token: String,
        idMascota: Int,
        estado: String
    ): List<DemandaResponse> {
        return try {
            val listaDemandas = api.getDemandasByEstado(token, idMascota, estado)
            listaDemandas
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getDemanda(token: String, id: Int): DemandaResponse {
        return try {
            val response = api.getDemanda(token, id)
            response
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun deleteDemanda(token: String, id: Int): CustomResponse {
        return try {
            val response = api.deleteDemanda(token, id)
            response
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun createDemanda(token: String, demanda: Demanda): CustomResponse {
        return try {
            val response = api.guardarDemanda(token, demanda)
            response

        } catch (e: Exception) {
            throw e
        }

    }

    suspend fun updateDemanda(token: String, demanda: Demanda): CustomResponse {
        return try {
            val response = api.updateDemanda(token, demanda)
            response
        } catch (e: Exception) {
            throw e
        }
    }
}