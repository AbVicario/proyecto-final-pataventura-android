
import android.app.Activity
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.R
import com.example.pataventura.presentation.composables.CustomText
import com.example.pataventura.presentation.composables.LoginButton
import com.example.pataventura.presentation.screens.loginCliente.LoginClienteViewModel
import com.example.pataventura.ui.theme.Tierra
import kotlinx.coroutines.delay


@Composable
fun LoginScreen(
    /*navController: NavController,*/
    loginClienteViewModel: LoginClienteViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, end = 10.dp, start = 10.dp, bottom = 20.dp)
        ,
    ) {
        HeaderLogin(modifier = Modifier.padding(bottom = 5.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(300.dp)
            )

            Spacer(modifier = Modifier.size(15.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    //.height(400.dp)
                    .padding(20.dp)
            ) {
                Login(/*navController,*/ loginClienteViewModel)
            }
        }
    }

}

@Composable
fun Login(/*navController: NavController,*/ loginClienteViewModel: LoginClienteViewModel) {
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
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomText(text = "Entrar como:", color = Color.Black, fontSize = 20.sp )
            LoginButton(text = "Tutor")
            LoginButton(text = "Cuidador")
            CustomText(text = "Registrarse", color = Color.Black, fontSize = 20.sp )
            CustomText(text = "ó", color = Color.Black, fontSize = 20.sp )
        }
    }
}

@Composable
fun HeaderLogin(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "close app",
        modifier = modifier.clickable { activity.finish() }
    )
}

/*@Composable
fun RotatingImage(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    durationMillis: Int = 1000 // Duración de la animación en milisegundos
) {
    var rotation by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(durationMillis.toLong())
            rotation += 360f // Giramos la imagen 360 grados
        }
    }

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier.rotate(rotation) // Aplicamos la rotación a la imagen
    )
}*/




