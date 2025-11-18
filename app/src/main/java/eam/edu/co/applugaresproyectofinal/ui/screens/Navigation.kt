package eam.edu.co.applugaresproyectofinal.ui.screens


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eam.edu.co.applugaresproyectofinal.model.Role
import eam.edu.co.applugaresproyectofinal.ui.screens.admin.ModerationScreen
import eam.edu.co.applugaresproyectofinal.ui.screens.admin.nav.HomeAdmin
import eam.edu.co.applugaresproyectofinal.ui.screens.shared.AuthScreen
import eam.edu.co.applugaresproyectofinal.ui.screens.user.HomeUser
import eam.edu.co.applugaresproyectofinal.ui.screens.shared.RecoverPasswordEmailScreen
import eam.edu.co.applugaresproyectofinal.utils.SharedPrefsUtil
import eam.edu.co.applugaresproyectofinal.viewModel.MainViewModel

val LocalMainViewModel = staticCompositionLocalOf<MainViewModel> { error("No MainViewModel provided") }
@Composable
fun Navigation(
    mainViewModel: MainViewModel
){
    val context = LocalContext.current
    val navController = rememberNavController()
    val user = SharedPrefsUtil.getPreferences(context)

    var startDestination = if (user.isEmpty()){
        RouteScreen.Auth
    }else{
        if(user["role"] == Role.ADMIN.value){
            RouteScreen.HomeAdmin
        }else{
            RouteScreen.HomeUser
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        CompositionLocalProvider (
            LocalMainViewModel provides mainViewModel
        ) {
            NavHost(
                navController = navController,
                startDestination = startDestination

            ) {
                composable<RouteScreen.Auth> {
                    AuthScreen(
                        onNavigateToHome = { userId, role ->
                            SharedPrefsUtil.savePreferences(context, userId, role)
                            if(role == Role.ADMIN){
                                navController.navigate(RouteScreen.HomeAdmin)
                            }else{
                                navController.navigate(RouteScreen.HomeUser)

                            }
                        },
                        onNavigateToRecoverPasswordEmail = {
                            navController.navigate(RouteScreen.RecoverPasswordEmail)
                        }
                    )
                }

                composable<RouteScreen.HomeUser> {
                    HomeUser(
                        onLogout = {
                            mainViewModel.usersViewModel.logout(context)
                            navController.navigate(RouteScreen.Auth){
                                popUpTo(0)
                            }
                        }
                    )
                }

                composable<RouteScreen.HomeAdmin> {
                    HomeAdmin (
                        onLogout = {
                            mainViewModel.usersViewModel.logout(context)
                            navController.navigate(RouteScreen.Auth){
                                popUpTo(0)
                            }
                        },
                    )
                }

                composable<RouteScreen.RecoverPasswordEmail> {
                    RecoverPasswordEmailScreen(
                        onContinue = { email ->
                            navController.navigate(RouteScreen.Auth)
                        },
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}