
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CampoObligatorioText
import com.example.pataventura.ui.composables.CustomOutlinedTextField
import com.example.pataventura.ui.composables.CustomOutlinedTextFieldPass
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.EmailNoValidoText
import com.example.pataventura.ui.composables.HeaderLogin
import com.example.pataventura.ui.composables.LoginButton
import com.example.pataventura.ui.composables.MyAlertDialog
import com.example.pataventura.ui.screens.login.LoginViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.VerdeOliva

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HeaderLogin()
            BodyLogin(loginViewModel, navController)
        }
    }

}
@Composable
fun BodyLogin(loginViewModel:LoginViewModel, navController: NavController) {
    val emailNoValido: Boolean by loginViewModel.emailNoValido.observeAsState(false)
    val emailEmpty: Boolean by loginViewModel.emailEmpty.observeAsState(false)
    val passEmpty: Boolean by loginViewModel.passEmpty.observeAsState(false)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.silueta_de_gato),
            contentDescription = "Silueta gato", Modifier.fillMaxSize()
        )

        Column(
            Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomOutlinedTextField(
                onValueChange = { loginViewModel.onEmailChange(it)},
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                enabled = true,
                readOnly = false,
                placeholder = "Email",
                leadingIcon = { Icon(Icons.Default.Mail, null) },
                trailingIcon = {},
                supportingText = {if(emailEmpty) CampoObligatorioText()
                else if(emailNoValido) EmailNoValidoText()},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )
            CustomOutlinedTextFieldPass(
                onValueChange = { loginViewModel.onPasswordChange(it)},
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                enabled = true,
                readOnly = false,
                placeholder = "Password",
                leadingIcon = { Icon(Icons.Default.Lock, null) },
                supportingText = {if(passEmpty) CampoObligatorioText()},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )

            Box(
                Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp)
            ) {
                MyAlertDialog(
                    show = loginViewModel.showDialog,
                    icon = Icons.Default.Error,
                    onConfirm = { loginViewModel.onDialogConfirm() },
                    dialogTitle = "Error",
                    dialogText = "Ha habido un error inesperado\n" +
                            " Intentelo más tarde"
                )
                LoginButton(text = "Acceder", null,
                    onClick= { loginViewModel.onLoginButtonClicked(navController) }
                )
            }

            Row(
                Modifier.clickable { },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_google),
                    contentDescription = "Logo Google", Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                CustomText(
                    text = "Acceder con Google",
                    color = VerdeOliva, fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = CustomFontFamily,
                    Modifier.clickable { loginViewModel.onGooglePress(navController) }
                )
            }

            CustomText(
                text = "ó",
                color = VerdeOliva, fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = CustomFontFamily
            )

            Box(Modifier.clickable { }) {
                CustomText(
                    text = "Registrase",
                    color = VerdeOliva, fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = CustomFontFamily,
                    Modifier.clickable { loginViewModel.onRegistrarsePress(navController) }
                )
            }

        }

    }
}











