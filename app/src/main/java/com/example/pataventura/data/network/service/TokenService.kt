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
    suspend fun getTokenFromApi(loginModel: LoginModel): LoginResponse {
        return withContext(Dispatchers.IO) {
            Log.e("LOGINMODEL", loginModel.toString())
            val response = tokenApi.doLogin(loginModel)
            Log.e("LOGINMODEL", response.toString())
            response
        }
    }
}