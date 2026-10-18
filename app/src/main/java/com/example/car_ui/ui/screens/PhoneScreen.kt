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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PhoneInTalk
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.car_ui.ui.theme.CarBorder
import com.example.car_ui.ui.theme.CarCardBackground
import com.example.car_ui.ui.theme.CarSurface
import com.example.car_ui.ui.theme.CarTextPrimary
import com.example.car_ui.ui.theme.CarTextSecondary

data class Contact(val name: String, val number: String)

@Composable
fun PhoneScreen(
    ambientColor: Color,
    modifier: Modifier = Modifier
) {
    var dialNumber by remember { mutableStateOf("") }
    var isInCall by remember { mutableStateOf(false) }

    val sampleContacts = remember {
        listOf(
            Contact("Mom", "+1 (555) 019-2834"),
            Contact("Dad", "+1 (555) 014-9821"),
            Contact("Emergency Assistance", "911 / roadside"),
            Contact("Alex Smith", "+1 (555) 012-3456"),
            Contact("Mechanic Service", "+1 (555) 018-7711")
        )
    }

    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Left Area: Keypad Dialer
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
                // Dial Number Display / In-Call Status
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(CarSurface)
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (isInCall) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.PhoneInTalk, "In Call", tint = Color(0xFF00E676), modifier = Modifier.size(24.dp))
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "CALLING: ${dialNumber.ifEmpty { "Mom" }} (00:14)",
                                color = Color(0xFF00E676),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        }
                    } else {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = dialNumber.ifEmpty { "Enter phone number..." },
                                color = if (dialNumber.isEmpty()) CarTextSecondary else CarTextPrimary,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )

                            if (dialNumber.isNotEmpty()) {
                                Icon(
                                    imageVector = Icons.Default.Backspace,
                                    contentDescription = "Delete",
                                    tint = CarTextSecondary,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable { dialNumber = dialNumber.dropLast(1) }
                                )
                            }
                        }
                    }
                }

                // 3x4 Keypad Grid
                val keypadKeys = listOf(
                    listOf("1", "2", "3"),
                    listOf("4", "5", "6"),
                    listOf("7", "8", "9"),
                    listOf("*", "0", "#")
                )

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    keypadKeys.forEach { row ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            row.forEach { digit ->
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(52.dp)
                                        .clip(RoundedCornerShape(14.dp))
                                        .background(CarSurface)
                                        .border(1.dp, CarBorder, RoundedCornerShape(14.dp))
                                        .clickable { dialNumber += digit },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = digit,
                                        color = CarTextPrimary,
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }

                // Call / End Call Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(56.dp)
                        .clip(CircleShape)
                        .background(if (isInCall) Color(0xFFFF5252) else Color(0xFF00E676))
                        .clickable { isInCall = !isInCall },
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = if (isInCall) Icons.Default.CallEnd else Icons.Default.Phone,
                            contentDescription = "Call Action",
                            tint = Color.Black,
                            modifier = Modifier.size(26.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = if (isInCall) "END CALL" else "START CALL",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }

        // Right Area: Speed Dial Contacts List
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(24.dp))
                .background(CarCardBackground)
                .border(1.dp, CarBorder, RoundedCornerShape(24.dp))
                .padding(20.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Person, "Contacts", tint = ambientColor, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "SPEED DIAL & CONTACTS",
                        color = ambientColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 1.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(sampleContacts) { contact ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(14.dp))
                                .background(CarSurface)
                                .border(1.dp, CarBorder, RoundedCornerShape(14.dp))
                                .clickable {
                                    dialNumber = contact.number
                                    isInCall = true
                                }
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .background(ambientColor.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.Person, "Contact", tint = ambientColor, modifier = Modifier.size(22.dp))
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Column {
                                    Text(contact.name, color = CarTextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                                    Text(contact.number, color = CarTextSecondary, fontSize = 12.sp)
                                }
                            }

                            Icon(Icons.Default.Phone, "Call", tint = Color(0xFF00E676), modifier = Modifier.size(22.dp))
                        }
                    }
                }
            }
        }
    }
}
