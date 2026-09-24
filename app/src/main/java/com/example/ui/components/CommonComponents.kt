package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.LozAiRepository
import com.example.model.LozAiUser
import com.example.model.TicketStatus
import com.example.model.UserRole
import com.example.ui.theme.LozAiBg
import com.example.ui.theme.LozAiCardBorder
import com.example.ui.theme.LozAiCardBorderGlow
import com.example.ui.theme.LozAiCardGradientBrush
import com.example.ui.theme.LozAiCyan
import com.example.ui.theme.LozAiGradientBrush
import com.example.ui.theme.LozAiHeroBannerBrush
import com.example.ui.theme.LozAiPurpleDark
import com.example.ui.theme.LozAiPurpleLight
import com.example.ui.theme.LozAiPurplePrimary
import com.example.ui.theme.LozAiSky
import com.example.ui.theme.LozAiSurface
import com.example.ui.theme.LozAiSurfaceElevated
import com.example.ui.theme.LozAiTextMuted
import com.example.ui.theme.LozAiTextPrimary
import com.example.ui.theme.LozAiTextSecondary
import com.example.ui.theme.PillBadgeBg
import com.example.ui.theme.PillBadgeText
import com.example.ui.theme.StatusCompletedBg
import com.example.ui.theme.StatusCompletedBorder
import com.example.ui.theme.StatusCompletedText
import com.example.ui.theme.StatusInactiveBg
import com.example.ui.theme.StatusInactiveBorder
import com.example.ui.theme.StatusInactiveText
import com.example.ui.theme.StatusInProgressBg
import com.example.ui.theme.StatusInProgressBorder
import com.example.ui.theme.StatusInProgressText
import com.example.ui.theme.StatusPendingBg
import com.example.ui.theme.StatusPendingBorder
import com.example.ui.theme.StatusPendingText

/**
 * Brand Logo Icon & Label
 */
@Composable
fun LozAiLogoBadge(
    modifier: Modifier = Modifier,
    badgeSize: Dp = 40.dp,
    showSubtitle: Boolean = true,
    subtitleText: String = "Soporte técnico inteligente"
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(badgeSize)
                .clip(RoundedCornerShape(12.dp))
                .background(LozAiGradientBrush),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "AI",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = (badgeSize.value * 0.42f).sp,
                letterSpacing = 0.5.sp
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = "LozAI",
                color = LozAiTextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = (badgeSize.value * 0.5f).sp
            )
            if (showSubtitle) {
                Text(
                    text = subtitleText,
                    color = LozAiTextMuted,
                    fontSize = 12.sp
                )
            }
        }
    }
}

/**
 * Top App Bar with brand, role indicator, and quick persona switcher
 */
