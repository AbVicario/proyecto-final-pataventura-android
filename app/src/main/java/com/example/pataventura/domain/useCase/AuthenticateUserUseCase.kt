package com.example.pataventura.domain.useCase

import android.util.Log
import com.example.pataventura.data.model.LoginModel
import com.example.pataventura.data.network.repository.TokenRepository
import com.example.pataventura.domain.model.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthenticateUserUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    suspend fun login(username: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val loginModel = LoginModel(username, password)
                val token = tokenRepository.getTokenFromApi(loginModel)
                val tokenDatabase = tokenRepository.getTokenFromDatabase()
                Log.e("TOKENNNNNN", token.token + tokenDatabase.token)
                if (tokenDatabase.token != token.token) {
                    tokenRepository.delteTokenToDatabase()
                    tokenRepository.insertOneTokenToDatabase(token.toEntity())
                }
                true
            } catch (e: Exception) {
                false
            }
        }
    }
}