package com.example.data

import com.example.model.ChatMessage
import com.example.model.LozAiUser
import com.example.model.Machine
import com.example.model.SupportTicket
import com.example.model.TicketHistory
import com.example.model.TicketStatus
import com.example.model.ToolExecution
import com.example.model.UserRole
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

object LozAiRepository {
    private val scope = CoroutineScope(Dispatchers.Default)

    // Current active user for session (default: Mario - Técnico)
    private val _currentUser = MutableStateFlow(
        LozAiUser(
            id = "tech_mario",
            name = "Mario",
            email = "mario@lozai.com",
            role = UserRole.TECH,
            isActive = true
        )
    )
    val currentUser: StateFlow<LozAiUser> = _currentUser.asStateFlow()

    // Pre-defined personas for easy switching during academic presentation
    val sampleUsers = listOf(
        LozAiUser("tech_mario", "Mario", "mario@lozai.com", UserRole.TECH, true),
        LozAiUser("usr_celeste", "Celeste", "celeste@lozai.com", UserRole.USER, true),
        LozAiUser("adm_azul", "Azul", "usuario@lozai.com", UserRole.ADMIN, true),
        LozAiUser("tech_jano", "Jano 2", "jano@lozai.com.ar", UserRole.TECH, true),
        LozAiUser("tech_juan", "juan", "juan@gmail.com", UserRole.TECH, false),
        LozAiUser("tech_pedro", "Pedro Técnico", "tecnico@lozai.com", UserRole.TECH, true),
        LozAiUser("usr_prueba", "Prueba", "prueba@lozai.com", UserRole.USER, false),
        LozAiUser("usr_ricardo", "Ricardo", "Ricardo@gmail.com", UserRole.USER, true)
    )

    // Users List for Admin
    private val _usersList = MutableStateFlow(sampleUsers)
    val usersList: StateFlow<List<LozAiUser>> = _usersList.asStateFlow()

    // Machines List for Tech
    private val _machines = MutableStateFlow(
        listOf(
            Machine("m_1", "CC20", "192.168.1.10", "Activa", true, "Sin revisiones registradas"),
            Machine("m_2", "CC03", "192.168.1.34", "Activa", true, "Sin revisiones registradas"),
            Machine("m_3", "CC04", "192.168.2.3", "Activa", true, "Sin revisiones registradas"),
            Machine("m_4", "CC05", "192.168.5.14", "Activa", true, "Sin revisiones registradas"),
            Machine("m_5", "CC01", "192.168.1.20", "Activa", true, "Sin revisiones registradas"),
            Machine("m_6", "TANO", "192.168.3.15", "Activa", true, "Sin revisiones registradas"),
            Machine("m_7", "LAB-01", "192.168.1.102", "En mantenimiento", true, "Última revisión: 20/08/2026")
        )
    )
    val machines: StateFlow<List<Machine>> = _machines.asStateFlow()

    // Tickets List
    private val _tickets = MutableStateFlow(
        listOf(
            SupportTicket(
                id = "t_25",
                number = 25,
                description = "NO enciende",
                machineName = "CC20",
                userName = "Mario",
                date = "18/08/2026",
                status = TicketStatus.PENDIENTE,
                history = emptyList()
            ),
            SupportTicket(
                id = "t_20",
                number = 20,
                description = "La computadora no tiene conexión a Internet.",
                machineName = "CC03",
                userName = "Jano 2",
                date = "18/08/2026",
                status = TicketStatus.COMPLETADA,
                history = listOf(
                    TicketHistory(
                        id = "h_1",
                        date = "18/08/2026 14:30",
                        author = "Jano 2",
                        comment = "Revisión de cable de red y renovación de DHCP.",
                        status = TicketStatus.COMPLETADA
                    )
                )
            ),
            SupportTicket(
                id = "t_21",
                number = 21,
                description = "El equipo funciona muy lento al abrir programas.",
                machineName = "CC04",
                userName = "Prueba",
                date = "18/08/2026",
                status = TicketStatus.COMPLETADA,
                history = listOf(
                    TicketHistory(
                        id = "h_2",
                        date = "18/08/2026 16:15",
                        author = "Pedro Técnico",
                        comment = "Limpieza de temporales y optimización de servicios de inicio.",
                        status = TicketStatus.COMPLETADA
                    )
                )
            ),
            SupportTicket(
                id = "t_22",
                number = 22,
                description = "La computadora pierde la conexión de red de forma intermitente.",
                machineName = "CC20",
                userName = "Mario",
                date = "18/08/2026",
                status = TicketStatus.PENDIENTE,
                history = emptyList()
            ),
            SupportTicket(
                id = "t_23",
                number = 23,
                description = "El usuario informa que el equipo se congela durante el uso.",
                machineName = "CC05",
                userName = "juan",
                date = "18/08/2026",
                status = TicketStatus.PENDIENTE,
                history = emptyList()
            ),
            SupportTicket(
                id = "t_17",
                number = 17,
                description = "La computadora pierde la conexión de red de forma intermitente.",
                machineName = "CC03",
                userName = "Celeste",
                date = "18/08/2026",
                status = TicketStatus.PENDIENTE,
                history = emptyList()
            )
        )
    )
    val tickets: StateFlow<List<SupportTicket>> = _tickets.asStateFlow()

