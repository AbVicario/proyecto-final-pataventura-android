package com.example.pataventura.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pataventura.data.database.entity.TokenEntity

@Dao
interface TokenDao {

    @Query("SELECT * from token_table")
    fun getToken(): TokenEntity

    @Query("DELETE FROM token_table")
    fun clearAll():Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToken(token: TokenEntity)
}