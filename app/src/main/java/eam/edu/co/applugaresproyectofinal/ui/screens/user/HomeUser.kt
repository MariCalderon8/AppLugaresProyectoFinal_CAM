package eam.edu.co.applugaresproyectofinal.ui.screens.user

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import eam.edu.co.applugaresproyectofinal.ui.screens.user.nav.ContentUser
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.ui.screens.user.nav.RouteTab


@Composable
fun HomeUser() {
    val navController = rememberNavController()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton (
                onClick = {
                    navController.navigate(RouteTab.CreatePlace)
                },
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
        ,
//        topBar = {
//            TopBarUser(
//                navController = navController
//            )
//        }
//        ,
        bottomBar = {
            BottomBarUser(
                navController = navController
            )
        },
    ) { padding ->
        ContentUser(
            padding = padding,
            navController = navController
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarUser(
    navController: NavHostController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    when (currentRoute) {
        RouteTab.Map::class.qualifiedName -> {}
        RouteTab.Favorites::class.qualifiedName -> {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.label_favorites)) }
            )
        }

        RouteTab.Profile::class.qualifiedName -> {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.label_profile)) }
            )
        }

        else -> {}
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarUser(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar() {
        Destination.entries.forEachIndexed { index, destination ->
            val isSelected = currentDestination?.route == destination.route::class.qualifiedName
            NavigationBarItem(
                label = {
                    Text(
                        text = stringResource(destination.label)
                    )
                },
                onClick = {
                    navController.navigate(destination.route)
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = stringResource(destination.label)
                    )
                },
                selected = isSelected,
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

enum class Destination(
    val route: RouteTab,
    val label: Int,
    val icon: ImageVector,
){
    HOME(route = RouteTab.Map, R.string.label_map, Icons.Default.Map),
    FAVORITES(route = RouteTab.Favorites, R.string.label_favorites, Icons.Default.Favorite),
    PROFILE(route = RouteTab.Profile, R.string.label_profile, Icons.Default.AccountCircle)
}