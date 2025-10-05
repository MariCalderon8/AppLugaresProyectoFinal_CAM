package eam.edu.co.applugaresproyectofinal.ui.screens.admin.nav

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import eam.edu.co.applugaresproyectofinal.ui.screens.admin.ModerationScreen
import eam.edu.co.applugaresproyectofinal.ui.screens.admin.PlaceDetailAdminScreen

@Composable
fun HomeAdmin(
    onLogout: () -> Unit
){
    val navController = rememberNavController()
    ContentAdmin(
        navController = navController,
        onLogout = { onLogout() }
    )

}

@Composable
fun ContentAdmin(
    navController: NavHostController,
    onLogout: () -> Unit
){

    NavHost(
        navController = navController,
        startDestination = RoutesAdmin.ModerationScreen,
    ) {
        composable<RoutesAdmin.ModerationScreen> {
            ModerationScreen(
                onLogout = { onLogout() },
                onNavigateToPlaceDetail = { placeId ->
                    navController.navigate(RoutesAdmin.PlaceDetailAdmin(placeId))
                }
            )
        }


        composable<RoutesAdmin.PlaceDetailAdmin> {
            val args = it.toRoute<RoutesAdmin.PlaceDetailAdmin>()
            PlaceDetailAdminScreen(
                placeId = args.placeId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}