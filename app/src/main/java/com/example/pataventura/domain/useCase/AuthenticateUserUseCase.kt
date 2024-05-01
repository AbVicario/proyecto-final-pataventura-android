package com.example.pataventura.domain.useCase

import com.example.pataventura.data.database.entity.TokenEntity
import com.example.pataventura.data.model.LoginModel
import com.example.pataventura.data.network.repository.TokenRepository
import javax.inject.Inject

class AuthenticateUserUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    suspend fun login(username: String, password: String): Boolean {
        val loginModel = LoginModel(username, password)
        val token = tokenRepository.getTokenFromApi(loginModel)
        val tokenEntity = TokenEntity(token=token.token)
        tokenRepository.insertOneTokenToDatabase(tokenEntity)
        return true
    }
}