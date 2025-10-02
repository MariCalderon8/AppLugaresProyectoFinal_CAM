package eam.edu.co.applugaresproyectofinal.ui.screens.user.nav


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs.CreatePlaceScreen
import eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs.FavoritesScreen
import eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs.MapScreen
import eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs.MyPlacesScreen
import eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs.ProfileScreen
import eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs.UpdateProfileScreen

@Composable
fun ContentUser(
    padding: PaddingValues,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = RouteTab.Map,
        modifier = Modifier.padding(padding)
    ) {
        composable<RouteTab.Map> {
            MapScreen()
        }
        composable<RouteTab.Favorites> {
            FavoritesScreen()
        }
        composable<RouteTab.Profile> {
            ProfileScreen(
                onNavigateToUpdateProfile = {
                    navController.navigate(RouteTab.UpdateProfile)
                },
                onNavigateToMyPlaces = {
                    navController.navigate(RouteTab.MyPlaces)
                }
            )
        }
        composable<RouteTab.CreatePlace> {
            CreatePlaceScreen(
                onNavigateToMyPlaces = {
                    navController.navigate(RouteTab.MyPlaces)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable<RouteTab.MyPlaces> {
            MyPlacesScreen()
        }
        composable<RouteTab.UpdateProfile> {
            UpdateProfileScreen(
                onNavitageToProfile = {
                    navController.navigate(RouteTab.Profile)
                }
            )
        }
    }
}