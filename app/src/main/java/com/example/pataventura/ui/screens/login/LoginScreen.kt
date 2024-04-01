
import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CustomOutlinedTextEmail
import com.example.pataventura.ui.composables.CustomOutlinedTextPass
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.HeaderLogin
import com.example.pataventura.ui.composables.LoginButton
import com.example.pataventura.ui.screens.loginCliente.LoginClienteViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.VerdeOliva

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    loginClienteViewModel: LoginClienteViewModel
) {
    val email: String by loginClienteViewModel.email.observeAsState(initial = "")
    val password: String by loginClienteViewModel.password.observeAsState(initial = "")
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HeaderLogin()
            BodyLogin(loginClienteViewModel)
        }
    }

}
@Composable
fun BodyLogin(loginClienteViewModel:LoginClienteViewModel) {
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

            CustomOutlinedTextEmail(placeholder = "Email:") {
                loginClienteViewModel.onEmailChange(
                    it
                )
            }
            CustomOutlinedTextPass(placeholder = "Password:") {
                loginClienteViewModel.onEmailChange(
                    it
                )
            }

            Box(
                Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp)
            ) {
                LoginButton(text = "Acceder")
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
                    fontFamily = CustomFontFamily
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
                    fontFamily = CustomFontFamily
                )
            }

        }

    }
}











