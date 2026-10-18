package com.example.car_ui.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.car_ui.model.VehicleState
import com.example.car_ui.ui.theme.CarBorder
import com.example.car_ui.ui.theme.CarSurface
import com.example.car_ui.ui.theme.CarTextPrimary
import com.example.car_ui.ui.theme.CarTextSecondary
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TopStatusBar(
    vehicleState: VehicleState,
    onToggleLock: () -> Unit,
    onCycleDriveMode: () -> Unit,
    ambientColor: Color,
    modifier: Modifier = Modifier
) {
    var currentTime by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        while (true) {
            val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
            currentTime = formatter.format(Date())
            delay(1000)
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(CarSurface)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Left side: Drive Mode & Vehicle Lock
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Drive Mode Pill
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(vehicleState.driveMode.colorHex).copy(alpha = 0.2f))
                    .border(1.dp, Color(vehicleState.driveMode.colorHex), RoundedCornerShape(12.dp))
                    .clickable { onCycleDriveMode() }
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = vehicleState.driveMode.label,
                    color = Color(vehicleState.driveMode.colorHex),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Lock Toggle Button
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(CarBorder)
                    .clickable { onToggleLock() }
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FrutigerAeroIcon(
                        imageVector = if (vehicleState.isDoorsLocked) Icons.Default.Lock else Icons.Default.LockOpen,
                        contentDescription = "Lock status",
                        iconSize = 14.dp,
                        containerSize = 26.dp,
                        tint = Color.White,
                        glossColor = if (vehicleState.isDoorsLocked) Color(0xFF00E676) else Color(0xFFFF5252),
                        isSelected = vehicleState.isDoorsLocked
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (vehicleState.isDoorsLocked) "LOCKED" else "UNLOCKED",
                        color = CarTextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        // Center: Clock & Outside Temp
        Row(verticalAlignment = Alignment.CenterVertically) {
            FrutigerAeroIcon(
                imageVector = Icons.Default.WbSunny,
                contentDescription = "Weather",
                iconSize = 15.dp,
                containerSize = 28.dp,
                tint = Color.White,
                glossColor = Color(0xFFFFB300)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "${vehicleState.outsideTemperatureC}°C",
                color = CarTextPrimary,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                text = currentTime.ifEmpty { "10:42 AM" },
                color = CarTextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        // Right side: Battery & Connection status
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Battery Level
            Row(verticalAlignment = Alignment.CenterVertically) {
                FrutigerAeroIcon(
                    imageVector = Icons.Default.BatteryChargingFull,
                    contentDescription = "Battery",
                    iconSize = 15.dp,
                    containerSize = 28.dp,
                    tint = Color.White,
                    glossColor = ambientColor
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "${vehicleState.batteryPercent}% (${vehicleState.estimatedRangeKm} km)",
                    color = CarTextPrimary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            FrutigerAeroIcon(
                imageVector = Icons.Default.Bluetooth,
                contentDescription = "Bluetooth",
                iconSize = 14.dp,
                containerSize = 26.dp,
                tint = Color.White,
                glossColor = ambientColor
            )
            Spacer(modifier = Modifier.width(8.dp))
            FrutigerAeroIcon(
                imageVector = Icons.Default.Wifi,
                contentDescription = "Wi-Fi",
                iconSize = 14.dp,
                containerSize = 26.dp,
                tint = Color.White,
                glossColor = Color(0xFF00E5FF)
            )
        }
    }
}
