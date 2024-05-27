package com.example.pataventura.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pataventura.data.database.entity.ServicioEntity

@Dao
interface ServicioDao {
    @Query("SELECT * from servicio_table")
    fun getServicios(): List<ServicioEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertServicio(Servicio: ServicioEntity)

    @Update
    fun updateServicio(servicio: ServicioEntity)

    @Query("DELETE FROM servicio_table WHERE id_oferta = :idServicio")
    fun deleteServicio(idServicio: Int)
    @Query("DELETE FROM servicio_table ")
    fun clearAll()
}