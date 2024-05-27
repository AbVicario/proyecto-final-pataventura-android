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
        try {
            val token = tokenRepository.getTokenFromDatabase()
            return cuidadorRepository.getCuidadorFromDatabase()
                ?: return cuidadorRepository.getCuidadorFromApi(token.token)
        } catch (e : Exception) {
            throw e
        }


    }

    suspend fun getCuidadorById(id: Int): Cuidador? {
        val token = tokenRepository.getTokenFromDatabase()
        return cuidadorRepository.getCuidadorByIdFromApi(id, token.token)

    }

    suspend fun getCuidadoresByDistance(): List<Cuidador> {
        val token = tokenRepository.getTokenFromDatabase()
        Log.e("Cuidador", token.token)
        return cuidadorRepository.getCuidadorByDistance(token.token)
    }
}