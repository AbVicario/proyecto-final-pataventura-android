package com.example.pataventura.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pataventura.data.database.entity.MascotaEntity
import com.example.pataventura.data.model.MascotaModel
import okhttp3.Response

@Dao
interface MascotaDao {
    @Query("SELECT * from mascota_table")
    fun getMascotas(): List<MascotaEntity>

    @Query("SELECT * from mascota_table WHERE id_mascota = :idMascota")
    fun getMascota(idMascota: Int): MascotaEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMascota(mascota: MascotaEntity)
    @Update
    fun updateMascota(mascota: MascotaEntity)

    @Query("DELETE FROM mascota_table WHERE id_mascota = :idMascota")
    fun deleteMascota(idMascota: Int)
}