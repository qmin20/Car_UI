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
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.AutoMode
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.WindPower
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.car_ui.model.ClimateState
import com.example.car_ui.ui.theme.CarBorder
import com.example.car_ui.ui.theme.CarCardBackground
import com.example.car_ui.ui.theme.CarIconInactive
import com.example.car_ui.ui.theme.CarSurface
import com.example.car_ui.ui.theme.CarTextPrimary
import com.example.car_ui.ui.theme.CarTextSecondary

@Composable
fun ClimateScreen(
    climateState: ClimateState,
    ambientColor: Color,
    onAdjustDriverTemp: (Float) -> Unit,
    onAdjustPassengerTemp: (Float) -> Unit,
    onSetFanSpeed: (Int) -> Unit,
    onToggleAc: () -> Unit,
    onToggleAuto: () -> Unit,
    onToggleSync: () -> Unit,
    onToggleRecirculation: () -> Unit,
    onToggleFrontDefrost: () -> Unit,
    onToggleRearDefrost: () -> Unit,
    onToggleDriverSeatHeat: () -> Unit,
    onTogglePassengerSeatHeat: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Left Column: Driver Dial & Passenger Dial Card
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
                    Icon(Icons.Default.Thermostat, "Climate", tint = ambientColor, modifier = Modifier.size(26.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "DUAL-ZONE CLIMATE CONTROL",
                        color = ambientColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 1.sp
                    )
                }

                // Driver & Passenger Dial Display Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Driver Temp Dial
                    ClimateTempDial(
                        label = "DRIVER",
                        temp = climateState.driverTemp,
                        seatHeatLevel = climateState.driverSeatHeatLevel,
                        accentColor = ambientColor,
                        onDecrease = { onAdjustDriverTemp(-0.5f) },
                        onIncrease = { onAdjustDriverTemp(0.5f) },
                        onToggleSeatHeat = onToggleDriverSeatHeat
                    )

                    Box(modifier = Modifier.width(1.dp).height(160.dp).background(CarBorder))

                    // Passenger Temp Dial
                    ClimateTempDial(
                        label = "PASSENGER",
                        temp = climateState.passengerTemp,
                        seatHeatLevel = climateState.passengerSeatHeatLevel,
                        accentColor = ambientColor,
                        onDecrease = { onAdjustPassengerTemp(-0.5f) },
                        onIncrease = { onAdjustPassengerTemp(0.5f) },
                        onToggleSeatHeat = onTogglePassengerSeatHeat
                    )
                }

                // Quick Mode Toggles Row (A/C, AUTO, SYNC, RECIRCULATION)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ClimateTogglePill("A/C", climateState.isAcOn, ambientColor, onToggleAc)
                    ClimateTogglePill("AUTO", climateState.isAutoOn, ambientColor, onToggleAuto)
                    ClimateTogglePill("SYNC", climateState.isSyncOn, ambientColor, onToggleSync)
                    ClimateTogglePill("RECIRC", climateState.isRecirculationOn, ambientColor, onToggleRecirculation)
                }
            }
        }

        // Right Column: Fan Speed & Defrost Controls
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
                    Icon(Icons.Default.WindPower, "Fan", tint = ambientColor, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "FAN SPEED & AIRFLOW",
                        color = ambientColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 1.sp
                    )
                }

                // Fan Speed Level Selector
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("Fan Speed Level: ${climateState.fanSpeed}", color = CarTextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        (1..7).forEach { speed ->
                            val isSelected = speed <= climateState.fanSpeed

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(52.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (isSelected) ambientColor else CarSurface)
                                    .border(1.dp, if (isSelected) ambientColor else CarBorder, RoundedCornerShape(12.dp))
                                    .clickable { onSetFanSpeed(speed) },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "$speed",
                                    color = if (isSelected) Color.Black else CarTextSecondary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }

                // Defrost Controls
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("Windshield & Mirror Defrost", color = CarTextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        DefrostButton(
                            label = "FRONT DEFROST",
                            isActive = climateState.isFrontDefrostOn,
                            accentColor = ambientColor,
                            onClick = onToggleFrontDefrost,
                            modifier = Modifier.weight(1f)
                        )
                        DefrostButton(
                            label = "REAR DEFROST",
                            isActive = climateState.isRearDefrostOn,
                            accentColor = ambientColor,
                            onClick = onToggleRearDefrost,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ClimateTempDial(
    label: String,
    temp: Float,
    seatHeatLevel: Int,
    accentColor: Color,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit,
    onToggleSeatHeat: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, color = CarTextSecondary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        // Large Temperature Circle Card
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(CarSurface)
                .border(3.dp, accentColor, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${"%.1f".format(temp)}°",
                    color = CarTextPrimary,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text("CELSIUS", color = CarTextSecondary, fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // + / - and Seat Heat Buttons
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(CarSurface)
                    .border(1.dp, CarBorder, CircleShape)
                    .clickable { onDecrease() },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Remove, "-", tint = CarTextPrimary, modifier = Modifier.size(22.dp))
            }

            // Seat Heat Pill
            Box(
                modifier = Modifier
                    .height(44.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(if (seatHeatLevel > 0) Color(0xFFFF5252).copy(alpha = 0.25f) else CarSurface)
                    .border(1.dp, if (seatHeatLevel > 0) Color(0xFFFF5252) else CarBorder, RoundedCornerShape(22.dp))
                    .clickable { onToggleSeatHeat() }
                    .padding(horizontal = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocalFireDepartment, "Seat Heat", tint = if (seatHeatLevel > 0) Color(0xFFFF5252) else CarIconInactive, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (seatHeatLevel > 0) "HEAT $seatHeatLevel" else "HEAT OFF",
                        color = if (seatHeatLevel > 0) Color(0xFFFF5252) else CarTextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(CarSurface)
                    .border(1.dp, CarBorder, CircleShape)
                    .clickable { onIncrease() },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Add, "+", tint = CarTextPrimary, modifier = Modifier.size(22.dp))
            }
        }
    }
}

@Composable
private fun ClimateTogglePill(
    label: String,
    isActive: Boolean,
    accentColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(46.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(if (isActive) accentColor else CarSurface)
            .border(1.dp, if (isActive) accentColor else CarBorder, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(horizontal = 18.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = if (isActive) Color.Black else CarTextSecondary,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        )
    }
}

@Composable
private fun DefrostButton(
    label: String,
    isActive: Boolean,
    accentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(56.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(if (isActive) accentColor.copy(alpha = 0.25f) else CarSurface)
            .border(1.dp, if (isActive) accentColor else CarBorder, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Air, label, tint = if (isActive) accentColor else CarIconInactive, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(label, color = if (isActive) accentColor else CarTextSecondary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}
