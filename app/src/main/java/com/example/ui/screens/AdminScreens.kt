package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.LozAiRepository
import com.example.model.LozAiUser
import com.example.model.UserRole
import com.example.ui.components.GradientButton
import com.example.ui.components.LozAiHeaderBanner
import com.example.ui.components.SecondaryButton
import com.example.ui.components.StatusPill
import com.example.ui.theme.LozAiBg
import com.example.ui.theme.LozAiCardBorder
import com.example.ui.theme.LozAiCyan
import com.example.ui.theme.LozAiPurpleLight
import com.example.ui.theme.LozAiPurplePrimary
import com.example.ui.theme.LozAiSky
import com.example.ui.theme.LozAiSurface
import com.example.ui.theme.LozAiSurfaceElevated
import com.example.ui.theme.LozAiTextMuted
import com.example.ui.theme.LozAiTextPrimary
import com.example.ui.theme.LozAiTextSecondary

/**
 * 15. Admin Dashboard Screen (Dash.png for Admin)
 */
@Composable
fun AdminDashboardScreen(
    currentUser: LozAiUser,
    onManageUsersClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LozAiBg)
            .verticalScroll(scrollState)
            .padding(bottom = 80.dp)
    ) {
        LozAiHeaderBanner(
            category = "Area Administrativa",
            title = "Hola, ${currentUser.name}",
            subtitle = "Administra los usuarios que acceden al sistema.",
            statusPillText = "Acceso administrativo"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "GESTION",
                color = LozAiCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Text(
                text = "Usuarios del sistema",
                color = LozAiTextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
            )

            // Card: Administrar cuentas
            Surface(
                onClick = onManageUsersClick,
                shape = RoundedCornerShape(16.dp),
                color = LozAiSurface,
                border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .testTag("admin_dash_manage_users_card")
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(LozAiCyan.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Group,
                            contentDescription = null,
                            tint = LozAiCyan,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "USUARIOS",
                        color = LozAiCyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Administrar cuentas",
                        color = LozAiTextPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Text(
                        text = "Consulta el listado y realiza altas, ediciones o bajas logicas.",
                        color = LozAiTextSecondary,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Abrir listado",
                            color = LozAiSky,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = LozAiSky,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }

            // Section: FUNCIONES - Control administrativo
            Text(
                text = "FUNCIONES",
                color = LozAiCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Control administrativo",
                color = LozAiTextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
            )

            // Card Roles
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = LozAiSurface,
                border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(LozAiPurplePrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Security,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Roles",
                            color = LozAiTextPrimary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Asigna perfiles de administrador, tecnico o usuario.",
                            color = LozAiTextMuted,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            // Card Acceso
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = LozAiSurface,
                border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(LozAiCyan),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.LockOpen,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Acceso",
                            color = LozAiTextPrimary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Activa o desactiva cuentas sin borrar su historial.",
                            color = LozAiTextMuted,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

/**
 * 16. Usuarios List Screen (Usuarios lista.png)
 */
@Composable
fun UsersListScreen(
    onEditUserClick: (String) -> Unit
) {
    val users by LozAiRepository.usersList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LozAiBg)
            .padding(bottom = 80.dp)
    ) {
        LozAiHeaderBanner(
            category = "Administracion",
            title = "Usuarios",
            subtitle = "Consulta y administra los usuarios registrados.",
            statusPillText = "Acceso administrativo"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Usuarios registrados",
                color = LozAiTextPrimary,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Total: ${users.size}",
                color = LozAiTextMuted,
                fontSize = 12.sp
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(users) { user ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("user_item_${user.name}"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = LozAiSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(38.dp)
                                        .clip(CircleShape)
                                        .background(
                                            when (user.role) {
                                                UserRole.ADMIN -> LozAiCyan
                                                UserRole.TECH -> LozAiPurplePrimary
                                                UserRole.USER -> LozAiSky
                                            }
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = user.name.take(1).uppercase(),
                                        color = if (user.role == UserRole.ADMIN) Color.Black else Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                }
                                Column {
                                    Text(
                                        text = user.name,
                                        color = LozAiTextPrimary,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp
                                    )
                                    Text(
                                        text = user.email,
                                        color = LozAiTextMuted,
                                        fontSize = 12.sp
                                    )
                                }
                            }

                            StatusPill(status = if (user.isActive) "Activo" else "Inactivo")
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Rol: ${user.role.label}",
                                color = LozAiTextSecondary,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )

                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Surface(
                                    onClick = { onEditUserClick(user.id) },
                                    shape = RoundedCornerShape(8.dp),
                                    color = LozAiSurfaceElevated,
                                    border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder)
                                ) {
                                    Text(
                                        text = "Editar",
                                        color = LozAiTextSecondary,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                    )
                                }

                                Surface(
                                    onClick = { LozAiRepository.toggleUserActive(user.id) },
                                    shape = RoundedCornerShape(8.dp),
                                    color = LozAiSurfaceElevated,
                                    border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder)
                                ) {
                                    Text(
                                        text = if (user.isActive) "Desactivar" else "Activar",
                                        color = if (user.isActive) LozAiTextMuted else LozAiSky,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * 17. Edit User Screen (Editar usuario.png)
 */
@Composable
fun EditUserScreen(
    userId: String,
    onSaveSuccess: () -> Unit,
    onCancelClick: () -> Unit
) {
    val users by LozAiRepository.usersList.collectAsState()
    val user = users.firstOrNull { it.id == userId } ?: users.first()

    var name by remember(user) { mutableStateOf(user.name) }
    var email by remember(user) { mutableStateOf(user.email) }
    var role by remember(user) { mutableStateOf(user.role) }
    var expandedRole by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LozAiBg)
            .verticalScroll(scrollState)
            .padding(bottom = 80.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onCancelClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = LozAiTextPrimary
                )
            }
            Text(
                text = "Editar usuario",
                color = LozAiTextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        LozAiHeaderBanner(
            category = "Administracion",
            title = "Editar usuario",
            subtitle = "Modifica los datos y el rol de la cuenta.",
            statusPillText = "Acceso administrativo"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = LozAiSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Datos del usuario",
                    color = LozAiTextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Nombre
                Text(
                    text = "Nombre",
                    color = LozAiTextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("user_name_input"),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF0B1120),
                        unfocusedContainerColor = Color(0xFF0B1120),
                        focusedBorderColor = LozAiCyan,
                        unfocusedBorderColor = LozAiCardBorder,
                        focusedTextColor = LozAiTextPrimary,
                        unfocusedTextColor = LozAiTextPrimary
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Correo electronico
                Text(
                    text = "Correo electronico",
                    color = LozAiTextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("user_email_input"),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF0B1120),
                        unfocusedContainerColor = Color(0xFF0B1120),
                        focusedBorderColor = LozAiCyan,
                        unfocusedBorderColor = LozAiCardBorder,
                        focusedTextColor = LozAiTextPrimary,
                        unfocusedTextColor = LozAiTextPrimary
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Rol
                Text(
                    text = "Rol",
                    color = LozAiTextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(6.dp))

                Box {
                    Surface(
                        onClick = { expandedRole = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFF0B1120),
                        border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = role.label,
                                color = LozAiTextPrimary,
                                fontSize = 14.sp
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null,
                                tint = LozAiTextMuted
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = expandedRole,
                        onDismissRequest = { expandedRole = false },
                        modifier = Modifier
                            .background(LozAiSurfaceElevated)
                            .border(1.dp, LozAiCardBorder, RoundedCornerShape(8.dp))
                    ) {
                        UserRole.values().forEach { r ->
                            DropdownMenuItem(
                                text = { Text(text = r.label, color = LozAiTextPrimary) },
                                onClick = {
                                    role = r
                                    expandedRole = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SecondaryButton(
                        text = "Cancelar",
                        onClick = onCancelClick,
                        modifier = Modifier.weight(1f)
                    )

                    GradientButton(
                        text = "Guardar cambios",
                        enabled = name.isNotBlank() && email.isNotBlank(),
                        onClick = {
                            LozAiRepository.updateUser(user.id, name, email, role)
                            onSaveSuccess()
                        },
                        modifier = Modifier.weight(1.4f),
                        testTag = "save_user_button"
                    )
                }
            }
        }
    }
}
