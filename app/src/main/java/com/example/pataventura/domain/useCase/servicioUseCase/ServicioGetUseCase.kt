package com.example.pataventura.domain.useCase.servicioUseCase

import com.example.pataventura.data.network.repository.ServicioRepository
import com.example.pataventura.domain.model.Servicio
import javax.inject.Inject

class ServicioGetUseCase  @Inject constructor(
private val servicioRepository: ServicioRepository
) {
    suspend fun getServiciosFromDataBase(): List<Servicio> {
        return servicioRepository.getServiciosFromDatabase()
    }
} 