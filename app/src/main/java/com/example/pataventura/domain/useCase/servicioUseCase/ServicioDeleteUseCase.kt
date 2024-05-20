package com.example.pataventura.domain.useCase.servicioUseCase

import android.util.Log
import com.example.pataventura.data.network.repository.ServicioRepository
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.domain.model.Servicio
import com.example.pataventura.domain.model.toEntity
import com.example.pataventura.domain.model.toModel
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class ServicioDeleteUseCase@Inject constructor(
    private val servicioRepository: ServicioRepository,
    private val tokenGetUseCase: TokenGetUseCase
) {

    suspend fun deleteServicio(servicio: Servicio) : CustomResponse{
        //servicioRepository.deleteServicioFromDatabase(servicio.idOferta)
        val token = tokenGetUseCase.getToken().token
        Log.d("token",token)
        val response = servicioRepository.deleteServicioFromApi(token,servicio.toModel())
        if(response.status ==200){
            servicioRepository.deleteServicioFromDatabase(servicio.idOferta)

        }
        return response
    }
}