package com.example.pataventura.ui.screens.loginCliente


import android.annotation.SuppressLint
import com.example.pataventura.ui.screens.loginCliente.composables.EmailTextField
import com.example.pataventura.ui.screens.loginCliente.composables.PasswordTextField
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.HeaderLogin
import com.example.pataventura.ui.composables.IconBack
import com.example.pataventura.ui.composables.LoginButton
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde
import com.example.pataventura.ui.theme.Verde30
import com.example.pataventura.ui.theme.VerdeOliva


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginClienteScreen(
    /*navController: NavController,*/
    loginClienteViewModel: LoginClienteViewModel
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HeaderLogin()
            BodyLoginCliente()

        }
    }
}
@Composable
fun BodyLoginCliente() {
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.silueta_de_gato),
            contentDescription = "Silueta gato", Modifier.fillMaxSize() )
        Column(Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly) {
            CustomText(
                text = "Acceder como:",
                color = VerdeOliva,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = CustomFontFamily
            )
            LoginButton(text = "Tutor")
            LoginButton(text = "Cuidador")
        }

    }

}



@Composable
fun LoginCliente(/*navController: NavController,*/ loginClienteViewModel: LoginClienteViewModel) {
    val email: String by loginClienteViewModel.email.observeAsState(initial = "")
    val password: String by loginClienteViewModel.password.observeAsState(initial = "")
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EmailTextField(email = email) {
                loginClienteViewModel.onEmailChange(
                    it
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            PasswordTextField(password = password) {
                loginClienteViewModel.onPasswordChange(
                    it
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            LoginButton(text = "Acceder")

           // CustomText(text = "ó", color = Color.Black, fontSize = 20.sp )


        }
    }
}

