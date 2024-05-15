package com.example.pataventura.data.network.service

import android.util.Log
import com.example.pataventura.data.model.LoginModel
import com.example.pataventura.data.network.ApiClient
import com.example.pataventura.data.network.response.LoginResponseCuidador
import com.example.pataventura.data.network.response.LoginResponseTutor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TokenService @Inject constructor(
    private val tokenApi: ApiClient
) {
    suspend fun getTokenFromApiTutor(loginModel: LoginModel): LoginResponseTutor {
        return withContext(Dispatchers.IO) {
            Log.e("LOGINMODEL", loginModel.toString())
            val response = tokenApi.doLoginTutor(loginModel)
            Log.e("LOGINMODEL", response.toString())
            response
        }
    }

    suspend fun getTokenFromApiCuidador(loginModel: LoginModel): LoginResponseCuidador {
        return withContext(Dispatchers.IO) {
            Log.e("LOGINMODEL", loginModel.toString())
            val response = tokenApi.doLoginCuidador(loginModel)
            Log.e("LOGINMODEL", response.toString())
            response
        }
    }
}