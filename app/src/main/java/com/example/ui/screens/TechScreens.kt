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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
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
import com.example.model.Machine
import com.example.model.SupportTicket
import com.example.model.TicketStatus
import com.example.ui.components.GradientButton
import com.example.ui.components.LozAiHeaderBanner
import com.example.ui.components.SecondaryButton
import com.example.ui.components.StatusPill
import com.example.ui.theme.LozAiBg
import com.example.ui.theme.LozAiCardBorder
import com.example.ui.theme.LozAiCardBorderGlow
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
 * 8. Tech Dashboard Screen (Dash.png)
 */
@Composable
fun TechDashboardScreen(
    currentUser: LozAiUser,
    onViewTicketsClick: () -> Unit,
    onViewMachinesClick: () -> Unit,
    onOpenLozAiChatClick: () -> Unit
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
            category = "Mesa de trabajo",
            title = "Centro tecnico",
            subtitle = "Gestiona equipos y solicitudes de soporte.",
            statusPillText = "Herramientas disponibles"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Section: HERRAMIENTAS - Selecciona una opcion
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "HERRAMIENTAS",
                color = LozAiCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Text(
                text = "Selecciona una opcion",
                color = LozAiTextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
            )

            // Card 1: Tickets
            Surface(
                onClick = onViewTicketsClick,
                shape = RoundedCornerShape(16.dp),
                color = LozAiSurface,
                border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .testTag("tech_dash_tickets_card")
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(LozAiPurplePrimary.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ConfirmationNumber,
                            contentDescription = null,
                            tint = LozAiPurpleLight,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "GESTION",
                        color = LozAiCyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Tickets",
                        color = LozAiTextPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Text(
                        text = "Revisa solicitudes y registra su seguimiento.",
                        color = LozAiTextSecondary,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Ver tickets",
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

            // Card 2: Maquinas
            Surface(
                onClick = onViewMachinesClick,
                shape = RoundedCornerShape(16.dp),
                color = LozAiSurface,
                border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .testTag("tech_dash_machines_card")
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(LozAiSky.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Computer,
                            contentDescription = null,
                            tint = LozAiSky,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "EQUIPOS",
                        color = LozAiCyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Maquinas",
                        color = LozAiTextPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Text(
                        text = "Consulta equipos y utiliza las herramientas de diagnostico.",
                        color = LozAiTextSecondary,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Ver maquinas",
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

            // Card 3: Asistente LozAI
            Surface(
                onClick = onOpenLozAiChatClick,
                shape = RoundedCornerShape(16.dp),
                color = LozAiSurface,
                border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorderGlow),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .testTag("tech_dash_lozai_card")
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(LozAiCyan.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.SmartToy,
                            contentDescription = null,
                            tint = LozAiCyan,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "INTELIGENCIA ARTIFICIAL",
                        color = LozAiCyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Asistente LozAI",
                        color = LozAiTextPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Text(
                        text = "Diagnóstico en tiempo real y ejecución contextual de herramientas de red.",
                        color = LozAiTextSecondary,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Abrir LozAI",
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

            // Section: PROCESO - Flujo de trabajo
            Text(
                text = "PROCESO",
                color = LozAiCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Flujo de trabajo",
                color = LozAiTextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
            )

            StepProcessCard(step = "1", title = "Revisar", desc = "Consulta el ticket.")
            Spacer(modifier = Modifier.height(8.dp))
            StepProcessCard(step = "2", title = "Diagnosticar", desc = "Analiza la maquina.")
            Spacer(modifier = Modifier.height(8.dp))
            StepProcessCard(step = "3", title = "Actualizar", desc = "Registra el resultado.")
        }
    }
}

/**
 * 9. Tech Tickets Screen (Tickets.png)
 */
@Composable
fun TechTicketsScreen(
    onSelectTicket: (String) -> Unit
) {
    val tickets by LozAiRepository.tickets.collectAsState()
    var selectedFilter by remember { mutableStateOf("Todos") }

    val filteredTickets = remember(tickets, selectedFilter) {
        when (selectedFilter) {
            "Pendiente" -> tickets.filter { it.status == TicketStatus.PENDIENTE }
            "En proceso" -> tickets.filter { it.status == TicketStatus.EN_PROCESO }
            "Completada" -> tickets.filter { it.status == TicketStatus.COMPLETADA }
            else -> tickets
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LozAiBg)
            .padding(bottom = 80.dp)
    ) {
        LozAiHeaderBanner(
            category = "Soporte Tecnico",
            title = "Tickets",
            subtitle = "Consulta y actualiza las solicitudes de soporte.",
            statusPillText = "Gestion de tickets"
        )

        // Filters row
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val filters = listOf("Todos", "Pendiente", "En proceso", "Completada")
            items(filters) { filter ->
                val isSelected = selectedFilter == filter
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedFilter = filter },
                    label = {
                        Text(
                            text = filter,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = LozAiPurplePrimary,
                        selectedLabelColor = Color.White,
                        containerColor = LozAiSurfaceElevated,
                        labelColor = LozAiTextSecondary
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = if (isSelected) LozAiPurpleLight else LozAiCardBorder,
                        enabled = true,
                        selected = isSelected
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filteredTickets) { ticket ->
                Surface(
                    onClick = { onSelectTicket(ticket.id) },
                    shape = RoundedCornerShape(16.dp),
                    color = LozAiSurface,
                    border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("tech_ticket_card_${ticket.number}")
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Ticket #${ticket.number}",
                                color = LozAiTextPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            StatusPill(status = ticket.status.label)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = ticket.description,
                            color = LozAiTextSecondary,
                            fontSize = 13.sp,
                            lineHeight = 18.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${ticket.userName} · ${ticket.date}",
                                color = LozAiTextMuted,
                                fontSize = 12.sp
                            )
                            Text(
                                text = ticket.machineName,
                                color = LozAiCyan,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * 10. Detalle de ticket técnico (Ticket seleccionado.png)
 */
@Composable
fun TechTicketDetailScreen(
    ticketId: String,
    onBackClick: () -> Unit,
    onOpenLozAiForMachine: (String) -> Unit
) {
    val tickets by LozAiRepository.tickets.collectAsState()
    val ticket = tickets.firstOrNull { it.id == ticketId } ?: tickets.first()

    var currentStatus by remember(ticket) { mutableStateOf(ticket.status) }
    var followUpComment by remember { mutableStateOf("") }
    var expandedStatusDropdown by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LozAiBg)
            .verticalScroll(scrollState)
            .padding(bottom = 80.dp)
    ) {
        // Back bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = LozAiTextPrimary
                )
            }
            Text(
                text = "Detalle del ticket",
                color = LozAiTextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        LozAiHeaderBanner(
            category = "Soporte Tecnico",
            title = "Ticket #${ticket.number}",
            subtitle = "Gestiona y actualiza la resolución del caso.",
            statusPillText = ticket.status.label
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Info Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = LozAiSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Datos de la solicitud",
                        color = LozAiTextPrimary,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    StatusPill(status = ticket.status.label)
                }

                Spacer(modifier = Modifier.height(14.dp))

                DetailFieldRow(label = "Usuario", value = ticket.userName)
                DetailFieldRow(label = "Fecha", value = ticket.date)

                Spacer(modifier = Modifier.height(6.dp))

                // Machine row with quick AI diagnostic shortcut
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        Text(
                            text = "Maquina: ",
                            color = LozAiTextMuted,
                            fontSize = 13.sp
                        )
                        Text(
                            text = ticket.machineName,
                            color = LozAiCyan,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }

                    // Direct shortcut to LozAI Chat with this machine context!
                    Surface(
                        onClick = { onOpenLozAiForMachine(ticket.machineName) },
                        shape = RoundedCornerShape(8.dp),
                        color = LozAiPurplePrimary.copy(alpha = 0.2f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, LozAiPurplePrimary)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.SmartToy,
                                contentDescription = null,
                                tint = LozAiPurpleLight,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "LozAI Chat",
                                color = LozAiPurpleLight,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Descripcion del problema:",
                    color = LozAiTextMuted,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = ticket.description,
                    color = LozAiTextPrimary,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Action form: Cambiar estado y agregar seguimiento
                Text(
                    text = "Actualizar estado",
                    color = LozAiTextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Estado Dropdown
                Text(
                    text = "Estado",
                    color = LozAiTextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(6.dp))

                Box {
                    Surface(
                        onClick = { expandedStatusDropdown = true },
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
                                text = currentStatus.label,
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
                        expanded = expandedStatusDropdown,
                        onDismissRequest = { expandedStatusDropdown = false },
                        modifier = Modifier
                            .background(LozAiSurfaceElevated)
                            .border(1.dp, LozAiCardBorder, RoundedCornerShape(8.dp))
                    ) {
                        TicketStatus.values().forEach { st ->
                            DropdownMenuItem(
                                text = { Text(text = st.label, color = LozAiTextPrimary) },
                                onClick = {
                                    currentStatus = st
                                    expandedStatusDropdown = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Comentario de seguimiento
                Text(
                    text = "Comentario de seguimiento",
                    color = LozAiTextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = followUpComment,
                    onValueChange = { followUpComment = it },
                    placeholder = {
                        Text(
                            "Escribe el seguimiento realizado...",
                            color = LozAiTextMuted
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .testTag("tech_comment_input"),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF0B1120),
                        unfocusedContainerColor = Color(0xFF0B1120),
                        focusedBorderColor = LozAiCyan,
                        unfocusedBorderColor = LozAiCardBorder,
                        focusedTextColor = LozAiTextPrimary,
                        unfocusedTextColor = LozAiTextPrimary
                    ),
                    maxLines = 4
                )

                Spacer(modifier = Modifier.height(20.dp))

                GradientButton(
                    text = "Guardar cambios",
                    onClick = {
                        LozAiRepository.updateTicket(
                            ticketId = ticket.id,
                            newStatus = currentStatus,
                            comment = followUpComment
                        )
                        followUpComment = ""
                    },
                    testTag = "save_ticket_changes_button"
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Historial / Seguimiento Timeline
                Text(
                    text = "Historial de seguimiento",
                    color = LozAiTextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (ticket.history.isEmpty()) {
                    Text(
                        text = "Sin actualizaciones de seguimiento previas.",
                        color = LozAiTextMuted,
                        fontSize = 13.sp
                    )
                } else {
                    ticket.history.forEach { historyItem ->
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = LozAiSurfaceElevated,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = historyItem.author,
                                        color = LozAiSky,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(
                                        text = historyItem.date,
                                        color = LozAiTextMuted,
                                        fontSize = 11.sp
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = historyItem.comment,
                                    color = LozAiTextSecondary,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * 11. Equipos (Maquinas.png)
 */
@Composable
fun MachinesScreen(
    onNewMachineClick: () -> Unit,
    onEditMachineClick: (String) -> Unit,
    onChatWithMachineClick: (Machine) -> Unit
) {
    val machines by LozAiRepository.machines.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LozAiBg)
            .padding(bottom = 80.dp)
    ) {
        LozAiHeaderBanner(
            category = "Area Tecnica",
            title = "Maquinas",
            subtitle = "Consulta y administra los equipos registrados.",
            statusPillText = "Gestion de equipos"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Equipos registrados",
                    color = LozAiTextPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Total: ${machines.size}",
                    color = LozAiTextMuted,
                    fontSize = 12.sp
                )
            }

            Surface(
                onClick = onNewMachineClick,
                shape = RoundedCornerShape(10.dp),
                color = LozAiPurplePrimary,
                modifier = Modifier.testTag("new_machine_button")
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Nueva maquina",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(machines) { machine ->
                MachineItemCard(
                    machine = machine,
                    onChatClick = { onChatWithMachineClick(machine) },
                    onEditClick = { onEditMachineClick(machine.id) },
                    onToggleEnabled = { LozAiRepository.toggleMachineEnabled(machine.id) }
                )
            }
        }
    }
}

@Composable
fun MachineItemCard(
    machine: Machine,
    onChatClick: () -> Unit,
    onEditClick: () -> Unit,
    onToggleEnabled: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("machine_card_${machine.name}"),
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
                            .size(36.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(LozAiPurplePrimary.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Computer,
                            contentDescription = null,
                            tint = LozAiPurpleLight,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Column {
                        Text(
                            text = machine.name,
                            color = LozAiTextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "IP: ${machine.ip}",
                            color = LozAiSky,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                StatusPill(status = if (machine.isEnabled) "Habilitada" else "Deshabilitada")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Estado: ${machine.status}",
                    color = LozAiTextSecondary,
                    fontSize = 12.sp
                )
                Text(
                    text = machine.lastReview,
                    color = LozAiTextMuted,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Buttons row: [ 💬 Chat ] [ Editar ] [ Desactivar ]
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Chat button with LozAI
                Surface(
                    onClick = onChatClick,
                    modifier = Modifier.weight(1.2f),
                    shape = RoundedCornerShape(10.dp),
                    color = LozAiPurplePrimary,
                    border = androidx.compose.foundation.BorderStroke(1.dp, LozAiPurpleLight.copy(alpha = 0.3f))
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ChatBubbleOutline,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Chat",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }

                // Edit button
                Surface(
                    onClick = onEditClick,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    color = LozAiSurfaceElevated,
                    border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder)
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Editar",
                            color = LozAiTextSecondary,
                            fontWeight = FontWeight.Medium,
                            fontSize = 13.sp
                        )
                    }
                }

                // Desactivar / Activar button
                Surface(
                    onClick = onToggleEnabled,
                    modifier = Modifier.weight(1.1f),
                    shape = RoundedCornerShape(10.dp),
                    color = LozAiSurfaceElevated,
                    border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder)
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = if (machine.isEnabled) "Desactivar" else "Activar",
                            color = if (machine.isEnabled) LozAiTextMuted else LozAiSky,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

/**
 * 12 & 13. Machine Form Screen (Nueva maquina / Editar maquina)
 */
@Composable
fun MachineFormScreen(
    machineId: String?,
    onSaveSuccess: () -> Unit,
    onCancelClick: () -> Unit
) {
    val isEdit = machineId != null
    val machines by LozAiRepository.machines.collectAsState()
    val existingMachine = machines.firstOrNull { it.id == machineId }

    var name by remember(existingMachine) { mutableStateOf(existingMachine?.name ?: "") }
    var ip by remember(existingMachine) { mutableStateOf(existingMachine?.ip ?: "") }
    var status by remember(existingMachine) { mutableStateOf(existingMachine?.status ?: "Activa") }
    var expandedStatus by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LozAiBg)
            .verticalScroll(scrollState)
            .padding(bottom = 80.dp)
    ) {
        LozAiHeaderBanner(
            category = "Area Tecnica",
            title = if (isEdit) "Editar maquina" else "Nueva maquina",
            subtitle = if (isEdit) "Modifica los datos del equipo." else "Registra un nuevo equipo en el sistema.",
            statusPillText = "Gestion de equipos"
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
                    text = if (isEdit) "Editar equipo" else "Registrar equipo",
                    color = LozAiTextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(18.dp))

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
                    placeholder = { Text("Ej: CC20", color = LozAiTextMuted) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("machine_name_input"),
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

                // Field: Direccion IP
                Text(
                    text = "Direccion IP",
                    color = LozAiTextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = ip,
                    onValueChange = { ip = it },
                    placeholder = { Text("Ej: 192.168.1.10", color = LozAiTextMuted) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("machine_ip_input"),
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

                // Field: Estado
                Text(
                    text = "Estado",
                    color = LozAiTextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(6.dp))

                Box {
                    Surface(
                        onClick = { expandedStatus = true },
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
                                text = status,
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
                        expanded = expandedStatus,
                        onDismissRequest = { expandedStatus = false },
                        modifier = Modifier
                            .background(LozAiSurfaceElevated)
                            .border(1.dp, LozAiCardBorder, RoundedCornerShape(8.dp))
                    ) {
                        listOf("Activa", "Inactiva", "En mantenimiento").forEach { st ->
                            DropdownMenuItem(
                                text = { Text(text = st, color = LozAiTextPrimary) },
                                onClick = {
                                    status = st
                                    expandedStatus = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Buttons: [ Cancelar ] [ Crear maquina / Guardar cambios ]
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
                        text = if (isEdit) "Guardar cambios" else "Crear máquina",
                        enabled = name.isNotBlank() && ip.isNotBlank(),
                        onClick = {
                            if (isEdit && machineId != null) {
                                LozAiRepository.updateMachine(machineId, name, ip, status)
                            } else {
                                LozAiRepository.addMachine(name, ip, status)
                            }
                            onSaveSuccess()
                        },
                        modifier = Modifier.weight(1.4f),
                        testTag = "save_machine_button"
                    )
                }
            }
        }
    }
}
