package com.example.pataventura.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pataventura.data.database.entity.CuidadorEntity
import com.example.pataventura.data.network.response.CustomResponse

@Dao
interface CuidadorDao {
    @Query("SELECT * from cuidador_table")
    fun getCuidador(): CuidadorEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCuidador(cuidador: CuidadorEntity)

    @Query("DELETE FROM cuidador_table")
    fun deleteCuidador(): Int

    @Update
    fun updateCuidador(cuidador: CuidadorEntity)
}