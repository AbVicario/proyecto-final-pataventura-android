package com.example.pataventura

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.pataventura.core.navigations.NavigationHost
import com.example.pataventura.ui.screens.calendario.CalendarioViewModel
import com.example.pataventura.ui.screens.contratacion.ContratacionViewModel
import com.example.pataventura.ui.screens.historia_mascota.HistorialCuidadorViewModel
import com.example.pataventura.ui.screens.historia_mascota.HistorialMascotaViewModel
import com.example.pataventura.ui.screens.home.HomeViewModel
import com.example.pataventura.ui.screens.login.LoginViewModel
import com.example.pataventura.ui.screens.loginCliente.LoginClienteViewModel
import com.example.pataventura.ui.screens.perfil_trabajador.PerfilTrabajadorViewModel
import com.example.pataventura.ui.screens.registoMascota.RegistroMascotaViewModel
import com.example.pataventura.ui.screens.registro.RegistroServicioViewModel
import com.example.pataventura.ui.screens.registro.RegistroViewModel
import com.example.pataventura.ui.theme.PataVenturaTheme
import dagger.hilt.android.AndroidEntryPoint
import com.example.pataventura.ui.screens.mascotas.MascotasViewModel
import com.example.pataventura.ui.screens.perfilTutor.PerfilTutorViewModel
import com.example.pataventura.ui.screens.perfil_mascota.PerfilMascotaViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginClienteViewModel: LoginClienteViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private val registroViewModel: RegistroViewModel by viewModels()
    private val registroMascotaViewModel: RegistroMascotaViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private val registroServicioViewModel: RegistroServicioViewModel by viewModels()
    private val contratacionViewModel: ContratacionViewModel by viewModels()
    private val perfilTrabajadorViewModel: PerfilTrabajadorViewModel by viewModels()
    private val calendarioViewModel: CalendarioViewModel by viewModels()
    private val historialMascotaViewModel: HistorialMascotaViewModel by viewModels()
    private val historialCuidadorViewModel: HistorialCuidadorViewModel by viewModels()
    private val mascotasViewModel: MascotasViewModel by viewModels()
    private val perfilMascotaViewModel: PerfilMascotaViewModel by viewModels()
    private val perfilTutorViewModel: PerfilTutorViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.S)
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            
            /*val permissionState = rememberMultiplePermissionsState(
                permissions = listOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )*/

            //val viewState by homeViewModel.viewState.collectAsStateWithLifecycle()

            PataVenturaTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationHost(
                        registerViewModel = registroViewModel,
                        registerMascotaViewModel = registroMascotaViewModel,
                        loginViewModel = loginViewModel,
                        loginClienteViewModel = loginClienteViewModel,
                        homeViewModel = homeViewModel,
                        registroServicioViewModel = registroServicioViewModel,
                        contratacionViewModel = contratacionViewModel,
                        perfilTrabajadorViewModel = perfilTrabajadorViewModel,
                        calendarioViewModel = calendarioViewModel,
                        historialMascotaViewModel = historialMascotaViewModel,
                        historialCuidadorViewModel = historialCuidadorViewModel,
                        mascotasViewModel = mascotasViewModel,
                        perfilMascotaViewModel = perfilMascotaViewModel,
                        perfilTutorViewModel = perfilTutorViewModel,
                    )

                    /*LaunchedEffect(!hasLocationPermission()) {
                        permissionState.launchMultiplePermissionRequest()
                    }

                    when {
                        permissionState.allPermissionsGranted -> {
                            LaunchedEffect(Unit) {
                                homeViewModel.handle(PermissionEvent.Granted)
                            }
                        }

                        permissionState.shouldShowRationale -> {
                            RationaleAlert(onDismiss = { }) {
                                permissionState.launchMultiplePermissionRequest()
                            }
                        }

                        !permissionState.allPermissionsGranted && !permissionState.shouldShowRationale -> {
                            LaunchedEffect(Unit) {
                                homeViewModel.handle(PermissionEvent.Revoked)
                            }
                        }
                    }

                    with(viewState) {
                        when (this) {
                            ViewState.Loading -> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }

                            ViewState.RevokedPermissions -> {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(24.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text("We need permissions to use this app")
                                    Button(
                                        onClick = {
                                            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                                        },
                                        enabled = !hasLocationPermission()
                                    ) {
                                        if (hasLocationPermission()) CircularProgressIndicator(
                                            modifier = Modifier.size(14.dp),
                                            color = Color.White
                                        )
                                        else Text("Settings")
                                    }
                                }
                            }

                            is ViewState.Success -> {
                                val currentLoc =
                                    LatLng(
                                        location?.latitude ?: 0.0,
                                        location?.longitude ?: 0.0
                                    )
                                val cameraState = rememberCameraPositionState()

                                LaunchedEffect(key1 = currentLoc) {
                                    cameraState.centerOnLocation(currentLoc)
                                }

                                homeViewModel.setCameraState(cameraState)
                                homeViewModel.setCurrentLoc(currentLoc)

                            }

                            else -> {}
                        }
                    }*/
                }
            }
        }
    }

}


/*private suspend fun CameraPositionState.centerOnLocation(
    location: LatLng
) = animate(
    update = CameraUpdateFactory.newLatLngZoom(
        location,
        15f
    ),
    durationMs = 1500
)*/