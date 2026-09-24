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
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Schedule
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
import com.example.model.SupportTicket
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
 * 4. User Dashboard Screen (Usuario dash.png)
 */
@Composable
fun UserDashboardScreen(
    currentUser: LozAiUser,
    onCreateTicketClick: () -> Unit,
    onViewTicketsClick: () -> Unit
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
            category = "Portal de Asistencia",
            title = "Hola, ${currentUser.name}",
            subtitle = "Crea solicitudes y consulta su estado.",
            statusPillText = "Soporte disponible"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Section: SOPORTE - Que necesitas hacer
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "SOPORTE",
                color = LozAiCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Text(
                text = "Que necesitas hacer",
                color = LozAiTextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
            )

            // Card 1: Crear ticket
            Surface(
                onClick = onCreateTicketClick,
                shape = RoundedCornerShape(16.dp),
                color = LozAiSurface,
                border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .testTag("user_dash_create_ticket_card")
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
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = LozAiPurpleLight,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "NUEVA SOLICITUD",
                        color = LozAiCyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Crear ticket",
                        color = LozAiTextPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Text(
                        text = "Informa un problema para recibir asistencia tecnica.",
                        color = LozAiTextSecondary,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Crear",
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

            // Card 2: Mis tickets
            Surface(
                onClick = onViewTicketsClick,
                shape = RoundedCornerShape(16.dp),
                color = LozAiSurface,
                border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .testTag("user_dash_my_tickets_card")
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
                            imageVector = Icons.Default.Schedule,
                            contentDescription = null,
                            tint = LozAiSky,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "SEGUIMIENTO",
                        color = LozAiCyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Mis tickets",
                        color = LozAiTextPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Text(
                        text = "Consulta el estado de tus solicitudes.",
                        color = LozAiTextSecondary,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Consultar",
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

            // Section: PROCESO - Como funciona
            Text(
                text = "PROCESO",
                color = LozAiCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Como funciona",
                color = LozAiTextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
            )

            // Step 1
            StepProcessCard(step = "1", title = "Crear", desc = "Describe el problema.")
            Spacer(modifier = Modifier.height(8.dp))
            // Step 2
            StepProcessCard(step = "2", title = "Revision", desc = "Un tecnico analiza el caso.")
            Spacer(modifier = Modifier.height(8.dp))
            // Step 3
            StepProcessCard(step = "3", title = "Seguimiento", desc = "Consulta los avances.")
        }
    }
}

@Composable
fun StepProcessCard(step: String, title: String, desc: String) {
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
                    .size(28.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(LozAiPurplePrimary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = step,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    color = LozAiTextPrimary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
                Text(
                    text = desc,
                    color = LozAiTextMuted,
                    fontSize = 12.sp
                )
            }
        }
    }
}

/**
 * 5. Create Ticket Screen (Crear ticket.png)
 */
@Composable
fun CreateTicketScreen(
    onTicketCreated: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val machines by LozAiRepository.machines.collectAsState()
    var selectedMachine by remember { mutableStateOf(machines.firstOrNull()?.name ?: "CC20") }
    var description by remember { mutableStateOf("") }
    var expandedMachineDropdown by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LozAiBg)
            .verticalScroll(scrollState)
            .padding(bottom = 80.dp)
    ) {
        LozAiHeaderBanner(
            category = "Soporte",
            title = "Crear ticket",
            subtitle = "Describe el problema para recibir asistencia tecnica.",
            statusPillText = "Soporte disponible"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .testTag("create_ticket_form_card"),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = LozAiSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Nueva solicitud",
                    color = LozAiTextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Maquina dropdown selector (Required per brief)
                Text(
                    text = "Maquina *",
                    color = LozAiTextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(6.dp))

                Box {
                    Surface(
                        onClick = { expandedMachineDropdown = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .testTag("machine_dropdown_trigger"),
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
                                text = selectedMachine,
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
                        expanded = expandedMachineDropdown,
                        onDismissRequest = { expandedMachineDropdown = false },
                        modifier = Modifier
                            .background(LozAiSurfaceElevated)
                            .border(1.dp, LozAiCardBorder, RoundedCornerShape(8.dp))
                    ) {
                        machines.forEach { machine ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "${machine.name} (${machine.ip})",
                                        color = LozAiTextPrimary
                                    )
                                },
                                onClick = {
                                    selectedMachine = machine.name
                                    expandedMachineDropdown = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Descripcion del problema
                Text(
                    text = "Descripcion del problema",
                    color = LozAiTextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = {
                        Text(
                            "Explica que problema estas teniendo...",
                            color = LozAiTextMuted
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 130.dp)
                        .testTag("ticket_description_input"),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF0B1120),
                        unfocusedContainerColor = Color(0xFF0B1120),
                        focusedBorderColor = LozAiCyan,
                        unfocusedBorderColor = LozAiCardBorder,
                        focusedTextColor = LozAiTextPrimary,
                        unfocusedTextColor = LozAiTextPrimary
                    ),
                    maxLines = 6
                )

                Spacer(modifier = Modifier.height(24.dp))

                GradientButton(
                    text = "Crear ticket",
                    enabled = description.isNotBlank(),
                    onClick = {
                        val ticket = LozAiRepository.createTicket(
                            machineName = selectedMachine,
                            description = description
                        )
                        onTicketCreated(ticket.id)
                    },
                    testTag = "submit_create_ticket_button"
                )
            }
        }
    }
}

