package com.example.pataventura.domain.useCase.servicioUseCase

import com.example.pataventura.data.network.repository.ServicioRepository
import javax.inject.Inject

class ServicioUpdateUseCase@Inject constructor(
    private val servicioRepository: ServicioRepository
) {
}