package com.example.car_ui.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.car_ui.model.ClimateState
import com.example.car_ui.model.NavScreen
import com.example.car_ui.ui.theme.CarBorder
import com.example.car_ui.ui.theme.CarCardBackground
import com.example.car_ui.ui.theme.CarIconInactive
import com.example.car_ui.ui.theme.CarSurface
import com.example.car_ui.ui.theme.CarTextPrimary
import com.example.car_ui.ui.theme.CarTextSecondary

@Composable
fun NavigationDock(
    activeScreen: NavScreen,
    climateState: ClimateState,
    ambientColor: Color,
    onSelectScreen: (NavScreen) -> Unit,
    onAdjustDriverTemp: (Float) -> Unit,
    onAdjustPassengerTemp: (Float) -> Unit,
    onToggleDriverSeatHeat: () -> Unit,
    onTogglePassengerSeatHeat: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(76.dp)
            .background(CarSurface)
            .border(1.dp, CarBorder)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Driver Quick Temp Control
        QuickTempControl(
            label = "DRIVER",
            temp = climateState.driverTemp,
            seatHeatLevel = climateState.driverSeatHeatLevel,
            accentColor = ambientColor,
            onDecrease = { onAdjustDriverTemp(-0.5f) },
            onIncrease = { onAdjustDriverTemp(0.5f) },
            onToggleSeatHeat = onToggleDriverSeatHeat
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Center Navigation Dock Icons
        Row(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(20.dp))
                .background(CarCardBackground)
                .padding(vertical = 6.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DockNavItem(
                icon = Icons.Default.Home,
                label = "Home",
                isSelected = activeScreen == NavScreen.HOME,
                activeColor = ambientColor,
                onClick = { onSelectScreen(NavScreen.HOME) }
            )
            DockNavItem(
                icon = Icons.Default.MusicNote,
                label = "Media",
                isSelected = activeScreen == NavScreen.MEDIA,
                activeColor = ambientColor,
                onClick = { onSelectScreen(NavScreen.MEDIA) }
            )
            DockNavItem(
                icon = Icons.Default.Navigation,
                label = "Nav",
                isSelected = activeScreen == NavScreen.NAVIGATION,
                activeColor = ambientColor,
                onClick = { onSelectScreen(NavScreen.NAVIGATION) }
            )
            DockNavItem(
                icon = Icons.Default.Thermostat,
                label = "Climate",
                isSelected = activeScreen == NavScreen.CLIMATE,
                activeColor = ambientColor,
                onClick = { onSelectScreen(NavScreen.CLIMATE) }
            )
            DockNavItem(
                icon = Icons.Default.DirectionsCar,
                label = "Vehicle",
                isSelected = activeScreen == NavScreen.VEHICLE,
                activeColor = ambientColor,
                onClick = { onSelectScreen(NavScreen.VEHICLE) }
            )
            DockNavItem(
                icon = Icons.Default.Phone,
                label = "Phone",
                isSelected = activeScreen == NavScreen.PHONE,
                activeColor = ambientColor,
                onClick = { onSelectScreen(NavScreen.PHONE) }
            )
            DockNavItem(
                icon = Icons.Default.Settings,
                label = "Settings",
                isSelected = activeScreen == NavScreen.SETTINGS,
                activeColor = ambientColor,
                onClick = { onSelectScreen(NavScreen.SETTINGS) }
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Passenger Quick Temp Control
        QuickTempControl(
            label = "PASSENGER",
            temp = climateState.passengerTemp,
            seatHeatLevel = climateState.passengerSeatHeatLevel,
            accentColor = ambientColor,
            onDecrease = { onAdjustPassengerTemp(-0.5f) },
            onIncrease = { onAdjustPassengerTemp(0.5f) },
            onToggleSeatHeat = onTogglePassengerSeatHeat
        )
    }
}

@Composable
private fun DockNavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    activeColor: Color,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) activeColor.copy(alpha = 0.15f) else Color.Transparent,
        label = "bg"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(backgroundColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        FrutigerAeroIcon(
            imageVector = icon,
            contentDescription = label,
            iconSize = 20.dp,
            containerSize = 36.dp,
            tint = if (isSelected) Color.White else CarTextSecondary,
            glossColor = if (isSelected) activeColor else Color(0xFF00E5FF),
            isSelected = isSelected
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            color = if (isSelected) CarTextPrimary else CarTextSecondary,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )
    }
}

@Composable
private fun QuickTempControl(
    label: String,
    temp: Float,
    seatHeatLevel: Int,
    accentColor: Color,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit,
    onToggleSeatHeat: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(CarCardBackground)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        // Seat Heat Indicator with Frutiger Aero Gloss
        Box(
            modifier = Modifier.clickable { onToggleSeatHeat() },
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                FrutigerAeroIcon(
                    imageVector = Icons.Default.AcUnit,
                    contentDescription = "Seat Heat",
                    iconSize = 16.dp,
                    containerSize = 34.dp,
                    tint = if (seatHeatLevel > 0) Color.White else CarIconInactive,
                    glossColor = if (seatHeatLevel > 0) Color(0xFFFF5252) else Color(0xFF00E5FF),
                    isSelected = seatHeatLevel > 0
                )
                if (seatHeatLevel > 0) {
                    Text(
                        text = "•".repeat(seatHeatLevel),
                        color = Color(0xFFFF5252),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 8.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Decrease Temp Button
        FrutigerAeroIcon(
            imageVector = Icons.Default.Remove,
            contentDescription = "Decrease Temp",
            iconSize = 16.dp,
            containerSize = 32.dp,
            tint = CarTextPrimary,
            glossColor = accentColor,
            onClick = onDecrease
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Temperature display
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "${"%.1f".format(temp)}°",
                color = CarTextPrimary,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = label,
                color = CarTextSecondary,
                fontSize = 9.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Increase Temp Button
        FrutigerAeroIcon(
            imageVector = Icons.Default.Add,
            contentDescription = "Increase Temp",
            iconSize = 16.dp,
            containerSize = 32.dp,
            tint = CarTextPrimary,
            glossColor = accentColor,
            onClick = onIncrease
        )
    }
}
