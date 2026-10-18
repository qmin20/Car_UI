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
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.car_ui.model.DriveMode
import com.example.car_ui.model.VehicleState
import com.example.car_ui.ui.theme.CarBorder
import com.example.car_ui.ui.theme.CarCardBackground
import com.example.car_ui.ui.theme.CarSurface
import com.example.car_ui.ui.theme.CarTextPrimary
import com.example.car_ui.ui.theme.CarTextSecondary

@Composable
fun VehicleScreen(
    vehicleState: VehicleState,
    ambientColor: Color,
    onCycleDriveMode: () -> Unit,
    onToggleHeadlights: () -> Unit,
    onToggleDoorsLock: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Left Area: Instrument Cluster & Speedometer Gauge
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
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Speed, "Speed", tint = ambientColor, modifier = Modifier.size(26.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "INSTRUMENT CLUSTER & SPEED",
                        color = ambientColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 1.sp
                    )
                }

                // Speedometer Gauge Display
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .background(CarSurface)
                        .border(4.dp, Color(vehicleState.driveMode.colorHex), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${vehicleState.speedKmh}",
                            color = CarTextPrimary,
                            fontSize = 64.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = "KM / H",
                            color = CarTextSecondary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        )
                    }
                }

                // Drive Mode Selection Pill Row
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("SELECTED DRIVE MODE", color = CarTextSecondary, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        DriveMode.entries.forEach { mode ->
                            val isSelected = mode == vehicleState.driveMode
                            val modeColor = Color(mode.colorHex)

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(if (isSelected) modeColor else CarSurface)
                                    .border(1.dp, modeColor, RoundedCornerShape(14.dp))
                                    .clickable { onCycleDriveMode() }
                                    .padding(horizontal = 16.dp, vertical = 10.dp)
                            ) {
                                Text(
                                    text = mode.label,
                                    color = if (isSelected) Color.Black else modeColor,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        // Right Area: Battery, TPMS Tires & Quick Controls
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
                // Battery & Range Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(CarSurface)
                        .border(1.dp, CarBorder, RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.BatteryChargingFull, "Battery", tint = ambientColor, modifier = Modifier.size(32.dp))
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text("BATTERY LEVEL", color = CarTextSecondary, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                Text("${vehicleState.batteryPercent}% Charged", color = CarTextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text("EST. RANGE", color = CarTextSecondary, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            Text("${vehicleState.estimatedRangeKm} km", color = ambientColor, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                // Tire Pressure Monitoring System (TPMS)
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("TIRE PRESSURE (TPMS)", color = CarTextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(12.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(CarSurface)
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Left Tires
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("FL: ${vehicleState.tirePressureFrontLeftPsi} PSI", color = CarTextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(20.dp))
                                Text("RL: ${vehicleState.tirePressureRearLeftPsi} PSI", color = CarTextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }

                            // Car Graphic in middle
                            Icon(Icons.Default.DirectionsCar, "Car View", tint = ambientColor, modifier = Modifier.size(64.dp))

                            // Right Tires
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("FR: ${vehicleState.tirePressureFrontRightPsi} PSI", color = CarTextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(20.dp))
                                Text("RR: ${vehicleState.tirePressureRearRightPsi} PSI", color = CarTextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                // Headlight & Lock Controls
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (vehicleState.isHeadlightsOn) ambientColor else CarSurface)
                            .border(1.dp, if (vehicleState.isHeadlightsOn) ambientColor else CarBorder, RoundedCornerShape(16.dp))
                            .clickable { onToggleHeadlights() },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Lightbulb, "Headlights", tint = if (vehicleState.isHeadlightsOn) Color.Black else CarTextSecondary, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                if (vehicleState.isHeadlightsOn) "LIGHTS ON" else "LIGHTS OFF",
                                color = if (vehicleState.isHeadlightsOn) Color.Black else CarTextSecondary,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (vehicleState.isDoorsLocked) Color(0xFF00E676).copy(alpha = 0.2f) else Color(0xFFFF5252).copy(alpha = 0.2f))
                            .border(1.dp, if (vehicleState.isDoorsLocked) Color(0xFF00E676) else Color(0xFFFF5252), RoundedCornerShape(16.dp))
                            .clickable { onToggleDoorsLock() },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (vehicleState.isDoorsLocked) Icons.Default.Lock else Icons.Default.LockOpen,
                                contentDescription = "Doors Lock",
                                tint = if (vehicleState.isDoorsLocked) Color(0xFF00E676) else Color(0xFFFF5252),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                if (vehicleState.isDoorsLocked) "LOCKED" else "UNLOCKED",
                                color = if (vehicleState.isDoorsLocked) Color(0xFF00E676) else Color(0xFFFF5252),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
