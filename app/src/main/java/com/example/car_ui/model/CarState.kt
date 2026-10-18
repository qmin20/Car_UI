package com.example.car_ui.model

import androidx.compose.ui.graphics.Color

enum class NavScreen(val title: String) {
    HOME("Dashboard"),
    MEDIA("Media & Audio"),
    NAVIGATION("Navigation"),
    CLIMATE("Climate Control"),
    VEHICLE("Vehicle Status"),
    PHONE("Phone & Contacts"),
    SETTINGS("Settings")
}

enum class DriveMode(val label: String, val colorHex: Long) {
    ECO("ECO", 0xFF00E676),
    COMFORT("COMFORT", 0xFF2979FF),
    SPORT("SPORT", 0xFFFF5252),
    CUSTOM("CUSTOM", 0xFFFFB300)
}

data class MediaTrack(
    val id: String,
    val title: String,
    val artist: String,
    val album: String,
    val durationSeconds: Int,
    val albumArtRes: Int? = null
)

data class MediaState(
    val isPlaying: Boolean = false,
    val currentTrackIndex: Int = 0,
    val playlist: List<MediaTrack> = emptyList(),
    val progressSeconds: Int = 45,
    val volume: Float = 0.6f
) {
    val currentTrack: MediaTrack?
        get() = playlist.getOrNull(currentTrackIndex)
}

data class ClimateState(
    val driverTemp: Float = 21.5f,
    val passengerTemp: Float = 22.0f,
    val fanSpeed: Int = 3, // 1..7
    val isAcOn: Boolean = true,
    val isAutoOn: Boolean = true,
    val isSyncOn: Boolean = true,
    val isRecirculationOn: Boolean = false,
    val isFrontDefrostOn: Boolean = false,
    val isRearDefrostOn: Boolean = false,
    val driverSeatHeatLevel: Int = 1, // 0..3
    val passengerSeatHeatLevel: Int = 0 // 0..3
)

data class VehicleState(
    val speedKmh: Int = 68,
    val batteryPercent: Int = 82,
    val estimatedRangeKm: Int = 380,
    val driveMode: DriveMode = DriveMode.COMFORT,
    val tirePressureFrontLeftPsi: Float = 35.2f,
    val tirePressureFrontRightPsi: Float = 35.0f,
    val tirePressureRearLeftPsi: Float = 34.8f,
    val tirePressureRearRightPsi: Float = 35.1f,
    val isHeadlightsOn: Boolean = true,
    val isDoorsLocked: Boolean = true,
    val outsideTemperatureC: Int = 22,
    val weatherCondition: String = "Partly Cloudy"
)

data class SettingsState(
    val ambientColor: Color = Color(0xFF00E5FF),
    val screenBrightness: Float = 0.8f,
    val isNightMode: Boolean = true,
    val speedUnit: String = "km/h"
)
