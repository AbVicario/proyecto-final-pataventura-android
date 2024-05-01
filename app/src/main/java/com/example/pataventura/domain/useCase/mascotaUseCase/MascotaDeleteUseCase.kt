package com.example.pataventura.domain.useCase.mascotaUseCase

import com.example.pataventura.data.network.repository.MascotaRepository
import javax.inject.Inject

class MascotaDeleteUseCase  @Inject constructor(
    private val mascotaRepository: MascotaRepository
){
}