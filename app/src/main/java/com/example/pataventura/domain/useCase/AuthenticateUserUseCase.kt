package com.example.pataventura.domain.useCase

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.toUpperCase
import com.example.pataventura.R
import com.example.pataventura.data.model.LoginModel
import com.example.pataventura.data.network.repository.TokenRepository
import com.example.pataventura.domain.model.Token
import com.example.pataventura.domain.model.toEntity
import com.example.pataventura.ui.screens.loginCliente.LoginClienteViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

class AuthenticateUserUseCase @Inject constructor(
    private val tokenRepository: TokenRepository,
) {
    suspend fun login(username: String, password: String, rol: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val loginModel = LoginModel(username, password)
                val token:Token
                if(rol == "tutor"){
                    token = tokenRepository.getTokenFromApiTutor(loginModel)
                }else{
                    token = tokenRepository.getTokenFromApiCuidador(loginModel)
                }
                val tokenDatabase = tokenRepository.getTokenFromDatabase()
                if (tokenDatabase.token != token.token) {
                    tokenRepository.delteTokenToDatabase()
                    tokenRepository.insertOneTokenToDatabase(token.toEntity())
                }
                true
            } catch (e: Exception) {
                Log.e("LOOK_AT_ME", e.toString())
                false
            }
        }
    }

}