@Composable
fun LozAiTopBar(
    currentUser: LozAiUser,
    onRoleSwitchClick: () -> Unit,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        color = LozAiBg.copy(alpha = 0.95f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LozAiLogoBadge(
                badgeSize = 34.dp,
                showSubtitle = false
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Role Switcher Chip
                Surface(
                    onClick = onRoleSwitchClick,
                    shape = RoundedCornerShape(20.dp),
                    color = LozAiSurfaceElevated,
                    border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder),
                    modifier = Modifier.testTag("role_switcher_chip")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(
                                    when (currentUser.role) {
                                        UserRole.TECH -> LozAiSky
                                        UserRole.USER -> LozAiPurpleLight
                                        UserRole.ADMIN -> LozAiCyan
                                    }
                                )
                        )
                        Text(
                            text = currentUser.name,
                            color = LozAiTextPrimary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                        Icon(
                            imageVector = Icons.Default.SwapHoriz,
                            contentDescription = "Cambiar rol de usuario",
                            tint = LozAiTextMuted,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                // Logout icon button
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(LozAiPurplePrimary)
                        .clickable(onClick = onLogoutClick)
                        .testTag("logout_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "Cerrar sesión",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

/**
 * Header Banner with Gradient, chip and status pill
 */
@Composable
fun LozAiHeaderBanner(
    category: String,
    title: String,
    subtitle: String,
    statusPillText: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorderGlow)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(LozAiHeroBannerBrush)
                .padding(20.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = category.uppercase(),
                        color = LozAiCyan,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp
                    )

                    // Top-right status badge
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(PillBadgeBg)
                            .border(1.dp, LozAiPurplePrimary.copy(alpha = 0.4f), RoundedCornerShape(20.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = statusPillText,
                            color = PillBadgeText,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = title,
                    color = LozAiTextPrimary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = subtitle,
                    color = LozAiTextSecondary,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

/**
 * Status Pill (Pendiente, En proceso, Completada, etc.)
 */
@Composable
fun StatusPill(
    status: String,
    modifier: Modifier = Modifier
) {
    val (bg, border, text) = when (status.lowercase()) {
        "pendiente" -> Triple(StatusPendingBg, StatusPendingBorder, StatusPendingText)
        "en proceso" -> Triple(StatusInProgressBg, StatusInProgressBorder, StatusInProgressText)
        "completada", "activa", "activo", "habilitada" -> Triple(
            StatusCompletedBg,
            StatusCompletedBorder,
            StatusCompletedText
        )
        "inactiva", "inactivo", "deshabilitada" -> Triple(
            StatusInactiveBg,
            StatusInactiveBorder,
            StatusInactiveText
        )
        "en mantenimiento" -> Triple(StatusPendingBg, StatusPendingBorder, StatusPendingText)
        else -> Triple(LozAiSurfaceElevated, LozAiCardBorder, LozAiTextSecondary)
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bg)
            .border(1.dp, border, RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp, vertical = 3.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = status,
            color = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

/**
 * Gradient Action Button (Primary)
 */
@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    testTag: String = "gradient_button"
) {
    Surface(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .testTag(testTag),
        shape = RoundedCornerShape(12.dp),
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .background(if (enabled) LozAiGradientBrush else Brush.linearGradient(listOf(Color(0xFF334155), Color(0xFF1E293B))))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = text,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}

/**
 * Secondary Dark Button
 */
@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    testTag: String = "secondary_button"
) {
    Surface(
        onClick = onClick,
        modifier = modifier
            .height(44.dp)
            .testTag(testTag),
        shape = RoundedCornerShape(10.dp),
        color = LozAiSurfaceElevated,
        border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = LozAiTextSecondary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(
                text = text,
                color = LozAiTextSecondary,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp
            )
        }
    }
}

/**
 * Bottom Navigation Bar
 */
@Composable
fun LozAiBottomNav(
    userRole: UserRole,
    currentScreen: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = when (userRole) {
        UserRole.TECH -> listOf(
            Triple("tech_dash", "Inicio", Icons.Default.Home),
            Triple("tech_tickets", "Tickets", Icons.Default.ConfirmationNumber),
            Triple("machines", "Máquinas", Icons.Default.Computer),
            Triple("chat_lozai", "LozAI", Icons.Default.SmartToy)
        )
        UserRole.USER -> listOf(
            Triple("user_dash", "Inicio", Icons.Default.Home),
            Triple("user_tickets", "Mis tickets", Icons.Default.ConfirmationNumber),
            Triple("create_ticket", "Crear ticket", Icons.Default.ConfirmationNumber)
        )
        UserRole.ADMIN -> listOf(
            Triple("admin_dash", "Inicio", Icons.Default.Home),
            Triple("users_list", "Usuarios", Icons.Default.Group)
        )
    }

    NavigationBar(
        modifier = modifier.navigationBarsPadding(),
        containerColor = LozAiSurface,
        tonalElevation = 8.dp
    ) {
        items.forEach { (route, label, icon) ->
            val isSelected = currentScreen == route ||
                    (route == "tech_tickets" && currentScreen == "tech_ticket_detail") ||
                    (route == "user_tickets" && currentScreen == "user_ticket_detail") ||
                    (route == "machines" && (currentScreen == "new_machine" || currentScreen == "edit_machine")) ||
                    (route == "users_list" && currentScreen == "edit_user")

            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(route) },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        modifier = Modifier.size(22.dp)
                    )
                },
                label = {
                    Text(
                        text = label,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = LozAiSky,
                    indicatorColor = LozAiPurplePrimary,
                    unselectedIconColor = LozAiTextMuted,
                    unselectedTextColor = LozAiTextMuted
                )
            )
        }
    }
}

/**
 * Persona Switcher Dialog (Essential for Academic Demo Presentation)
 */
@Composable
fun RoleSwitchDialog(
    isOpen: Boolean,
    onDismiss: () -> Unit,
    onSelectUser: (LozAiUser) -> Unit
) {
    if (!isOpen) return

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = LozAiSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorderGlow),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.SwapHoriz,
                        contentDescription = null,
                        tint = LozAiSky
                    )
                    Text(
                        text = "Cambiar de Rol / Usuario",
                        color = LozAiTextPrimary,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = "Selecciona un perfil para navegar el prototipo durante la presentación:",
                    color = LozAiTextSecondary,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
                )

                // 3 Core Personas from Web Screenshots
                val presentationPersonas = listOf(
                    Triple(
                        LozAiUser("tech_mario", "Mario", "mario@lozai.com", UserRole.TECH, true),
                        "Técnico - Mesa de Trabajo",
                        "Acceso a Tickets, Máquinas y Chat Asistido LozAI"
                    ),
                    Triple(
                        LozAiUser("usr_celeste", "Celeste", "celeste@lozai.com", UserRole.USER, true),
                        "Usuario - Portal de Asistencia",
                        "Creación de tickets y consulta de solicitudes"
                    ),
                    Triple(
                        LozAiUser("adm_azul", "Azul", "usuario@lozai.com", UserRole.ADMIN, true),
                        "Administrador - Gestión de Cuentas",
                        "Control de usuarios, roles y activaciones"
                    )
                )

                presentationPersonas.forEach { (user, title, desc) ->
                    Surface(
                        onClick = {
                            onSelectUser(user)
                            onDismiss()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        shape = RoundedCornerShape(12.dp),
                        color = LozAiSurfaceElevated,
                        border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder)
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(
                                        when (user.role) {
                                            UserRole.TECH -> LozAiPurplePrimary
                                            UserRole.USER -> LozAiSky
                                            UserRole.ADMIN -> LozAiCyan
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = user.name.take(1),
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = title,
                                    color = LozAiTextPrimary,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = desc,
                                    color = LozAiTextMuted,
                                    fontSize = 11.sp,
                                    lineHeight = 14.sp
                                )
                            }
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                tint = LozAiTextMuted,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
