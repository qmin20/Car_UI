package com.example.car_ui.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness7
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.car_ui.model.SettingsState
import com.example.car_ui.ui.theme.CarBorder
import com.example.car_ui.ui.theme.CarCardBackground
import com.example.car_ui.ui.theme.CarSurface
import com.example.car_ui.ui.theme.CarTextPrimary
import com.example.car_ui.ui.theme.CarTextSecondary

@Composable
fun SettingsScreen(
    settingsState: SettingsState,
    onSetAmbientColor: (Color) -> Unit,
    onSetBrightness: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val ambientOptions = listOf(
        Color(0xFF00E5FF), // Cyan
        Color(0xFF2979FF), // Electric Blue
        Color(0xFFFFB300), // Amber Gold
        Color(0xFF00E676), // Emerald Green
        Color(0xFFE040FB), // Neon Purple
        Color(0xFFFF5252)  // Ruby Red
    )

    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Left Column: Ambient Lighting & Display Settings
        Box(
            modifier = Modifier
                .weight(1.2f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(24.dp))
                .background(CarCardBackground)
                .border(1.dp, CarBorder, RoundedCornerShape(24.dp))
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Settings, "Settings", tint = settingsState.ambientColor, modifier = Modifier.size(26.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "SYSTEM & DISPLAY PREFERENCES",
                        color = settingsState.ambientColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 1.sp
                    )
                }

                // Ambient LED Color Selector
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.ColorLens, "LED Theme", tint = CarTextPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Ambient LED Theme Color", color = CarTextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ambientOptions.forEach { color ->
                            val isSelected = color == settingsState.ambientColor

                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .border(
                                        if (isSelected) 3.dp else 0.dp,
                                        if (isSelected) Color.White else Color.Transparent,
                                        CircleShape
                                    )
                                    .clickable { onSetAmbientColor(color) }
                            )
                        }
                    }
                }

                // Screen Brightness Control
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Brightness7, "Brightness", tint = CarTextPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Display Brightness: ${(settingsState.screenBrightness * 100).toInt()}%",
                            color = CarTextPrimary,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Slider(
                        value = settingsState.screenBrightness,
                        onValueChange = onSetBrightness,
                        valueRange = 0.1f..1f,
                        colors = SliderDefaults.colors(
                            thumbColor = settingsState.ambientColor,
                            activeTrackColor = settingsState.ambientColor,
                            inactiveTrackColor = CarSurface
                        )
                    )
                }

                // Units Preference Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(CarSurface)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Speed, "Units", tint = CarTextPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Speed Units", color = CarTextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(settingsState.ambientColor)
                                .padding(horizontal = 14.dp, vertical = 6.dp)
                        ) {
                            Text("KM/H", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(CarCardBackground)
                                .padding(horizontal = 14.dp, vertical = 6.dp)
                        ) {
                            Text("MPH", color = CarTextSecondary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }
                }
            }
        }

        // Right Column: About System & Vehicle Info Card
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(24.dp))
                .background(CarCardBackground)
                .border(1.dp, CarBorder, RoundedCornerShape(24.dp))
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Info, "About", tint = settingsState.ambientColor, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "ABOUT VEHICLE & SYSTEM",
                        color = settingsState.ambientColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 1.sp
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(CarSurface)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SystemInfoRow("Project", "Car Infotainment UI")
                    SystemInfoRow("Platform", "Android Automotive / Compose")
                    SystemInfoRow("Vehicle Model", "CyberDrive EV GT")
                    SystemInfoRow("Software Version", "v2.5.0-Release")
                    SystemInfoRow("Build Type", "University Project Demo")
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(settingsState.ambientColor.copy(alpha = 0.15f))
                        .border(1.dp, settingsState.ambientColor, RoundedCornerShape(14.dp))
                        .padding(14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "🚀 All Systems Nominal • Ready to Drive",
                        color = settingsState.ambientColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun SystemInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = CarTextSecondary, fontSize = 13.sp)
        Text(value, color = CarTextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
    }
}
