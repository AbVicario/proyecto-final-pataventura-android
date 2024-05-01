package com.example.pataventura.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pataventura.data.database.entity.CuidadorEntity

@Dao
interface CuidadorDao {
    @Query("SELECT * from cuidador_table")
    fun getCuidador(): CuidadorEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCuidador(cuidador: CuidadorEntity)

    @Query("DELETE FROM cuidador_table")
    fun deleteCuidador()
}