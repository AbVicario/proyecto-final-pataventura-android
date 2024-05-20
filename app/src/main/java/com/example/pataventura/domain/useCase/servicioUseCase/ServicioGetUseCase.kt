package com.example.pataventura.domain.useCase.servicioUseCase

import android.util.Log
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
            var listaServicios : List<Servicio>
            val token = tokenGetUseCase.getToken().token
            listaServicios = servicioRepository.getServiciosFromDatabase()
            Log.e("ser", "getServicios: $listaServicios")
            if (listaServicios.isEmpty()) {
                listaServicios = servicioRepository.getServiciosFromApi(token)
                Log.e("ser", "getServicios: $listaServicios")

                if (listaServicios.isNotEmpty()){
                    for (servicio in listaServicios) {
                        val servicioEntity = servicio.toEntity()
                        servicioRepository.insertServicioToDatabase(servicioEntity)
                    }
                } else {
                    listaServicios = emptyList()
                }
            }
            listaServicios
        } catch (e: Exception) {
            throw e
        }
    }
} 