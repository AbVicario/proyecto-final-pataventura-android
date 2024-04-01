package com.example.pataventura

import LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.pataventura.ui.screens.loginCliente.LoginClienteScreen
import com.example.pataventura.ui.screens.loginCliente.LoginClienteViewModel
import com.example.pataventura.ui.screens.registoMascota.RegistroMascotaScreen
import com.example.pataventura.ui.screens.registoMascota.RegistroMascotaViewModel
import com.example.pataventura.ui.screens.registro.RegistroDosScreen
import com.example.pataventura.ui.screens.registro.RegistroUnoScreen
import com.example.pataventura.ui.screens.registro.RegistroViewModel
import com.example.pataventura.ui.theme.PataVenturaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginClienteViewModel: LoginClienteViewModel by viewModels()
    private val loginViewModel: LoginClienteViewModel by viewModels()
    private val registroViewModel: RegistroViewModel by viewModels()
    private val registroMascotaViewModel: RegistroMascotaViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PataVenturaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    //LoginScreen( loginViewModel)
                    //LoginClienteScreen( loginClienteViewModel)
                    //RegistroUnoScreen(registroViewModel)
                    //RegistroDosScreen(registroViewModel)
                    RegistroMascotaScreen(registroMascotaViewModel)

                }
            }
        }
    }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
   LoginScreen()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    PataVenturaTheme {
        LoginScreen()
    }
}*/