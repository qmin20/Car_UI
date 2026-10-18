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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlaylistPlay
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.VolumeDown
import androidx.compose.material.icons.filled.VolumeUp
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.car_ui.model.MediaState
import com.example.car_ui.ui.theme.CarBorder
import com.example.car_ui.ui.theme.CarCardBackground
import com.example.car_ui.ui.theme.CarSurface
import com.example.car_ui.ui.theme.CarTextPrimary
import com.example.car_ui.ui.theme.CarTextSecondary

@Composable
fun MediaScreen(
    mediaState: MediaState,
    ambientColor: Color,
    onTogglePlayPause: () -> Unit,
    onNextTrack: () -> Unit,
    onPreviousTrack: () -> Unit,
    onSetVolume: (Float) -> Unit,
    onPlayTrackAt: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentTrack = mediaState.currentTrack

    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Left Area: Now Playing Card & Controls
        Box(
            modifier = Modifier
                .weight(1.3f)
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
                // Album Art Card
                Box(
                    modifier = Modifier
                        .size(180.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(ambientColor.copy(alpha = 0.2f))
                        .border(2.dp, ambientColor, RoundedCornerShape(24.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.MusicNote,
                        contentDescription = "Album Art",
                        tint = ambientColor,
                        modifier = Modifier.size(90.dp)
                    )
                }

                // Track Info
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = currentTrack?.title ?: "Select a Track",
                        color = CarTextPrimary,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${currentTrack?.artist ?: "Unknown"} • ${currentTrack?.album ?: "Unknown Album"}",
                        color = CarTextSecondary,
                        fontSize = 15.sp,
                        maxLines = 1
                    )
                }

                // Audio Progress Bar
                Column(modifier = Modifier.fillMaxWidth()) {
                    val duration = currentTrack?.durationSeconds ?: 1
                    val progressFloat = (mediaState.progressSeconds.toFloat() / duration.toFloat()).coerceIn(0f, 1f)

                    Slider(
                        value = progressFloat,
                        onValueChange = {},
                        valueRange = 0f..1f,
                        colors = SliderDefaults.colors(
                            thumbColor = ambientColor,
                            activeTrackColor = ambientColor,
                            inactiveTrackColor = CarSurface
                        )
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val currentMin = mediaState.progressSeconds / 60
                        val currentSec = mediaState.progressSeconds % 60
                        val durMin = duration / 60
                        val durSec = duration % 60

                        Text(
                            text = "%d:%02d".format(currentMin, currentSec),
                            color = CarTextSecondary,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "%d:%02d".format(durMin, durSec),
                            color = CarTextSecondary,
                            fontSize = 12.sp
                        )
                    }
                }

                // Main Playback Controls
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(CarSurface)
                            .clickable { onPreviousTrack() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.SkipPrevious, "Previous", tint = CarTextPrimary, modifier = Modifier.size(28.dp))
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    Box(
                        modifier = Modifier
                            .size(68.dp)
                            .clip(CircleShape)
                            .background(ambientColor)
                            .clickable { onTogglePlayPause() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (mediaState.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = "Play/Pause",
                            tint = Color.Black,
                            modifier = Modifier.size(38.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(CarSurface)
                            .clickable { onNextTrack() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.SkipNext, "Next", tint = CarTextPrimary, modifier = Modifier.size(28.dp))
                    }
                }

                // Volume Bar
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(CarSurface)
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    Icon(Icons.Default.VolumeDown, "Volume Down", tint = CarTextSecondary, modifier = Modifier.size(20.dp))
                    Slider(
                        value = mediaState.volume,
                        onValueChange = onSetVolume,
                        valueRange = 0f..1f,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        colors = SliderDefaults.colors(
                            thumbColor = ambientColor,
                            activeTrackColor = ambientColor,
                            inactiveTrackColor = CarBorder
                        )
                    )
                    Icon(Icons.Default.VolumeUp, "Volume Up", tint = CarTextSecondary, modifier = Modifier.size(20.dp))
                }
            }
        }

        // Right Area: Playlist Items Card
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
                    Icon(
                        imageVector = Icons.Default.PlaylistPlay,
                        contentDescription = "Playlist",
                        tint = ambientColor,
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "UP NEXT / PLAYLIST",
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
                    itemsIndexed(mediaState.playlist) { idx, track ->
                        val isCurrent = idx == mediaState.currentTrackIndex

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(14.dp))
                                .background(if (isCurrent) ambientColor.copy(alpha = 0.2f) else CarSurface)
                                .border(
                                    1.dp,
                                    if (isCurrent) ambientColor else CarBorder,
                                    RoundedCornerShape(14.dp)
                                )
                                .clickable { onPlayTrackAt(idx) }
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (isCurrent) ambientColor else CarCardBackground),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = if (isCurrent && mediaState.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                        contentDescription = "Play",
                                        tint = if (isCurrent) Color.Black else CarTextSecondary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Column {
                                    Text(
                                        text = track.title,
                                        color = if (isCurrent) ambientColor else CarTextPrimary,
                                        fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Medium,
                                        fontSize = 15.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = track.artist,
                                        color = CarTextSecondary,
                                        fontSize = 12.sp,
                                        maxLines = 1
                                    )
                                }
                            }

                            val min = track.durationSeconds / 60
                            val sec = track.durationSeconds % 60
                            Text(
                                text = "%d:%02d".format(min, sec),
                                color = CarTextSecondary,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
