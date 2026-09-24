package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.LozAiRepository
import com.example.model.ChatMessage
import com.example.model.Machine
import com.example.model.ToolExecution
import com.example.ui.components.LozAiHeaderBanner
import com.example.ui.components.StatusPill
import com.example.ui.theme.LozAiBg
import com.example.ui.theme.LozAiCardBorder
import com.example.ui.theme.LozAiCardBorderGlow
import com.example.ui.theme.LozAiCyan
import com.example.ui.theme.LozAiGradientBrush
import com.example.ui.theme.LozAiPurpleLight
import com.example.ui.theme.LozAiPurplePrimary
import com.example.ui.theme.LozAiSky
import com.example.ui.theme.LozAiSurface
import com.example.ui.theme.LozAiSurfaceElevated
import com.example.ui.theme.LozAiTextMuted
import com.example.ui.theme.LozAiTextPrimary
import com.example.ui.theme.LozAiTextSecondary

/**
 * 14. Chat LozAI Screen (ChatLozAI.png)
 * Technical AI Support with context and simulated tool execution.
 */
@Composable
fun LozAiChatScreen(
    onBackToMachines: () -> Unit
) {
    val activeMachine by LozAiRepository.activeChatMachine.collectAsState()
    val chatMap by LozAiRepository.chatMessages.collectAsState()
    val isExecutingTool by LozAiRepository.isChatExecutingTool.collectAsState()

    val currentMachine = activeMachine ?: Machine("m_default", "CC20", "192.168.1.10", "Activa")
    val messages = chatMap[currentMachine.id] ?: emptyList()

    var inputQuery by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    LaunchedEffect(messages.size, isExecutingTool) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LozAiBg)
            .imePadding()
    ) {
        // Top Back Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBackToMachines) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = LozAiTextPrimary
                    )
                }
                Text(
                    text = "Asistente LozAI",
                    color = LozAiTextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (messages.isNotEmpty()) {
                Surface(
                    onClick = { LozAiRepository.clearChat(currentMachine.id) },
                    shape = RoundedCornerShape(8.dp),
                    color = LozAiSurfaceElevated
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Limpiar chat",
                            tint = LozAiTextMuted,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "Limpiar",
                            color = LozAiTextMuted,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }

        // Context Banner Header
        LozAiHeaderBanner(
            category = "Asistente Tecnico",
            title = "Chat con LozAI",
            subtitle = "Consulta el estado de ${currentMachine.name} y solicita pruebas de diagnostico.",
            statusPillText = "IA disponible"
        )

        // Machine Context Card & Quick Prompt Chips
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = LozAiSurfaceElevated),
            border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(LozAiPurplePrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Computer,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Column {
                            Text(
                                text = "Máquina: ${currentMachine.name}",
                                color = LozAiTextPrimary,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "IP: ${currentMachine.ip}",
                                color = LozAiSky,
                                fontSize = 11.sp
                            )
                        }
                    }

                    StatusPill(status = currentMachine.status)
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Quick Prompt Examples (from Web screenshot ChatLozAI.png)
                Text(
                    text = "EJEMPLOS DE CONSULTA",
                    color = LozAiCyan,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                val promptChips = listOf(
                    "¿La máquina responde?",
                    "Ejecuta un ping.",
                    "Realiza un diagnóstico.",
                    "Comprobá si tiene conectividad."
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(promptChips) { chipText ->
                        Surface(
                            onClick = {
                                if (!isExecutingTool) {
                                    LozAiRepository.sendChatMessage(currentMachine.id, chipText)
                                }
                            },
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFF070B1A),
                            border = androidx.compose.foundation.BorderStroke(1.dp, LozAiPurplePrimary.copy(alpha = 0.5f))
                        ) {
                            Text(
                                text = chipText,
                                color = LozAiTextSecondary,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }
        }

        // Chat Messages Stream
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            if (messages.isEmpty()) {
                // Empty state matching ChatLozAI.png
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(LozAiSurfaceElevated)
                            .border(1.dp, LozAiCardBorderGlow, RoundedCornerShape(20.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.SmartToy,
                            contentDescription = null,
                            tint = LozAiPurpleLight,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Inicia una consulta",
                        color = LozAiTextPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Escribe un mensaje o pulsa un ejemplo sobre ${currentMachine.name}.",
                        color = LozAiTextMuted,
                        fontSize = 13.sp
                    )
                }
            } else {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item { Spacer(modifier = Modifier.height(4.dp)) }

                    items(messages) { msg ->
                        ChatMessageItem(message = msg)
                    }

                    item { Spacer(modifier = Modifier.height(10.dp)) }
                }
            }
        }

        // Bottom Input Bar
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            shape = RoundedCornerShape(16.dp),
            color = LozAiSurface,
            border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = inputQuery,
                    onValueChange = { inputQuery = it },
                    placeholder = {
                        Text(
                            "Escribe tu consulta tecnica...",
                            color = LozAiTextMuted,
                            fontSize = 13.sp
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("chat_query_input"),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = LozAiTextPrimary,
                        unfocusedTextColor = LozAiTextPrimary
                    ),
                    singleLine = true
                )

                // Send Icon Button
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (inputQuery.isNotBlank() && !isExecutingTool) LozAiGradientBrush else SolidColor(LozAiSurfaceElevated))
                        .clickable(enabled = inputQuery.isNotBlank() && !isExecutingTool) {
                            val textToSend = inputQuery
                            inputQuery = ""
                            LozAiRepository.sendChatMessage(currentMachine.id, textToSend)
                        }
                        .testTag("chat_send_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Enviar",
                        tint = if (inputQuery.isNotBlank() && !isExecutingTool) Color.White else LozAiTextMuted,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ChatMessageItem(message: ChatMessage) {
    if (message.isFromTech) {
        // Technician message on right
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp, 4.dp, 16.dp, 16.dp),
                color = LozAiPurplePrimary,
                modifier = Modifier.fillMaxWidth(0.85f)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = message.text,
                        color = Color.White,
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = message.timestamp,
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 10.sp,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
        }
    } else {
        // LozAI Response on left (with optional tool execution banner)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Column(modifier = Modifier.fillMaxWidth(0.92f)) {
                // Header with AI Icon
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(bottom = 6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(LozAiGradientBrush),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "AI",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                    Text(
                        text = "LozAI",
                        color = LozAiSky,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                    Text(
                        text = message.timestamp,
                        color = LozAiTextMuted,
                        fontSize = 10.sp
                    )
                }

                // Tool Execution Card (if tool was invoked!)
                if (message.toolExecution != null) {
                    ToolExecutionCard(execution = message.toolExecution)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // AI Response Body
                Surface(
                    shape = RoundedCornerShape(4.dp, 16.dp, 16.dp, 16.dp),
                    color = LozAiSurface,
                    border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = message.text,
                            color = LozAiTextPrimary,
                            fontSize = 14.sp,
                            lineHeight = 20.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ToolExecutionCard(execution: ToolExecution) {
    val infiniteTransition = rememberInfiniteTransition(label = "tool_spin")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF060B18)),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (execution.isRunning) LozAiCyan.copy(alpha = 0.8f) else LozAiPurpleLight.copy(alpha = 0.4f)
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (execution.isRunning) {
                    Icon(
                        imageVector = Icons.Default.Sync,
                        contentDescription = "Ejecutando",
                        tint = LozAiCyan,
                        modifier = Modifier
                            .size(16.dp)
                            .rotate(rotation)
                    )
                    Text(
                        text = "Ejecutando herramienta: ${execution.toolName} en ${execution.target}...",
                        color = LozAiCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Resultado",
                        tint = Color(0xFF34D399),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Herramienta completada: ${execution.toolName}",
                        color = Color(0xFF34D399),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            if (!execution.isRunning && execution.result != null) {
                Spacer(modifier = Modifier.height(8.dp))
                // Terminal style result box
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFF030712),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1F2937)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = execution.result,
                        color = Color(0xFF38BDF8),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}
