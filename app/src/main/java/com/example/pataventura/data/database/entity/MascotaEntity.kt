package com.example.pataventura.data.database.entity

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mascota_table")
data class MascotaEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_mascota") var idMascota: Int,
    @ColumnInfo(name = "nombre") var nombre: String,
    @ColumnInfo(name = "num_chip") var numChip: String,
    @ColumnInfo(name = "edad") var edad: String = "",
    @ColumnInfo(name = "imagen") var imagen: String = "",
    @ColumnInfo(name = "tamanyo") var tamanyo: Float,
    @ColumnInfo(name = "peso") var peso: Float,
    @ColumnInfo(name = "tipo") var tipo: String,
    @ColumnInfo(name = "raza") var raza: String = "",
    @ColumnInfo(name = "observacion") var observacion: String = "",
    @ColumnInfo(name = "color") var color: String
){
    constructor() : this(0, "", "", "", "", 0.0f, 0.0f, "", "", "", Color.Black.toString())
}