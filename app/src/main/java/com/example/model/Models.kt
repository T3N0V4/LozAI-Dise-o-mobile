package com.example.model

enum class UserRole(val label: String) {
    ADMIN("Administrador"),
    TECH("Técnico"),
    USER("Usuario")
}

data class LozAiUser(
    val id: String,
    val name: String,
    val email: String,
    val role: UserRole,
    val isActive: Boolean
)

data class Machine(
    val id: String,
    val name: String,
    val ip: String,
    val status: String, // "Activa", "Inactiva", "En mantenimiento"
    val isEnabled: Boolean = true,
    val lastReview: String = "Sin revisiones registradas"
)

enum class TicketStatus(val label: String) {
    PENDIENTE("Pendiente"),
    EN_PROCESO("En proceso"),
    COMPLETADA("Completada")
}

data class TicketHistory(
    val id: String,
    val date: String,
    val author: String,
    val comment: String,
    val status: TicketStatus
)

data class SupportTicket(
    val id: String,
    val number: Int,
    val description: String,
    val machineName: String,
    val userName: String,
    val date: String,
    val status: TicketStatus,
    val history: List<TicketHistory> = emptyList()
)

data class ToolExecution(
    val toolName: String,
    val target: String,
    val isRunning: Boolean,
    val result: String? = null
)

data class ChatMessage(
    val id: String,
    val isFromTech: Boolean,
    val text: String,
    val timestamp: String,
    val toolExecution: ToolExecution? = null
)
