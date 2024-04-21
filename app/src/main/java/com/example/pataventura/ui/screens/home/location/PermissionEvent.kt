package com.example.pataventura.ui.screens.home.location

sealed interface PermissionEvent {
    object Granted : PermissionEvent
    object Revoked : PermissionEvent
}