package com.example.pataventura.domain.useCase.servicioUseCase

import com.example.pataventura.data.network.repository.ServicioRepository
import com.example.pataventura.domain.model.Servicio
import com.example.pataventura.domain.model.toEntity
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class ServicioGetUseCase  @Inject constructor(
    private val servicioRepository: ServicioRepository,
    private val tokenGetUseCase: TokenGetUseCase
    
) {
    suspend fun getServicios(): List<Servicio> {
        return try {
            val token = tokenGetUseCase.getToken().token
            var listaServicios = servicioRepository.getServiciosFromDatabase()
            if (listaServicios.isEmpty()) {
                listaServicios = servicioRepository.getServiciosFromApi(token)
                if (listaServicios.isNotEmpty()){
                    for (servicio in listaServicios) {
                        val servicioEntity = servicio.toEntity()
                        servicioRepository.insertServicioToDatabase(servicioEntity)
                    }
                }
            }
            listaServicios
        } catch (e: Exception) {
            throw e
        }
    }
} 