    // Selected machine for LozAI Chat
    private val _activeChatMachine = MutableStateFlow<Machine?>(_machines.value.firstOrNull())
    val activeChatMachine: StateFlow<Machine?> = _activeChatMachine.asStateFlow()

    // Chat messages map per machine ID
    private val _chatMessages = MutableStateFlow<Map<String, List<ChatMessage>>>(emptyMap())
    val chatMessages: StateFlow<Map<String, List<ChatMessage>>> = _chatMessages.asStateFlow()

    // Currently executing tool in chat
    private val _isChatExecutingTool = MutableStateFlow(false)
    val isChatExecutingTool: StateFlow<Boolean> = _isChatExecutingTool.asStateFlow()

    // Active ticket for detail viewing
    private val _selectedTicketId = MutableStateFlow<String?>("t_25")
    val selectedTicketId: StateFlow<String?> = _selectedTicketId.asStateFlow()

    // Machine editing state
    private val _selectedMachineId = MutableStateFlow<String?>("m_1")
    val selectedMachineId: StateFlow<String?> = _selectedMachineId.asStateFlow()

    // User editing state
    private val _selectedUserId = MutableStateFlow<String?>("adm_azul")
    val selectedUserId: StateFlow<String?> = _selectedUserId.asStateFlow()

    fun setCurrentUser(user: LozAiUser) {
        _currentUser.value = user
    }

    fun setSelectedTicketId(id: String?) {
        _selectedTicketId.value = id
    }

    fun setSelectedMachineId(id: String?) {
        _selectedMachineId.value = id
    }

    fun setSelectedUserId(id: String?) {
        _selectedUserId.value = id
    }

    fun setActiveChatMachine(machine: Machine) {
        _activeChatMachine.value = machine
    }

