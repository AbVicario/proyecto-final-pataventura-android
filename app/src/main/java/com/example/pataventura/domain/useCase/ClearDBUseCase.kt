package com.example.pataventura.domain.useCase

import com.example.pataventura.data.database.dao.CuidadorDao
import com.example.pataventura.data.database.dao.MascotaDao
import com.example.pataventura.data.database.dao.ServicioDao
import com.example.pataventura.data.database.dao.TokenDao
import com.example.pataventura.data.database.dao.TutorDao
import com.example.pataventura.domain.model.Cuidador
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClearDBUseCase@Inject constructor(
    private val tokenDao: TokenDao,
    private val tutorDao: TutorDao,
    private val cuidadorDao: CuidadorDao,
    private val servicioDao: ServicioDao,
    private val mascotaDao: MascotaDao,

    ) {
    suspend  fun clearDB() {
        try {
            withContext(Dispatchers.IO) {
                tokenDao.clearAll()
                tutorDao.deleteTutor()
                cuidadorDao.deleteCuidador()
                servicioDao.clearAll()
                mascotaDao.clearAll()
            }
        }catch (e:Exception){
            throw e
        }
    }
}