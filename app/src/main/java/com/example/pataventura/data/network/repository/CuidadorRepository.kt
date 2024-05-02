package com.example.pataventura.data.network.repository

import android.util.Log
import com.example.pataventura.data.database.dao.CuidadorDao
import com.example.pataventura.data.database.entity.CuidadorEntity
import com.example.pataventura.data.model.CuidadorModel
import com.example.pataventura.data.model.LoginModel
import com.example.pataventura.data.model.TokenModel

import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.service.CuidadorService
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.Token
import com.example.pataventura.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CuidadorRepository @Inject constructor(
    private val cuidadorService: CuidadorService,
    private val cuidadorDao: CuidadorDao
) {
    suspend fun getTokenFromApi(loginModel: LoginModel): Token {
        val response: TokenModel = cuidadorService.getTokenFromApi(loginModel)
        return response.toDomain()
    }

    suspend fun deleteCuidadorFromApi(token : String, cuidador: CuidadorModel): CustomResponse {
        val response = cuidadorService.deleteCuidadorFromApi(token, cuidador)
        return response
    }

    suspend fun deleteCuidadorFromDataBase(): CustomResponse {
        val response = cuidadorDao.deleteCuidador()
        return response
    }
    suspend fun registerCuidadorFromApi(cuidador: CuidadorModel): CustomResponse {
        return cuidadorService.registerCuidadorFromApi(cuidador)
    }

    suspend fun insertCuidadorToDatabase(cuidador: CuidadorEntity) {
        return withContext(Dispatchers.IO) {
            try {
                cuidadorDao.insertCuidador(cuidador)

            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
            }
        }
    }
    suspend fun updateCuidadorFromApi(token: String, cuidadorModel: CuidadorModel) {
        return withContext(Dispatchers.IO) {
            try {
                cuidadorService.updateCuidadorFromApi(token, cuidadorModel)
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
            }
        }
    }

    suspend fun getCuidadorFromDatabase(): Cuidador? {
        return withContext(Dispatchers.IO) {
            try {
                val cuidador = cuidadorDao.getCuidador()
                cuidador.toDomain()
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
                null
            }
        }
    }


}