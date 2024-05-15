package com.example.pataventura.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pataventura.data.database.dao.CuidadorDao
import com.example.pataventura.data.database.dao.MascotaDao
import com.example.pataventura.data.database.dao.ServicioDao
import com.example.pataventura.data.database.dao.TokenDao
import com.example.pataventura.data.database.dao.TutorDao
import com.example.pataventura.data.database.entity.MascotaEntity
import com.example.pataventura.data.database.entity.TokenEntity
import com.example.pataventura.data.database.entity.CuidadorEntity
import com.example.pataventura.data.database.entity.ServicioEntity
import com.example.pataventura.data.database.entity.TutorEntity

@Database(
    entities = [TokenEntity::class, CuidadorEntity::class, MascotaEntity::class,
        TutorEntity::class, ServicioEntity::class], version = 2, exportSchema = false
)
abstract class PataVenturaDatabase : RoomDatabase() {
    abstract fun tokenDao(): TokenDao
    abstract fun tutorDao(): TutorDao
    abstract fun cuidadorDao(): CuidadorDao
    abstract fun servicioDao(): ServicioDao
    abstract fun mascotaDao(): MascotaDao
}