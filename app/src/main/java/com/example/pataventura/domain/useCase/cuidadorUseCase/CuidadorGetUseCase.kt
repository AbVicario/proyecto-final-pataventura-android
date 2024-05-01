package com.example.pataventura.domain.useCase.cuidadorUseCase

import com.example.pataventura.data.network.repository.CuidadorRepository
import com.example.pataventura.domain.model.Cuidador
import javax.inject.Inject

class CuidadorGetUseCase @Inject constructor(
    private val cuidadorRepository: CuidadorRepository
) {
    suspend fun getCuidador(): Cuidador? {
        return cuidadorRepository.getCuidadorFromDatabase()
    }
}