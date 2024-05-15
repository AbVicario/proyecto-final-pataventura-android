package com.example.pataventura.data.network.service

import android.util.Log
import com.example.pataventura.data.model.CuidadorModel
import com.example.pataventura.data.model.LoginModel
import com.example.pataventura.data.model.TokenModel
import com.example.pataventura.data.model.TutorModel
import com.example.pataventura.data.network.ApiClient
import com.example.pataventura.data.network.response.CuidadorResponse
import com.example.pataventura.data.network.response.CustomResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

class CuidadorService @Inject constructor(
    private val cuidadorApi: ApiClient
) {

    suspend fun deleteCuidadorFromApi(token: String, cuidadorModel: CuidadorModel): CustomResponse {
        return try {
            withContext(Dispatchers.IO) {
                Log.d("Prueba", cuidadorModel.toString())
                cuidadorApi.deleteCuidador(token, cuidadorModel.idUsuario)
            }
        } catch (e: Exception) {
            Log.e("Prueba", "${e.message}")
            CustomResponse(data = "Error", status = 500, false)
        }
    }

    suspend fun registerCuidadorFromApi(cuidadorModel: CuidadorModel): CustomResponse {
        return try {
            withContext(Dispatchers.IO) {
                cuidadorApi.registerCuidador(cuidadorModel)
            }
        } catch (e: Exception) {
            Log.e("Prueba", "${e.message}")
            CustomResponse(data = "Error", status = 500, false)
        }
    }

    suspend fun updateCuidadorFromApi(token: String, cuidadorModel: CuidadorModel): CuidadorResponse {
        return try {
            withContext(Dispatchers.IO) {
                cuidadorApi.updateCuidador(token, cuidadorModel.idUsuario, cuidadorModel)
            }
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getCuidadorFromApi(token: String): CuidadorModel {
        return try {
            withContext(Dispatchers.IO) {
                Log.e("LOOK AT ME", "Entra")
                val response = cuidadorApi.getCuidador(token)
                CuidadorModel(
                    response.data.idUsuario,
                    response.data.email,
                    response.data.password,
                    response.data.telefono,
                    response.data.nombre,
                    response.data.apellido,
                    response.data.imagen,
                    response.data.alias,
                    response.data.direccion
                )
            }
        } catch (e: Exception) {
            Log.e("LOOK AT ME", "${e.message}")
            throw e
        }
    }


}