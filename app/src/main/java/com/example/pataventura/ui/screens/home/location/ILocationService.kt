package com.example.pataventura.ui.screens.home.location

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface ILocationService {
    fun requestLocationUpdates(): Flow<LatLng?>
    fun requestCurrentLocation(): Flow<LatLng?>
}