package com.example.pataventura.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tutor_table")
data class TutorEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_usuario") var idUsuario: Int,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "telefono") var telefono: String,
    @ColumnInfo(name = "nombre") var nombre: String,
    @ColumnInfo(name = "apellido") var apellido: String,
    @ColumnInfo(name = "imagen") var imagen: String = "",
    @ColumnInfo(name = "alias") var alias: String,
    @ColumnInfo(name = "direccion") var direccion: String

)