/**
 * 6. User Tickets Screen (Mis tickets.png)
 */
@Composable
fun UserTicketsScreen(
    onSelectTicket: (String) -> Unit,
    onCreateTicketClick: () -> Unit
) {
    val tickets by LozAiRepository.tickets.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LozAiBg)
            .padding(bottom = 80.dp)
    ) {
        LozAiHeaderBanner(
            category = "Soporte",
            title = "Mis tickets",
            subtitle = "Consulta el estado y seguimiento de tus solicitudes.",
            statusPillText = "Soporte disponible"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Solicitudes",
                color = LozAiTextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Surface(
                onClick = onCreateTicketClick,
                shape = RoundedCornerShape(10.dp),
                color = LozAiPurplePrimary,
                modifier = Modifier.testTag("new_ticket_top_button")
            ) {
                Text(
                    text = "Nuevo ticket",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(tickets) { ticket ->
                UserTicketItemCard(
                    ticket = ticket,
                    onClick = { onSelectTicket(ticket.id) }
                )
            }
        }
    }
}

@Composable
fun UserTicketItemCard(
    ticket: SupportTicket,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = LozAiSurface,
        border = androidx.compose.foundation.BorderStroke(1.dp, LozAiCardBorder),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("user_ticket_card_${ticket.number}")
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
                    text = "Máquina: ${ticket.machineName}",
                    color = LozAiCyan,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = ticket.date,
                    color = LozAiTextMuted,
                    fontSize = 12.sp
                )
            }
        }
    }
}

/**
 * 7. Detalle del ticket del usuario (Mis tickets-Seleccionado.png)
 */
@Composable
fun UserTicketDetailScreen(
    ticketId: String,
    onBackClick: () -> Unit
) {
    val tickets by LozAiRepository.tickets.collectAsState()
    val ticket = tickets.firstOrNull { it.id == ticketId } ?: tickets.first()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LozAiBg)
            .verticalScroll(scrollState)
            .padding(bottom = 80.dp)
    ) {
        // Top Back Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.testTag("user_ticket_detail_back_button")
            ) {
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
            category = "Soporte",
            title = "Ticket #${ticket.number}",
            subtitle = "Consulta el estado y seguimiento de tu caso.",
            statusPillText = ticket.status.label
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Ticket Details Card
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
                    text = "Información del ticket",
                    color = LozAiTextPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                DetailFieldRow(label = "Estado", value = ticket.status.label)
                DetailFieldRow(label = "Maquina", value = ticket.machineName)
                DetailFieldRow(label = "Fecha", value = ticket.date)

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Descripcion:",
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

                // Seguimiento section
                Text(
                    text = "Seguimiento",
                    color = LozAiTextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                if (ticket.history.isEmpty()) {
                    Text(
                        text = "Este ticket todavia no tiene actualizaciones.",
                        color = LozAiTextMuted,
                        fontSize = 13.sp
                    )
                } else {
                    ticket.history.forEach { historyItem ->
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = LozAiSurfaceElevated,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = historyItem.author,
                                        color = LozAiSky,
                                        fontSize = 12.sp,
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

        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier.padding(horizontal = 16.dp)) {
            SecondaryButton(
                text = "Volver a mis tickets",
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun DetailFieldRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label:",
            color = LozAiTextMuted,
            fontSize = 13.sp
        )
        Text(
            text = value,
            color = LozAiTextPrimary,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp
        )
    }
}
