package com.example.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// LozAI Brand Colors
val LozAiBg = Color(0xFF070B1A)
val LozAiSurface = Color(0xFF0F172A)
val LozAiSurfaceElevated = Color(0xFF1E293B)
val LozAiCardBorder = Color(0xFF334155)
val LozAiCardBorderGlow = Color(0xFF6366F1).copy(alpha = 0.3f)

val LozAiPurplePrimary = Color(0xFF7C3AED)
val LozAiPurpleLight = Color(0xFFA78BFA)
val LozAiPurpleDark = Color(0xFF5B21B6)
val LozAiCyan = Color(0xFF06B6D4)
val LozAiSky = Color(0xFF38BDF8)
val LozAiBlue = Color(0xFF2563EB)

val LozAiTextPrimary = Color(0xFFF8FAFC)
val LozAiTextSecondary = Color(0xFFCBD5E1)
val LozAiTextMuted = Color(0xFF94A3B8)
val LozAiTextDark = Color(0xFF64748B)

// Status Colors
val StatusPendingText = Color(0xFFFBBF24)
val StatusPendingBg = Color(0xFF451A03)
val StatusPendingBorder = Color(0xFF78350F)

val StatusInProgressText = Color(0xFF38BDF8)
val StatusInProgressBg = Color(0xFF0C4A6E)
val StatusInProgressBorder = Color(0xFF0284C7)

val StatusCompletedText = Color(0xFF34D399)
val StatusCompletedBg = Color(0xFF064E3B)
val StatusCompletedBorder = Color(0xFF059669)

val StatusInactiveText = Color(0xFFF87171)
val StatusInactiveBg = Color(0xFF7F1D1D)
val StatusInactiveBorder = Color(0xFF991B1B)

val PillBadgeBg = Color(0xFF1E1B4B)
val PillBadgeText = Color(0xFFC7D2FE)

// Gradient Brushes
val LozAiGradientBrush = Brush.horizontalGradient(
    colors = listOf(LozAiPurplePrimary, LozAiSky)
)

val LozAiCardGradientBrush = Brush.linearGradient(
    colors = listOf(
        Color(0xFF131D38),
        Color(0xFF0F172A)
    )
)

val LozAiHeroBannerBrush = Brush.linearGradient(
    colors = listOf(
        Color(0xFF2E1065),
        Color(0xFF1E1B4B),
        Color(0xFF0F172A)
    )
)
