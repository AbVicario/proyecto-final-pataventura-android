package com.example.pataventura.domain.useCase.mascotaUseCase

import com.example.pataventura.data.network.repository.MascotaRepository
import javax.inject.Inject

class MascotaUpdateUseCase@Inject constructor(
    private val mascotaRepository: MascotaRepository
) {

}