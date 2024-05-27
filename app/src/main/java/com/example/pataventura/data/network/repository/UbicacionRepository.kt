package com.example.pataventura.data.network.repository

import com.example.pataventura.data.model.UbicacionModel
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.service.UbicacionService
import com.example.pataventura.domain.model.Cuidador
import javax.inject.Inject

class UbicacionRepository  @Inject constructor(
    private val ubicacionService: UbicacionService
) {

    suspend fun registerUbicacionCuidadorFromApi(token: String, ubicacion: UbicacionModel): CustomResponse {
        return ubicacionService.registerUbicacionCuidadorFromApi(token, ubicacion)
    }

    suspend fun registerUbicacionTutorFromApi(token: String, ubicacion: UbicacionModel): CustomResponse {
        return ubicacionService.registerUbicacionTutorFromApi(token, ubicacion)
    }
}