    // Ticket Actions
    fun createTicket(machineName: String, description: String): SupportTicket {
        val nextNumber = (_tickets.value.maxOfOrNull { it.number } ?: 25) + 1
        val newTicket = SupportTicket(
            id = "t_$nextNumber",
            number = nextNumber,
            description = description,
            machineName = machineName,
            userName = _currentUser.value.name,
            date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()),
            status = TicketStatus.PENDIENTE,
            history = emptyList()
        )
        _tickets.value = listOf(newTicket) + _tickets.value
        _selectedTicketId.value = newTicket.id
        return newTicket
    }

    fun updateTicket(ticketId: String, newStatus: TicketStatus, comment: String) {
        val currentTime = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
        _tickets.value = _tickets.value.map { ticket ->
            if (ticket.id == ticketId) {
                val updatedHistory = if (comment.isNotBlank()) {
                    ticket.history + TicketHistory(
                        id = UUID.randomUUID().toString(),
                        date = currentTime,
                        author = _currentUser.value.name,
                        comment = comment,
                        status = newStatus
                    )
                } else {
                    ticket.history
                }
                ticket.copy(status = newStatus, history = updatedHistory)
            } else {
                ticket
            }
        }
    }

    // Machine Actions
    fun addMachine(name: String, ip: String, status: String) {
        val newMachine = Machine(
            id = "m_${System.currentTimeMillis()}",
            name = name,
            ip = ip,
            status = status,
            isEnabled = true,
            lastReview = "Sin revisiones registradas"
        )
        _machines.value = _machines.value + newMachine
    }

    fun updateMachine(id: String, name: String, ip: String, status: String) {
        _machines.value = _machines.value.map {
            if (it.id == id) it.copy(name = name, ip = ip, status = status) else it
        }
        if (_activeChatMachine.value?.id == id) {
            _activeChatMachine.value = _machines.value.firstOrNull { it.id == id }
        }
    }

    fun toggleMachineEnabled(id: String) {
        _machines.value = _machines.value.map {
            if (it.id == id) it.copy(isEnabled = !it.isEnabled) else it
        }
    }

    // User Actions
    fun updateUser(id: String, name: String, email: String, role: UserRole) {
        _usersList.value = _usersList.value.map {
            if (it.id == id) it.copy(name = name, email = email, role = role) else it
        }
        if (_currentUser.value.id == id) {
            _currentUser.value = _currentUser.value.copy(name = name, email = email, role = role)
        }
    }

    fun toggleUserActive(id: String) {
        _usersList.value = _usersList.value.map {
            if (it.id == id) it.copy(isActive = !it.isActive) else it
        }
    }

    // LozAI Chat Simulation with real tool execution timeline
    fun sendChatMessage(machineId: String, query: String) {
        val machine = _machines.value.firstOrNull { it.id == machineId }
            ?: _activeChatMachine.value
            ?: Machine("m_default", "CC20", "192.168.1.10", "Activa")

        val timeStr = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        val userMsg = ChatMessage(
            id = UUID.randomUUID().toString(),
            isFromTech = true,
            text = query,
            timestamp = timeStr
        )

        val currentList = _chatMessages.value[machine.id] ?: emptyList()
        _chatMessages.value = _chatMessages.value + (machine.id to (currentList + userMsg))

        // Trigger AI response with tool simulation
        scope.launch {
            _isChatExecutingTool.value = true

            val isConnectivityQuery = query.contains("conectividad", ignoreCase = true) ||
                    query.contains("ping", ignoreCase = true) ||
                    query.contains("responde", ignoreCase = true)

            val isDiagnosticQuery = query.contains("diagnostico", ignoreCase = true) ||
                    query.contains("diagnóstico", ignoreCase = true) ||
                    query.contains("estado", ignoreCase = true)

            val toolName = if (isConnectivityQuery) "Ping" else if (isDiagnosticQuery) "Diagnóstico de Hardware" else "Inspección de Servicios"

            // 1. Initial tool call message with status running
            val toolMsgId = UUID.randomUUID().toString()
            val runningToolExecution = ToolExecution(
                toolName = toolName,
                target = "${machine.name} (${machine.ip})",
                isRunning = true,
                result = null
            )
            val intermediateMsg = ChatMessage(
                id = toolMsgId,
                isFromTech = false,
                text = "Iniciando análisis asistido en contexto de ${machine.name}...",
                timestamp = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()),
                toolExecution = runningToolExecution
            )

            val updatedList1 = (_chatMessages.value[machine.id] ?: emptyList()) + intermediateMsg
            _chatMessages.value = _chatMessages.value + (machine.id to updatedList1)

            // Simulate execution latency
            delay(1600)

            // 2. Tool completed state
            val toolOutput = when {
                isConnectivityQuery -> "4 packets transmitted, 4 received, 0% packet loss, time 3004ms\nrtt min/avg/max = 1.12/1.42/1.98 ms"
                isDiagnosticQuery -> "CPU: Intel Core i5-11400 (Usage: 12%)\nRAM: 16GB DDR4 (Usage: 4.2GB / 26%)\nDisk: Kingston NVMe 500GB (Health: 98% SMART OK)\nNetwork: Intel I219-V (Link UP, 1Gbps)"
                else -> "Services checked: WinRM [Running], SSH [Stopped], WMI [Active], Agent [v1.0.4 Online]"
            }

            val completedToolExecution = runningToolExecution.copy(
                isRunning = false,
                result = toolOutput
            )

            val finalAiText = when {
                isConnectivityQuery -> "La máquina ${machine.name} (${machine.ip}) responde correctamente con latencia promedio de 1.42ms y 0% de pérdida de paquetes.\n\nLa interfaz física y el stack de red se encuentran 100% operativos. Si el usuario reporta que no enciende o no navega, la falla apunta a configuración DNS local o alimentación del monitor."
                isDiagnosticQuery -> "El diagnóstico de hardware para ${machine.name} indica parámetros dentro de la norma: CPU al 12%, memoria disponible al 74% y almacenamiento NVMe con estado de salud óptimo (98%). No se detectan anomalías de temperatura ni cuellos de botella térmicos."
                else -> "Análisis completado para ${machine.name}. Los servicios esenciales de administración y telemetría están activos. Puedes solicitar comprobaciones adicionales o registrar el seguimiento en el ticket correspondiente."
            }

            // Update with result and final explanation
            val listAfterTool = _chatMessages.value[machine.id]?.map { msg ->
                if (msg.id == toolMsgId) {
                    msg.copy(
                        text = finalAiText,
                        toolExecution = completedToolExecution
                    )
                } else msg
            } ?: emptyList()

            _chatMessages.value = _chatMessages.value + (machine.id to listAfterTool)
            _isChatExecutingTool.value = false
        }
    }

    fun clearChat(machineId: String) {
        _chatMessages.value = _chatMessages.value + (machineId to emptyList())
    }
}
