package com.example.pataventura.data.network.repository

import android.util.Log
import com.example.pataventura.data.database.dao.TokenDao
import com.example.pataventura.data.database.dao.TutorDao
import com.example.pataventura.data.database.entity.TokenEntity
import com.example.pataventura.data.database.entity.TutorEntity
import com.example.pataventura.data.model.LoginModel
import com.example.pataventura.data.model.TokenModel
import com.example.pataventura.data.model.TutorModel
import com.example.pataventura.data.network.response.CuidadorResponse
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.response.TutorResponse
import com.example.pataventura.data.network.service.TokenService
import com.example.pataventura.data.network.service.TutorService
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.Token
import com.example.pataventura.domain.model.Tutor
import com.example.pataventura.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TutorRepository @Inject constructor(
    private val tutorService: TutorService,
    private val tutorDao: TutorDao
) {
    suspend fun registerTutorFromApi(tutorModel: TutorModel): CustomResponse {
        return tutorService.registerTutorFromApi(tutorModel)
    }

    suspend fun insertTutorToDatabase(tutorEntity: TutorEntity) {
        return withContext(Dispatchers.IO) {
            try {

                tutorDao.insertTutor(tutorEntity)

            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
            }
        }
    }

    suspend fun getTutorFromDatabase(): Tutor? {
        return withContext(Dispatchers.IO) {
            try {
                val tutor = tutorDao.getTutor()
                tutor?.toDomain()

            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
                null
            }
        }
    }

    suspend fun updateTutorFromApi(token: String, tutorModel: TutorModel): TutorResponse {
        return withContext(Dispatchers.IO) {
            try {
                val response = tutorService.updateTutorFromApi(token, tutorModel)
                response
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
                throw e
            }
        }
    }

    suspend fun updateTutorFromDatabase(tutor: TutorEntity) {
        return withContext(Dispatchers.IO) {
            try {
                tutorDao.updateTutor(tutor)
            } catch (e: Exception) {
               throw e
            }
        }
    }

    suspend fun getCuidadoresFromApi(token: String, tipo: String): List<Cuidador> {
        return withContext(Dispatchers.IO) {
            try {
                tutorService.getCuidadoresFromApi(token, tipo)
            } catch (e: Exception) {
                Log.e("LOOK AT ME", "${e.message}")
                emptyList()
            }
        }
    }

    suspend fun getTutorFromApi(token: String): Tutor? {
        return try {
            val tutor = tutorService.getTutorFromApi(token)
            tutor.toDomain()
        } catch (e: Exception) {
            Log.e("LOOK AT ME", "${e.message}")
            null
        }
    }
}