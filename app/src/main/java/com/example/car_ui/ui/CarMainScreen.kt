package com.example.car_ui.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.car_ui.model.NavScreen
import com.example.car_ui.ui.components.NavigationDock
import com.example.car_ui.ui.components.TopStatusBar
import com.example.car_ui.ui.screens.ClimateScreen
import com.example.car_ui.ui.screens.HomeScreen
import com.example.car_ui.ui.screens.MediaScreen
import com.example.car_ui.ui.screens.NavigationScreen
import com.example.car_ui.ui.screens.PhoneScreen
import com.example.car_ui.ui.screens.SettingsScreen
import com.example.car_ui.ui.screens.VehicleScreen
import com.example.car_ui.ui.theme.CarBackground
import com.example.car_ui.ui.theme.Car_UITheme
import com.example.car_ui.viewmodel.CarUiViewModel

@Composable
fun CarMainScreen(
    viewModel: CarUiViewModel = viewModel()
) {
    val activeScreen by viewModel.activeScreen.collectAsState()
    val mediaState by viewModel.mediaState.collectAsState()
    val climateState by viewModel.climateState.collectAsState()
    val vehicleState by viewModel.vehicleState.collectAsState()
    val settingsState by viewModel.settingsState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CarBackground)
    ) {
        // Top Status Bar
        TopStatusBar(
            vehicleState = vehicleState,
            onToggleLock = { viewModel.toggleDoorsLock() },
            onCycleDriveMode = { viewModel.cycleDriveMode() },
            ambientColor = settingsState.ambientColor
        )

        // Center Content Screen Area
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            Crossfade(targetState = activeScreen, label = "ScreenTransition") { screen ->
                when (screen) {
                    NavScreen.HOME -> HomeScreen(
                        mediaState = mediaState,
                        climateState = climateState,
                        vehicleState = vehicleState,
                        ambientColor = settingsState.ambientColor,
                        onNavigate = { viewModel.selectScreen(it) },
                        onTogglePlayPause = { viewModel.togglePlayPause() },
                        onNextTrack = { viewModel.nextTrack() },
                        onPreviousTrack = { viewModel.previousTrack() },
                        onCycleDriveMode = { viewModel.cycleDriveMode() }
                    )

                    NavScreen.MEDIA -> MediaScreen(
                        mediaState = mediaState,
                        ambientColor = settingsState.ambientColor,
                        onTogglePlayPause = { viewModel.togglePlayPause() },
                        onNextTrack = { viewModel.nextTrack() },
                        onPreviousTrack = { viewModel.previousTrack() },
                        onSetVolume = { viewModel.setVolume(it) },
                        onPlayTrackAt = { viewModel.playTrackAt(it) }
                    )

                    NavScreen.NAVIGATION -> NavigationScreen(
                        ambientColor = settingsState.ambientColor
                    )

                    NavScreen.CLIMATE -> ClimateScreen(
                        climateState = climateState,
                        ambientColor = settingsState.ambientColor,
                        onAdjustDriverTemp = { viewModel.adjustDriverTemp(it) },
                        onAdjustPassengerTemp = { viewModel.adjustPassengerTemp(it) },
                        onSetFanSpeed = { viewModel.setFanSpeed(it) },
                        onToggleAc = { viewModel.toggleAc() },
                        onToggleAuto = { viewModel.toggleAuto() },
                        onToggleSync = { viewModel.toggleSync() },
                        onToggleRecirculation = { viewModel.toggleRecirculation() },
                        onToggleFrontDefrost = { viewModel.toggleFrontDefrost() },
                        onToggleRearDefrost = { viewModel.toggleRearDefrost() },
                        onToggleDriverSeatHeat = { viewModel.toggleDriverSeatHeat() },
                        onTogglePassengerSeatHeat = { viewModel.togglePassengerSeatHeat() }
                    )

                    NavScreen.VEHICLE -> VehicleScreen(
                        vehicleState = vehicleState,
                        ambientColor = settingsState.ambientColor,
                        onCycleDriveMode = { viewModel.cycleDriveMode() },
                        onToggleHeadlights = { viewModel.toggleHeadlights() },
                        onToggleDoorsLock = { viewModel.toggleDoorsLock() }
                    )

                    NavScreen.PHONE -> PhoneScreen(
                        ambientColor = settingsState.ambientColor
                    )

                    NavScreen.SETTINGS -> SettingsScreen(
                        settingsState = settingsState,
                        onSetAmbientColor = { viewModel.setAmbientColor(it) },
                        onSetBrightness = { viewModel.setScreenBrightness(it) }
                    )
                }
            }
        }

        // Bottom Navigation Dock
        NavigationDock(
            activeScreen = activeScreen,
            climateState = climateState,
            ambientColor = settingsState.ambientColor,
            onSelectScreen = { viewModel.selectScreen(it) },
            onAdjustDriverTemp = { viewModel.adjustDriverTemp(it) },
            onAdjustPassengerTemp = { viewModel.adjustPassengerTemp(it) },
            onToggleDriverSeatHeat = { viewModel.toggleDriverSeatHeat() },
            onTogglePassengerSeatHeat = { viewModel.togglePassengerSeatHeat() }
        )
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true, widthDp = 1024, heightDp = 600)
@Composable
fun CarMainScreenPreview() {
    Car_UITheme {
        CarMainScreen()
    }
}

