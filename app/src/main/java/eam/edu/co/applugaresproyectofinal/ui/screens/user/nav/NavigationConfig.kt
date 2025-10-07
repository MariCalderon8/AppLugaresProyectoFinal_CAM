package eam.edu.co.applugaresproyectofinal.ui.screens.user.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Map
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.ui.components.TopBarCustom


enum class NavigationConfig(
    val route: RouteTab,
    val label: Int?,
    val icon: ImageVector?,
    val showFAB: Boolean = false,
    val showInBottomMenu: Boolean = false,
    val canLoseChanges: Boolean = false
) {
    MAP(
        route = RouteTab.Map,
        label = R.string.label_map,
        icon = Icons.Default.Map
    ),
    FAVORITES(
        route = RouteTab.Favorites,
        label = R.string.label_favorites,
        icon = Icons.Default.Favorite,
    ),

    PROFILE(
        route = RouteTab.Profile,
        label = R.string.label_profile,
        icon = Icons.Default.AccountCircle,
    ),
    CREATE_PLACE(
        route = RouteTab.CreatePlace,
        label = null,
        icon = null
    ),
    UPDATE_PROFILE(
        route = RouteTab.UpdateProfile,
        icon = null,
        label = null,
    ),
    PLACE_DETAIL(
        route = RouteTab.PlaceDetail(""),
        icon = null,
        label = null,
    ),
    MY_PLACES(
        route = RouteTab.MyPlaces,
        icon = null,
        label = null,
    ),
    NEW_COMMENT(
        route = RouteTab.PlaceDetail(""),
        icon = null,
        label = null,
    ),
    NEW_REPORT(
        route = RouteTab.PlaceDetail(""),
        icon = null,
        label = null
    ),
}
