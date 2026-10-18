package com.example.car_ui.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.car_ui.model.ClimateState
import com.example.car_ui.model.DriveMode
import com.example.car_ui.model.MediaState
import com.example.car_ui.model.MediaTrack
import com.example.car_ui.model.NavScreen
import com.example.car_ui.model.SettingsState
import com.example.car_ui.model.VehicleState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CarUiViewModel : ViewModel() {

    private val _activeScreen = MutableStateFlow(NavScreen.HOME)
    val activeScreen: StateFlow<NavScreen> = _activeScreen.asStateFlow()

    private val samplePlaylist = listOf(
        MediaTrack("1", "Midnight Cruise", "Synthwave Collective", "Neon Horizons", 230),
        MediaTrack("2", "Electric Highway", "Cyber Drive", "Future Roads", 195),
        MediaTrack("3", "Night Runner", "Kavinsky Fan", "Outrun", 215),
        MediaTrack("4", "Sunset Horizon", "Chillwave Beats", "Lo-Fi Odyssey", 180)
    )

    private val _mediaState = MutableStateFlow(
        MediaState(
            isPlaying = true,
            currentTrackIndex = 0,
            playlist = samplePlaylist,
            progressSeconds = 65,
            volume = 0.7f
        )
    )
    val mediaState: StateFlow<MediaState> = _mediaState.asStateFlow()

    private val _climateState = MutableStateFlow(ClimateState())
    val climateState: StateFlow<ClimateState> = _climateState.asStateFlow()

    private val _vehicleState = MutableStateFlow(VehicleState())
    val vehicleState: StateFlow<VehicleState> = _vehicleState.asStateFlow()

    private val _settingsState = MutableStateFlow(SettingsState())
    val settingsState: StateFlow<SettingsState> = _settingsState.asStateFlow()

    // Screen Navigation
    fun selectScreen(screen: NavScreen) {
        _activeScreen.value = screen
    }

    // Media Actions
    fun togglePlayPause() {
        _mediaState.update { it.copy(isPlaying = !it.isPlaying) }
    }

    fun nextTrack() {
        _mediaState.update { state ->
            val nextIdx = (state.currentTrackIndex + 1) % state.playlist.size
            state.copy(currentTrackIndex = nextIdx, progressSeconds = 0)
        }
    }

    fun previousTrack() {
        _mediaState.update { state ->
            val prevIdx = if (state.currentTrackIndex > 0) state.currentTrackIndex - 1 else state.playlist.size - 1
            state.copy(currentTrackIndex = prevIdx, progressSeconds = 0)
        }
    }

    fun setVolume(volume: Float) {
        _mediaState.update { it.copy(volume = volume.coerceIn(0f, 1f)) }
    }

    fun playTrackAt(index: Int) {
        if (index in _mediaState.value.playlist.indices) {
            _mediaState.update { it.copy(currentTrackIndex = index, isPlaying = true, progressSeconds = 0) }
        }
    }

    // Climate Actions
    fun adjustDriverTemp(delta: Float) {
        _climateState.update { state ->
            val newTemp = (state.driverTemp + delta).coerceIn(16f, 30f)
            if (state.isSyncOn) {
                state.copy(driverTemp = newTemp, passengerTemp = newTemp)
            } else {
                state.copy(driverTemp = newTemp)
            }
        }
    }

    fun adjustPassengerTemp(delta: Float) {
        _climateState.update { state ->
            val newTemp = (state.passengerTemp + delta).coerceIn(16f, 30f)
            if (state.isSyncOn) {
                state.copy(driverTemp = newTemp, passengerTemp = newTemp)
            } else {
                state.copy(passengerTemp = newTemp)
            }
        }
    }

    fun setFanSpeed(speed: Int) {
        _climateState.update { it.copy(fanSpeed = speed.coerceIn(1, 7)) }
    }

    fun toggleAc() {
        _climateState.update { it.copy(isAcOn = !it.isAcOn) }
    }

    fun toggleAuto() {
        _climateState.update { it.copy(isAutoOn = !it.isAutoOn) }
    }

    fun toggleSync() {
        _climateState.update { state ->
            val newSync = !state.isSyncOn
            if (newSync) state.copy(isSyncOn = true, passengerTemp = state.driverTemp)
            else state.copy(isSyncOn = false)
        }
    }

    fun toggleRecirculation() {
        _climateState.update { it.copy(isRecirculationOn = !it.isRecirculationOn) }
    }

    fun toggleFrontDefrost() {
        _climateState.update { it.copy(isFrontDefrostOn = !it.isFrontDefrostOn) }
    }

    fun toggleRearDefrost() {
        _climateState.update { it.copy(isRearDefrostOn = !it.isRearDefrostOn) }
    }

    fun toggleDriverSeatHeat() {
        _climateState.update { it.copy(driverSeatHeatLevel = (it.driverSeatHeatLevel + 1) % 4) }
    }

    fun togglePassengerSeatHeat() {
        _climateState.update { it.copy(passengerSeatHeatLevel = (it.passengerSeatHeatLevel + 1) % 4) }
    }

    // Vehicle Actions
    fun cycleDriveMode() {
        _vehicleState.update { state ->
            val modes = DriveMode.entries
            val nextMode = modes[(state.driveMode.ordinal + 1) % modes.size]
            state.copy(driveMode = nextMode)
        }
    }

    fun toggleHeadlights() {
        _vehicleState.update { it.copy(isHeadlightsOn = !it.isHeadlightsOn) }
    }

    fun toggleDoorsLock() {
        _vehicleState.update { it.copy(isDoorsLocked = !it.isDoorsLocked) }
    }

    // Settings Actions
    fun setAmbientColor(color: Color) {
        _settingsState.update { it.copy(ambientColor = color) }
    }

    fun setScreenBrightness(brightness: Float) {
        _settingsState.update { it.copy(screenBrightness = brightness.coerceIn(0.1f, 1f)) }
    }
}
