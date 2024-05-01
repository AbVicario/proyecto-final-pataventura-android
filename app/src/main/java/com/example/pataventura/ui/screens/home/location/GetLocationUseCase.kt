package com.example.pataventura.ui.screens.home.location

import android.os.Build
import androidx.annotation.RequiresApi
import javax.inject.Inject
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

class GetLocationUseCase @Inject constructor(
    private val locationService: ILocationService
) {
    @RequiresApi(Build.VERSION_CODES.S)
    operator fun invoke(): Flow<LatLng?> = locationService.requestLocationUpdates()

}