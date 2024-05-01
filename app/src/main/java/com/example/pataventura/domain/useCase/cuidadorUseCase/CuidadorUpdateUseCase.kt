package com.example.pataventura.domain.useCase.cuidadorUseCase

import com.example.pataventura.data.network.repository.CuidadorRepository
import javax.inject.Inject

class CuidadorUpdateUseCase  @Inject constructor(
    private val cuidadorRepository: CuidadorRepository
){
}