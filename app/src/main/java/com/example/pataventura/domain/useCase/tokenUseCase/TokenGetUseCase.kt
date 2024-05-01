package com.example.pataventura.domain.useCase.tokenUseCase

import com.example.pataventura.data.network.repository.TokenRepository
import com.example.pataventura.domain.model.Token
import javax.inject.Inject

class TokenGetUseCase@Inject constructor(
    private val tokenRepository: TokenRepository
) {
    suspend fun getToken(): Token {
        return tokenRepository.getTokenFromDatabase()
    }
}