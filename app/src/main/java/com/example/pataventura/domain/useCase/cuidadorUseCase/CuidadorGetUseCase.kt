package com.example.pataventura.domain.useCase.cuidadorUseCase

import android.util.Log
import com.example.pataventura.data.network.repository.CuidadorRepository
import com.example.pataventura.data.network.repository.TokenRepository
import com.example.pataventura.domain.model.Cuidador
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CuidadorGetUseCase @Inject constructor(
    private val cuidadorRepository: CuidadorRepository,
    private val tokenRepository: TokenRepository
) {
    suspend fun getCuidador(): Cuidador? {
        val token = tokenRepository.getTokenFromDatabase()
        return cuidadorRepository.getCuidadorFromDatabase()
            ?: return cuidadorRepository.getCuidadorFromApi(token.token)
        Log.e("Cuidador", "jksjhs")

    }
}