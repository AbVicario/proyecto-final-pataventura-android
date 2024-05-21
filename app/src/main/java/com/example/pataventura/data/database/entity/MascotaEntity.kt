package com.example.pataventura.data.database.entity

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mascota_table")
data class MascotaEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_mascota") var idMascota: Int,
    @ColumnInfo(name = "nombre") var nombre: String,
    @ColumnInfo(name = "num_chip") var numChip: String,
    @ColumnInfo(name = "edad") var edad: String = "",
    @ColumnInfo(name = "imagen") var imagen: ByteArray? = null,
    @ColumnInfo(name = "tamanyo") var tamanyo: String = "",
    @ColumnInfo(name = "peso") var peso: Double = 0.0,
    @ColumnInfo(name = "tipo") var tipo: String,
    @ColumnInfo(name = "raza") var raza: String = "",
    @ColumnInfo(name = "observacion") var observacion: String = "",
    @ColumnInfo(name = "color") var color: String,
    @ColumnInfo(name = "sexo") var sexo: String = ""
){
    constructor() : this(0, "", "", "", byteArrayOf(), "", 0.0, "", "", "", Color.Black.toString(), "")
}