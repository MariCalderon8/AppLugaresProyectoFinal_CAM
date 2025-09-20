package eam.edu.co.applugaresproyectofinal.ui.screens


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eam.edu.co.applugaresproyectofinal.ui.screens.shared.LoginScreen
import eam.edu.co.applugaresproyectofinal.ui.screens.shared.RegisterScreen
import eam.edu.co.applugaresproyectofinal.ui.screens.user.HomeUser

@Composable
fun Navigation(){
    val navController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        NavHost(
            navController = navController,
            startDestination = RouteScreen.Login

        ) {
            composable<RouteScreen.Login> {
                LoginScreen(
                    onNavigateToRegister = {
                        navController.navigate(RouteScreen.Register)
                    },
                    onNavigateToHome = {
                        navController.navigate(RouteScreen.Home)
                    }
                )
            }

            composable<RouteScreen.Register> {
                RegisterScreen(
                    onNavigateToLogin = {
                        navController.navigate(RouteScreen.Login)
                    }
                )
            }

            composable<RouteScreen.Home> {
                HomeUser()
            }
        }
    }
}