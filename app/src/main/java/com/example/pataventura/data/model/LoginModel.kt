package com.example.pataventura.data.model

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String
)
