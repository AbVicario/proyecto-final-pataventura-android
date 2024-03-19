package com.example.pataventura.presentation.screens.loginCliente


import com.example.pataventura.presentation.screens.loginCliente.composables.EmailTextField
import com.example.pataventura.presentation.screens.loginCliente.composables.PasswordTextField
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.R
import com.example.pataventura.presentation.composables.CustomText
import com.example.pataventura.presentation.composables.IconBack
import com.example.pataventura.presentation.composables.LoginButton
import com.example.pataventura.ui.theme.Tierra


@Composable
fun LoginClienteScreen(
    /*navController: NavController,*/
    loginClienteViewModel: LoginClienteViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
        ,
    ) {
        IconBack() /*{
                navController.navigate(Destinations.Home.route)
            }*/
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(300.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    //.height(400.dp)
                    .padding(20.dp)
            ) {
                LoginCliente(/*navController,*/ loginClienteViewModel)
            }
        }
    }

}



@Composable
fun LoginCliente(/*navController: NavController,*/ loginClienteViewModel: LoginClienteViewModel) {
    val email: String by loginClienteViewModel.email.observeAsState(initial = "")
    val password: String by loginClienteViewModel.password.observeAsState(initial = "")
    Box(
        modifier = Modifier
            .background(
                color = Tierra.copy(alpha = 0.3f),
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp),
            ),
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

            CustomText(text = "o acceder con", color = Color.Black, fontSize = 16.sp )


        }
    }
}

