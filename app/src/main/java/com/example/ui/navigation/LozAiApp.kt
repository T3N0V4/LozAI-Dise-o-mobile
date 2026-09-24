package com.example.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.data.LozAiRepository
import com.example.model.UserRole
import com.example.ui.components.LozAiBottomNav
import com.example.ui.components.LozAiTopBar
import com.example.ui.components.RoleSwitchDialog
import com.example.ui.screens.AdminDashboardScreen
import com.example.ui.screens.CreateTicketScreen
import com.example.ui.screens.EditUserScreen
import com.example.ui.screens.LoginScreen
import com.example.ui.screens.LozAiChatScreen
import com.example.ui.screens.MachineFormScreen
import com.example.ui.screens.MachinesScreen
import com.example.ui.screens.RegisterScreen
import com.example.ui.screens.SplashScreen
import com.example.ui.screens.TechDashboardScreen
import com.example.ui.screens.TechTicketDetailScreen
import com.example.ui.screens.TechTicketsScreen
import com.example.ui.screens.UserDashboardScreen
import com.example.ui.screens.UserTicketDetailScreen
import com.example.ui.screens.UserTicketsScreen
import com.example.ui.screens.UsersListScreen
import com.example.ui.theme.LozAiBg

@Composable
fun LozAiApp(navController: NavHostController = rememberNavController()) {
    val currentUser by LozAiRepository.currentUser.collectAsState()
    val machines by LozAiRepository.machines.collectAsState()
    val selectedTicketId by LozAiRepository.selectedTicketId.collectAsState()
    val selectedMachineId by LozAiRepository.selectedMachineId.collectAsState()
    val selectedUserId by LozAiRepository.selectedUserId.collectAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "splash"

    var showRoleSwitchDialog by remember { mutableStateOf(false) }

    val isAuthScreen = currentRoute == "splash" || currentRoute == "login" || currentRoute == "register"

    RoleSwitchDialog(
        isOpen = showRoleSwitchDialog,
        onDismiss = { showRoleSwitchDialog = false },
        onSelectUser = { selectedUser ->
            LozAiRepository.setCurrentUser(selectedUser)
            val dest = when (selectedUser.role) {
                UserRole.TECH -> "tech_dash"
                UserRole.USER -> "user_dash"
                UserRole.ADMIN -> "admin_dash"
            }
            navController.navigate(dest) {
                popUpTo("login") { inclusive = false }
                launchSingleTop = true
            }
        }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = LozAiBg,
        topBar = {
            if (!isAuthScreen) {
                LozAiTopBar(
                    currentUser = currentUser,
                    onRoleSwitchClick = { showRoleSwitchDialog = true },
                    onLogoutClick = {
                        navController.navigate("login") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
        },
        bottomBar = {
            if (!isAuthScreen && currentRoute != "chat_lozai") {
                LozAiBottomNav(
                    userRole = currentUser.role,
                    currentScreen = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(
                                when (currentUser.role) {
                                    UserRole.TECH -> "tech_dash"
                                    UserRole.USER -> "user_dash"
                                    UserRole.ADMIN -> "admin_dash"
                                }
                            ) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(LozAiBg)
        ) {
            NavHost(
                navController = navController,
                startDestination = "splash",
                enterTransition = { fadeIn(animationSpec = tween(220)) },
                exitTransition = { fadeOut(animationSpec = tween(180)) },
                popEnterTransition = { fadeIn(animationSpec = tween(220)) },
                popExitTransition = { fadeOut(animationSpec = tween(180)) }
            ) {
                // 1. Splash
                composable("splash") {
                    SplashScreen(
                        onTimeout = {
                            navController.navigate("login") {
                                popUpTo("splash") { inclusive = true }
                            }
                        }
                    )
                }

                // 2. Login
                composable("login") {
                    LoginScreen(
                        onLoginSuccess = { user ->
                            val dest = when (user.role) {
                                UserRole.TECH -> "tech_dash"
                                UserRole.USER -> "user_dash"
                                UserRole.ADMIN -> "admin_dash"
                            }
                            navController.navigate(dest) {
                                popUpTo("login") { inclusive = true }
                            }
                        },
                        onNavigateToRegister = {
                            navController.navigate("register")
                        }
                    )
                }

                // 3. Register
                composable("register") {
                    RegisterScreen(
                        onRegisterSuccess = { user ->
                            navController.navigate("user_dash") {
                                popUpTo("login") { inclusive = true }
                            }
                        },
                        onNavigateToLogin = {
                            navController.popBackStack()
                        }
                    )
                }

                // 4. User Dashboard
                composable("user_dash") {
                    UserDashboardScreen(
                        currentUser = currentUser,
                        onCreateTicketClick = { navController.navigate("create_ticket") },
                        onViewTicketsClick = { navController.navigate("user_tickets") }
                    )
                }

                // 5. Create Ticket
                composable("create_ticket") {
                    CreateTicketScreen(
                        onTicketCreated = { ticketId ->
                            LozAiRepository.setSelectedTicketId(ticketId)
                            navController.navigate("user_tickets") {
                                popUpTo("user_dash")
                            }
                        },
                        onBackClick = { navController.popBackStack() }
                    )
                }

                // 6. User Tickets List
                composable("user_tickets") {
                    UserTicketsScreen(
                        onSelectTicket = { ticketId ->
                            LozAiRepository.setSelectedTicketId(ticketId)
                            navController.navigate("user_ticket_detail")
                        },
                        onCreateTicketClick = {
                            navController.navigate("create_ticket")
                        }
                    )
                }

                // 7. User Ticket Detail
                composable("user_ticket_detail") {
                    UserTicketDetailScreen(
                        ticketId = selectedTicketId ?: "t_25",
                        onBackClick = { navController.popBackStack() }
                    )
                }

                // 8. Tech Dashboard
                composable("tech_dash") {
                    TechDashboardScreen(
                        currentUser = currentUser,
                        onViewTicketsClick = { navController.navigate("tech_tickets") },
                        onViewMachinesClick = { navController.navigate("machines") },
                        onOpenLozAiChatClick = {
                            val defaultMachine = machines.firstOrNull() ?: com.example.model.Machine("m_1", "CC20", "192.168.1.10", "Activa")
                            LozAiRepository.setActiveChatMachine(defaultMachine)
                            navController.navigate("chat_lozai")
                        }
                    )
                }

                // 9. Tech Tickets List
                composable("tech_tickets") {
                    TechTicketsScreen(
                        onSelectTicket = { ticketId ->
                            LozAiRepository.setSelectedTicketId(ticketId)
                            navController.navigate("tech_ticket_detail")
                        }
                    )
                }

                // 10. Tech Ticket Detail
                composable("tech_ticket_detail") {
                    TechTicketDetailScreen(
                        ticketId = selectedTicketId ?: "t_25",
                        onBackClick = { navController.popBackStack() },
                        onOpenLozAiForMachine = { machineName ->
                            val matchMachine = machines.firstOrNull { it.name.equals(machineName, ignoreCase = true) }
                                ?: com.example.model.Machine("m_target", machineName, "192.168.1.10", "Activa")
                            LozAiRepository.setActiveChatMachine(matchMachine)
                            navController.navigate("chat_lozai")
                        }
                    )
                }

                // 11. Machines Screen
                composable("machines") {
                    MachinesScreen(
                        onNewMachineClick = {
                            LozAiRepository.setSelectedMachineId(null)
                            navController.navigate("new_machine")
                        },
                        onEditMachineClick = { machineId ->
                            LozAiRepository.setSelectedMachineId(machineId)
                            navController.navigate("edit_machine")
                        },
                        onChatWithMachineClick = { machine ->
                            LozAiRepository.setActiveChatMachine(machine)
                            navController.navigate("chat_lozai")
                        }
                    )
                }

                // 12. New Machine
                composable("new_machine") {
                    MachineFormScreen(
                        machineId = null,
                        onSaveSuccess = { navController.popBackStack() },
                        onCancelClick = { navController.popBackStack() }
                    )
                }

                // 13. Edit Machine
                composable("edit_machine") {
                    MachineFormScreen(
                        machineId = selectedMachineId,
                        onSaveSuccess = { navController.popBackStack() },
                        onCancelClick = { navController.popBackStack() }
                    )
                }

                // 14. Chat LozAI Screen
                composable("chat_lozai") {
                    LozAiChatScreen(
                        onBackToMachines = { navController.popBackStack() }
                    )
                }

                // 15. Admin Dashboard
                composable("admin_dash") {
                    AdminDashboardScreen(
                        currentUser = currentUser,
                        onManageUsersClick = { navController.navigate("users_list") }
                    )
                }

                // 16. Admin Users List
                composable("users_list") {
                    UsersListScreen(
                        onEditUserClick = { userId ->
                            LozAiRepository.setSelectedUserId(userId)
                            navController.navigate("edit_user")
                        }
                    )
                }

                // 17. Admin Edit User
                composable("edit_user") {
                    EditUserScreen(
                        userId = selectedUserId ?: "adm_azul",
                        onSaveSuccess = { navController.popBackStack() },
                        onCancelClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
