package com.example

import com.example.data.LozAiRepository
import com.example.model.TicketStatus
import com.example.model.UserRole
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class LozAiRepositoryTest {

    @Test
    fun testInitialDataSetup() {
        val users = LozAiRepository.usersList.value
        val machines = LozAiRepository.machines.value
        val tickets = LozAiRepository.tickets.value

        assertTrue(users.isNotEmpty())
        assertTrue(machines.any { it.name == "CC20" })
        assertTrue(tickets.any { it.number == 25 })
    }

    @Test
    fun testCreateTicket() {
        val created = LozAiRepository.createTicket("CC20", "Prueba de diagnóstico")
        assertNotNull(created)
        assertEquals("CC20", created.machineName)
        assertEquals(TicketStatus.PENDIENTE, created.status)
        assertTrue(LozAiRepository.tickets.value.any { it.id == created.id })
    }

    @Test
    fun testUpdateTicketStatus() {
        LozAiRepository.updateTicket("t_25", TicketStatus.EN_PROCESO, "Iniciando análisis")
        val ticket = LozAiRepository.tickets.value.first { it.id == "t_25" }
        assertEquals(TicketStatus.EN_PROCESO, ticket.status)
        assertTrue(ticket.history.any { it.comment == "Iniciando análisis" })
    }

    @Test
    fun testToggleMachineState() {
        val initialEnabled = LozAiRepository.machines.value.first { it.id == "m_1" }.isEnabled
        LozAiRepository.toggleMachineEnabled("m_1")
        val toggled = LozAiRepository.machines.value.first { it.id == "m_1" }.isEnabled
        assertEquals(!initialEnabled, toggled)
    }
}
