package com.example.pataventura.data.network.repository

import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.response.DemandaResponse
import com.example.pataventura.data.network.service.DemandaService
import com.example.pataventura.domain.model.Demanda
import javax.inject.Inject

class DemandaRepository@Inject constructor(
    private val demandaService: DemandaService
){
    suspend fun getDemandas(token: String,idMascota:Int, estado: String):List<DemandaResponse>{
      return try {
          val listaDemandas= demandaService.getDemandasByEstado(token,idMascota, estado)
          listaDemandas
      }catch (e:Exception){
          throw e
      }
    }

    suspend fun getDemanda(token: String, id: Int):DemandaResponse{
        return try {
            val response= demandaService.getDemanda(token, id)
            response
        }catch (e:Exception){
            throw e
        }
    }

    suspend fun deleteDemanda(token: String, id: Int):CustomResponse{
        return try {
            demandaService.deleteDemanda(token, id)
        }catch (e:Exception){
            throw e
        }
    }

    suspend fun registerDemanda(token: String, demanda: Demanda):CustomResponse{
        return try {
            demandaService.createDemanda(token, demanda)
        }catch (e:Exception) {
            throw e
        }
    }

    suspend fun updateDemanda(token: String, demanda: Demanda):CustomResponse {
        return try {
            val response = demandaService.updateDemanda(token, demanda)
            response
        } catch (e: Exception) {
            throw e

        }
    }
}
