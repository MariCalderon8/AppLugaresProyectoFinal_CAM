package eam.edu.co.applugaresproyectofinal.ui.screens


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eam.edu.co.applugaresproyectofinal.ui.screens.shared.AuthScreen
import eam.edu.co.applugaresproyectofinal.ui.screens.user.HomeUser
import eam.edu.co.applugaresproyectofinal.ui.screens.shared.RecoverPasswordEmailScreen
import eam.edu.co.applugaresproyectofinal.ui.screens.shared.RecoverPasswordNewPasswordScreen
import eam.edu.co.applugaresproyectofinal.ui.screens.shared.RecoverPasswordCodeScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        NavHost(
            navController = navController,
            startDestination = RouteScreen.Auth

        ) {
            composable<RouteScreen.Auth>{
                AuthScreen(
                    onNavigateToHome = {
                        navController.navigate(RouteScreen.Home)
                    },
                    onNavigateToRecoverPasswordEmail = {
                        navController.navigate(RouteScreen.RecoverPasswordEmail)
                    }
                )
            }

            composable<RouteScreen.Home> {
                HomeUser()
            }

            composable<RouteScreen.RecoverPasswordEmail> {
                RecoverPasswordEmailScreen(
                    onContinue = { email ->
                        navController.navigate(RouteScreen.RecoverPasswordCode)
                    },
                    onBack = { navController.popBackStack() }
                )
            }

            composable<RouteScreen.RecoverPasswordCode> {
                RecoverPasswordCodeScreen(
                    onContinue = { code ->
                        navController.navigate(RouteScreen.RecoverPasswordNewPassword)
                    },
                    onBack = { navController.popBackStack() }
                )
            }

            composable<RouteScreen.RecoverPasswordNewPassword> {
                RecoverPasswordNewPasswordScreen(
                    onChangePassword = { newPass, confirmPass ->
                        navController.navigate(RouteScreen.Auth) {
                            popUpTo(RouteScreen.Auth) { inclusive = true }
                        }
                    },
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}