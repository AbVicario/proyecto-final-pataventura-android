
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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CustomOutlinedTextEmail
import com.example.pataventura.ui.composables.CustomOutlinedTextPass
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.HeaderLogin
import com.example.pataventura.ui.composables.LoginButton
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
            Modifier.fillMaxSize().padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            val textFieldEmail =
            CustomOutlinedTextEmail(placeholder = "Email:",
                loginViewModel.tieneError.get()) {
                loginViewModel.onEmailChange(
                    it
                )
            }
            CustomOutlinedTextPass(placeholder = "Password:",
                loginViewModel.tieneError.get()) {
                loginViewModel.onPasswordChange(
                    it
                )
            }

            Box(
                Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp)
            ) {
                LoginButton(text = "Acceder",
                    onClick= { loginViewModel.onLoginPress(navController) }
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











