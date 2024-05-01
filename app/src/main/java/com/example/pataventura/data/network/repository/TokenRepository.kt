package com.example.pataventura.data.network.repository

import android.util.Log
import com.example.pataventura.data.database.dao.TokenDao
import com.example.pataventura.data.database.entity.TokenEntity
import com.example.pataventura.data.model.LoginModel
import com.example.pataventura.data.model.TokenModel
import com.example.pataventura.data.network.service.TokenService
import com.example.pataventura.domain.model.Token
import com.example.pataventura.domain.model.toDomain

import javax.inject.Inject

class TokenRepository @Inject constructor(
    private val tokenService: TokenService,
    private val tokenDao: TokenDao
) {
    suspend fun getTokenFromApi(loginModel: LoginModel): Token {
        val response :TokenModel= tokenService.getTokenFromApi(loginModel)
        return response.toDomain()
    }

    suspend fun insertOneTokenToDatabase(tokenEntity: TokenEntity) {
        tokenDao.insertToken(tokenEntity)
    }
    suspend fun getTokenFromDatabase(): Token {
        val response: TokenEntity = tokenDao.getToken()
        return response.toDomain()

    }
}