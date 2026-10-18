package com.example.car_ui.ui.screens

import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CompassCalibration
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.EvStation
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.car_ui.ui.theme.CarBorder
import com.example.car_ui.ui.theme.CarCardBackground
import com.example.car_ui.ui.theme.CarSurface
import com.example.car_ui.ui.theme.CarTextPrimary
import com.example.car_ui.ui.theme.CarTextSecondary

@Composable
fun NavigationScreen(
    ambientColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFF0F121C))
            .border(1.dp, CarBorder, RoundedCornerShape(24.dp))
    ) {
        // Map Canvas background
        Canvas(modifier = Modifier.fillMaxSize()) {
            val gridColor = Color(0xFF1E2336)
            val strokeWidth = 2f

            // Grid lines simulation
            val step = 100f
            var x = 0f
            while (x < size.width) {
                drawLine(gridColor, Offset(x, 0f), Offset(x, size.height), strokeWidth)
                x += step
            }
            var y = 0f
            while (y < size.height) {
                drawLine(gridColor, Offset(0f, y), Offset(size.width, y), strokeWidth)
                y += step
            }

            // Simulated Route Path
            val routePath = Path().apply {
                moveTo(size.width * 0.5f, size.height * 0.9f)
                lineTo(size.width * 0.5f, size.height * 0.5f)
                lineTo(size.width * 0.75f, size.height * 0.35f)
                lineTo(size.width * 0.75f, size.height * 0.15f)
            }
            drawPath(
                path = routePath,
                color = ambientColor,
                style = Stroke(width = 12f)
            )
        }

        // Overlay Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Row: Search Bar & Quick Destination Chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Search Input Box
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .height(54.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(CarCardBackground.copy(alpha = 0.9f))
                        .border(1.dp, CarBorder, RoundedCornerShape(16.dp))
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Search, "Search", tint = ambientColor, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Where to? (e.g. University, Charging, Home)",
                        color = CarTextSecondary,
                        fontSize = 15.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(Icons.Default.Mic, "Voice Search", tint = CarTextPrimary, modifier = Modifier.size(22.dp))
                }

                // Quick Chips Row
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    QuickNavChip(Icons.Default.Home, "Home", ambientColor)
                    QuickNavChip(Icons.Default.Work, "Work", ambientColor)
                    QuickNavChip(Icons.Default.EvStation, "EV Charge", ambientColor)
                    QuickNavChip(Icons.Default.Coffee, "Coffee", ambientColor)
                }
            }

            // Middle & Bottom Row: Turn Card & Map Controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                // Turn-by-Turn Instruction Card
                Box(
                    modifier = Modifier
                        .width(360.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(CarCardBackground.copy(alpha = 0.95f))
                        .border(1.dp, ambientColor, RoundedCornerShape(20.dp))
                        .padding(20.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(ambientColor),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Navigation, "Turn Right", tint = Color.Black, modifier = Modifier.size(28.dp))
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column {
                                Text("IN 300 METERS", color = ambientColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                Text("Turn Right onto Grand Ave", color = CarTextPrimary, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(CarBorder))
                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text("ETA", color = CarTextSecondary, fontSize = 11.sp)
                                Text("10:48 AM", color = CarTextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            }
                            Column {
                                Text("DURATION", color = CarTextSecondary, fontSize = 11.sp)
                                Text("18 min", color = ambientColor, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            }
                            Column {
                                Text("DISTANCE", color = CarTextSecondary, fontSize = 11.sp)
                                Text("12.4 km", color = CarTextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                // Map Control Buttons (Compass, Center, Zoom +, Zoom -)
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    MapControlButton(Icons.Default.CompassCalibration, "Compass", ambientColor)
                    MapControlButton(Icons.Default.DirectionsCar, "Recenter", ambientColor)
                    MapControlButton(Icons.Default.Add, "Zoom In", ambientColor)
                    MapControlButton(Icons.Default.Remove, "Zoom Out", ambientColor)
                }
            }
        }
    }
}

@Composable
private fun QuickNavChip(icon: ImageVector, label: String, accentColor: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(54.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(CarCardBackground.copy(alpha = 0.9f))
            .border(1.dp, CarBorder, RoundedCornerShape(16.dp))
            .clickable { }
            .padding(horizontal = 14.dp)
    ) {
        Icon(icon, label, tint = accentColor, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text(label, color = CarTextPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun MapControlButton(icon: ImageVector, description: String, accentColor: Color) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(CarCardBackground.copy(alpha = 0.95f))
            .border(1.dp, CarBorder, CircleShape)
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        Icon(icon, description, tint = CarTextPrimary, modifier = Modifier.size(24.dp))
    }
}
