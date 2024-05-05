package com.example.pataventura.domain.model

import com.example.pataventura.data.database.entity.TokenEntity
import com.example.pataventura.data.model.TokenModel

data class Token(val token: String)

fun TokenModel.toDomain() = Token(token)
fun TokenEntity.toDomain() = Token(token)

fun Token.toModel() = TokenModel(token)
fun Token.toEntity() = TokenEntity(token)