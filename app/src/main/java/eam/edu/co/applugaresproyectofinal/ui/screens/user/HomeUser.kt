package eam.edu.co.applugaresproyectofinal.ui.screens.user

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import eam.edu.co.applugaresproyectofinal.ui.screens.user.nav.ContentUser
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.ui.components.AlertDialogCustom
import eam.edu.co.applugaresproyectofinal.ui.screens.user.nav.NavigationConfig
import eam.edu.co.applugaresproyectofinal.ui.screens.user.nav.RouteTab

@Composable
fun HomeUser(
    onLogout: () -> Unit
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination =
        NavigationConfig.entries.find { it.route::class.qualifiedName == navBackStackEntry?.destination?.route }

    // Estado para controlar el diálogo
    var showDialog by remember { mutableStateOf(false) }

    fun safeBack() {
        if (currentDestination?.canLoseChanges == true) {
            showDialog = true
        } else {
            navController.popBackStack()
        }
    }


    // Diálogo de confirmación
    if (showDialog) {
        AlertDialogCustom (
            title = stringResource(R.string.title_lose_changes),
            text = stringResource(R.string.text_lose_changes),
            labelButtonConfirm = stringResource(R.string.btn_confirm),
            labelButtonDismiss = stringResource(R.string.btn_cancel),
            onDismiss = { showDialog = false },
            onConfirm = {
                showDialog = false
                navController.popBackStack()
            },
        )
    }

    Scaffold(
        floatingActionButton = {
            if (currentDestination?.showFAB == true) {
                FloatingActionButton(
                    onClick = { navController.navigate(RouteTab.CreatePlace) },
                    shape = CircleShape,
                    containerColor = Color(0xFF6A1B9A)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.label_create_place),
                        tint = Color.White,
                    )
                }
            }
        },
        topBar = {
            currentDestination?.topBar?.invoke(::safeBack)
        },
        bottomBar = {
            if (currentDestination?.showBottomBar == true) {
                NavigationBar {
                    NavigationConfig.entries
                        .filter { it.showInBottomMenu }
                        .forEach { destination ->
                            val isSelected = currentDestination?.route == destination.route

                            NavigationBarItem(
                                selected = isSelected,
                                onClick = { navController.navigate(destination.route) },
                                icon = {
                                    if (destination.icon != null && destination.label != null) {
                                        Icon(
                                            imageVector = destination.icon,
                                            contentDescription = stringResource(destination.label)
                                        )
                                    }
                                },
                                label = {
                                    if (destination.label != null) {
                                        Text(
                                            text = stringResource(destination.label)
                                        )
                                    }
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Color.Gray,
                                    selectedTextColor = Color.Gray,
                                    indicatorColor = Color(0xFFD1C4E9),
                                    unselectedIconColor = Color.Gray,
                                    unselectedTextColor = Color.Gray
                                )
                            )
                        }
                }
            }
        }
    ) { padding ->
        ContentUser(
            padding = padding,
            navController = navController,
            onLogout = { onLogout() }
        )
    }


}
