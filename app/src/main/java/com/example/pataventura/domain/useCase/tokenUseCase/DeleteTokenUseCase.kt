package com.example.pataventura.domain.useCase.tokenUseCase

import android.util.Log
import com.example.pataventura.data.database.dao.TokenDao
import com.example.pataventura.data.network.repository.CuidadorRepository
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.domain.model.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteTokenUseCase @Inject constructor(
    private val tokenDao: TokenDao
) {
    suspend fun deleteToken() {
        withContext(Dispatchers.IO) {
            try {
                tokenDao.clearAll()
            } catch (e: Exception) {
                Log.e("DAO", "Error al borrar todas las filas: ${e.message}")
                throw e
            }
        }

    }
}