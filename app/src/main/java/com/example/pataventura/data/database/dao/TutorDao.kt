package com.example.pataventura.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pataventura.data.database.entity.TutorEntity
import com.example.pataventura.data.model.MascotaModel
import com.example.pataventura.data.model.TutorModel

@Dao
interface TutorDao {
    @Query("SELECT * from tutor_table")
    fun getTutor(): TutorEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTutor(tutor: TutorEntity)

    @Query("DELETE FROM tutor_table")
    fun deleteTutor()

}