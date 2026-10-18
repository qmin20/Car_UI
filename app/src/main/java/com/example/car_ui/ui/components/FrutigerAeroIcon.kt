package com.example.car_ui.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Frutiger Aero Glossy Glass Icon Component.
 * Renders icons inside a 3D skeuomorphic glass orb or rounded tile with signature
 * Frutiger Aero curved glass reflections, rich aqua/cyan glass gradients,
 * reflective rim highlights, and soft depth shadows.
 */
@Composable
fun FrutigerAeroIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    iconSize: Dp = 22.dp,
    containerSize: Dp? = null,
    tint: Color = Color.White,
    glossColor: Color = Color(0xFF00E5FF),
    shape: Shape = CircleShape,
    elevation: Dp = 4.dp,
    isSelected: Boolean = false,
    showContainer: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    val actualContainerSize = containerSize ?: (iconSize + 16.dp)

    if (!showContainer) {
        // Standalone icon with subtle Frutiger Aero glossy glow tint
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = tint,
            modifier = modifier.size(iconSize)
        )
        return
    }

    // Base Gloss Gradient: Rich Frutiger Aero top gloss transition
    val backgroundBrush = if (isSelected) {
        Brush.verticalGradient(
            colors = listOf(
                glossColor.copy(alpha = 0.95f),
                glossColor.copy(alpha = 0.70f),
                Color(0xFF004D73).copy(alpha = 0.85f)
            )
        )
    } else {
        Brush.verticalGradient(
            colors = listOf(
                glossColor.copy(alpha = 0.55f),
                glossColor.copy(alpha = 0.25f),
                Color(0xFF0D1B2A).copy(alpha = 0.65f)
            )
        )
    }

    // Border Gloss Gradient: Bright white top rim fading into translucent aqua
    val borderBrush = Brush.verticalGradient(
        colors = listOf(
            Color.White.copy(alpha = if (isSelected) 0.95f else 0.75f),
            glossColor.copy(alpha = 0.5f),
            Color.White.copy(alpha = 0.20f)
        )
    )

    Box(
        modifier = modifier
            .size(actualContainerSize)
            .shadow(elevation, shape, clip = false)
            .clip(shape)
            .background(backgroundBrush)
            .border(1.2.dp, borderBrush, shape)
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClick
                    )
                } else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        // Top Curved Glass Reflection Glare (Signature Frutiger Aero Gloss)
        Box(
            modifier = Modifier
                .fillMaxWidth(0.88f)
                .height(actualContainerSize * 0.42f)
                .align(Alignment.TopCenter)
                .offset(y = 1.dp)
                .clip(RoundedCornerShape(topStart = 100.dp, topEnd = 100.dp, bottomStart = 40.dp, bottomEnd = 40.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.70f),
                            Color.White.copy(alpha = 0.08f)
                        )
                    )
                )
        )

        // Bottom Reflective Ambient Water/Glass Highlight
        Box(
            modifier = Modifier
                .fillMaxWidth(0.72f)
                .height(actualContainerSize * 0.22f)
                .align(Alignment.BottomCenter)
                .offset(y = (-2).dp)
                .clip(CircleShape)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            glossColor.copy(alpha = 0.4f)
                        )
                    )
                )
        )

        // Vector Icon inside
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(iconSize)
        )
    }
}
