package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.LozAiRepository
import com.example.model.LozAiUser
import com.example.model.UserRole
import com.example.ui.components.GradientButton
import com.example.ui.components.LozAiLogoBadge
import com.example.ui.theme.LozAiBg
import com.example.ui.theme.LozAiCardBorder
import com.example.ui.theme.LozAiCardBorderGlow
import com.example.ui.theme.LozAiCyan
import com.example.ui.theme.LozAiPurplePrimary
import com.example.ui.theme.LozAiSky
import com.example.ui.theme.LozAiSurface
import com.example.ui.theme.LozAiSurfaceElevated
import com.example.ui.theme.LozAiTextMuted
import com.example.ui.theme.LozAiTextPrimary
import com.example.ui.theme.LozAiTextSecondary

@Composable
fun LoginScreen(
    onLoginSuccess: (LozAiUser) -> Unit,
    onNavigateToRegister: () -> Unit
) {
    var email by remember { mutableStateOf("usuario@lozai.com") }
    var password by remember { mutableStateOf("••••••••") }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LozAiBg)
            .imePadding(),
        contentAlignment = Alignment.Center
    ) {
        // Subtle ambient radial glow orbs (matching web screenshots)
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(260.dp)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            LozAiPurplePrimary.copy(alpha = 0.18f),
                            Color.Transparent
                        )
                    )
                )
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(260.dp)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            LozAiCyan.copy(alpha = 0.12f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Main Card matching Web Screenshot Login.png
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("login_card"),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = LozAiSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorderGlow)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    LozAiLogoBadge(
                        badgeSize = 44.dp,
                        showSubtitle = true,
                        subtitleText = "Soporte técnico inteligente"
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    Text(
                        text = "Iniciar sesión",
                        color = LozAiTextPrimary,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Accedé al panel de gestión y asistencia técnica.",
                        color = LozAiTextMuted,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Field: Correo electrónico
                    Text(
                        text = "Correo electrónico",
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
                            .testTag("login_email_input"),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFF0B1120),
                            unfocusedContainerColor = Color(0xFF0B1120),
                            focusedBorderColor = LozAiCyan,
                            unfocusedBorderColor = LozAiCardBorder,
                            focusedTextColor = LozAiTextPrimary,
                            unfocusedTextColor = LozAiTextPrimary
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Field: Contraseña
                    Text(
                        text = "Contraseña",
                        color = LozAiTextSecondary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("login_password_input"),
                        shape = RoundedCornerShape(12.dp),
                        visualTransformation = PasswordVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFF0B1120),
                            unfocusedContainerColor = Color(0xFF0B1120),
                            focusedBorderColor = LozAiCyan,
                            unfocusedBorderColor = LozAiCardBorder,
                            focusedTextColor = LozAiTextPrimary,
                            unfocusedTextColor = LozAiTextPrimary
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Primary Button: Entrar al sistema
                    GradientButton(
                        text = "Entrar al sistema",
                        onClick = {
                            val defaultTech = LozAiRepository.sampleUsers.first { it.role == UserRole.TECH }
                            LozAiRepository.setCurrentUser(defaultTech)
                            onLoginSuccess(defaultTech)
                        },
                        testTag = "login_submit_button"
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Link: ¿No tenés cuenta? Crear usuario
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "¿No tenés cuenta? ",
                            color = LozAiTextMuted,
                            fontSize = 13.sp
                        )
                        Text(
                            text = "Crear usuario",
                            color = LozAiSky,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            modifier = Modifier
                                .clickable { onNavigateToRegister() }
                                .testTag("go_to_register_link")
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Quick access shortcuts for presentation
                    Text(
                        text = "ACCESO DIRECTO DEMO",
                        color = LozAiTextMuted,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            onClick = {
                                val tech = LozAiRepository.sampleUsers.first { it.name == "Mario" }
                                LozAiRepository.setCurrentUser(tech)
                                onLoginSuccess(tech)
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp),
                            color = LozAiSurfaceElevated,
                            border = androidx.compose.foundation.BorderStroke(1.dp, LozAiPurplePrimary)
                        ) {
                            Text(
                                text = "Técnico\n(Mario)",
                                color = LozAiTextPrimary,
                                fontSize = 11.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }

                        Surface(
                            onClick = {
                                val user = LozAiRepository.sampleUsers.first { it.name == "Celeste" }
                                LozAiRepository.setCurrentUser(user)
                                onLoginSuccess(user)
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp),
                            color = LozAiSurfaceElevated,
                            border = androidx.compose.foundation.BorderStroke(1.dp, LozAiSky)
                        ) {
                            Text(
                                text = "Usuario\n(Celeste)",
                                color = LozAiTextPrimary,
                                fontSize = 11.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }

                        Surface(
                            onClick = {
                                val admin = LozAiRepository.sampleUsers.first { it.name == "Azul" }
                                LozAiRepository.setCurrentUser(admin)
                                onLoginSuccess(admin)
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp),
                            color = LozAiSurfaceElevated,
                            border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCyan)
                        ) {
                            Text(
                                text = "Admin\n(Azul)",
                                color = LozAiTextPrimary,
                                fontSize = 11.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "LozAI · v1.0",
                color = LozAiTextMuted.copy(alpha = 0.5f),
                fontSize = 11.sp
            )
        }
    }
}

@Composable
fun RegisterScreen(
    onRegisterSuccess: (LozAiUser) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LozAiBg)
            .imePadding(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("register_card"),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = LozAiSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorderGlow)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    LozAiLogoBadge(
                        badgeSize = 44.dp,
                        showSubtitle = true,
                        subtitleText = "Crear nueva cuenta"
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    Text(
                        text = "Registro",
                        color = LozAiTextPrimary,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Creá un usuario para acceder al sistema.",
                        color = LozAiTextMuted,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Field: Nombre
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
                        placeholder = { Text("Tu nombre", color = LozAiTextMuted) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("register_name_input"),
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

                    Spacer(modifier = Modifier.height(18.dp))

                    // Field: Correo electrónico
                    Text(
                        text = "Correo electrónico",
                        color = LozAiTextSecondary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text("usuario@lozai.com", color = LozAiTextMuted) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("register_email_input"),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFF0B1120),
                            unfocusedContainerColor = Color(0xFF0B1120),
                            focusedBorderColor = LozAiCyan,
                            unfocusedBorderColor = LozAiCardBorder,
                            focusedTextColor = LozAiTextPrimary,
                            unfocusedTextColor = LozAiTextPrimary
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Field: Contraseña
                    Text(
                        text = "Contraseña",
                        color = LozAiTextSecondary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("Creá una contraseña", color = LozAiTextMuted) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("register_password_input"),
                        shape = RoundedCornerShape(12.dp),
                        visualTransformation = PasswordVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFF0B1120),
                            unfocusedContainerColor = Color(0xFF0B1120),
                            focusedBorderColor = LozAiCyan,
                            unfocusedBorderColor = LozAiCardBorder,
                            focusedTextColor = LozAiTextPrimary,
                            unfocusedTextColor = LozAiTextPrimary
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Primary Button: Crear cuenta
                    GradientButton(
                        text = "Crear cuenta",
                        onClick = {
                            val userName = name.ifBlank { "Nuevo Usuario" }
                            val userEmail = email.ifBlank { "usuario@lozai.com" }
                            val newUser = LozAiUser(
                                id = "usr_${System.currentTimeMillis()}",
                                name = userName,
                                email = userEmail,
                                role = UserRole.USER,
                                isActive = true
                            )
                            LozAiRepository.setCurrentUser(newUser)
                            onRegisterSuccess(newUser)
                        },
                        testTag = "register_submit_button"
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Link: ¿Ya tenés cuenta? Iniciar sesión
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "¿Ya tenés cuenta? ",
                            color = LozAiTextMuted,
                            fontSize = 13.sp
                        )
                        Text(
                            text = "Iniciar sesión",
                            color = LozAiSky,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            modifier = Modifier
                                .clickable { onNavigateToLogin() }
                                .testTag("go_to_login_link")
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "LozAI · v1.0",
                color = LozAiTextMuted.copy(alpha = 0.5f),
                fontSize = 11.sp
            )
        }
    }
}
