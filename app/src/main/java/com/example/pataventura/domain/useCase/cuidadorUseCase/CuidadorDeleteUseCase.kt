package com.example.pataventura.domain.useCase.cuidadorUseCase

import com.example.pataventura.data.network.repository.CuidadorRepository
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.toModel
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class CuidadorDeleteUseCase @Inject constructor(
    private val cuidadorRepository: CuidadorRepository,
    private val tokenGetUseCase: TokenGetUseCase
) {
    suspend fun deleteCuidador(): Boolean {
        val cuidador = cuidadorRepository.getCuidadorFromDatabase()
        val token = tokenGetUseCase.getToken().token
        val response = cuidadorRepository.deleteCuidadorFromApi(token, cuidador!!.toModel())
        if (response.status == 200) {
            val num = cuidadorRepository.deleteCuidadorFromDataBase()
            if (num > 0) return true
        }
        return false
    }
}