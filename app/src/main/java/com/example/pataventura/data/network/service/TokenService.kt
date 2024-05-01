package com.example.pataventura.data.network.service

import android.util.Log
import com.example.pataventura.data.model.LoginModel
import com.example.pataventura.data.model.TokenModel
import com.example.pataventura.data.network.ApiClient
import com.example.pataventura.data.network.response.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TokenService @Inject constructor(
    private val tokenApi: ApiClient
) {
    suspend fun getTokenFromApi(loginModel: LoginModel): TokenModel {
        return withContext(Dispatchers.IO) {
            val tokenResponse = tokenApi.doLogin(loginModel)
            tokenResponse.body() ?: throw IllegalStateException("Token response body is null")
        }
    }
}