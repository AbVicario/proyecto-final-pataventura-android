package com.example.pataventura.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "servicio_table")
data class ServicioEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_oferta") var idOferta: Int,
    @ColumnInfo(name = "tipo") var tipo: String,
    @ColumnInfo(name = "descripcion") var descripcion: String = "",
    @ColumnInfo(name = "precio") var precio: Float,
    @ColumnInfo(name = "radio") var radio: Int
)