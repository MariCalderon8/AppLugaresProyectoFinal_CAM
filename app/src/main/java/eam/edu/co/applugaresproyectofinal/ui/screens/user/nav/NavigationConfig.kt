package eam.edu.co.applugaresproyectofinal.ui.screens.user.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.ui.components.AppTopBarCustom


enum class NavigationConfig(
    val route: RouteTab,
    val label: Int?,
    val icon: ImageVector?,
    val showFAB: Boolean = false,
    val showBottomBar: Boolean = false,
    val showInBottomMenu: Boolean = false,
    val topBar: (@Composable ((() -> Unit) -> Unit))? = null,
    val canLoseChanges: Boolean = false
) {
    MAP(
        route = RouteTab.Map,
        label = R.string.label_map,
        icon = Icons.Default.Map,
        showFAB = true,
        showBottomBar = true,
        showInBottomMenu = true,
        topBar = {}
    ),
    FAVORITES(
        route = RouteTab.Favorites,
        label = R.string.label_favorites,
        icon = Icons.Default.Favorite,
        showBottomBar = true,
        showInBottomMenu = true,
        topBar =
            { _ ->
                AppTopBarCustom(
                    title = stringResource(R.string.label_favorites)
                )
            }
    ),

    PROFILE(
        route = RouteTab.Profile,
        label = R.string.label_profile,
        icon = Icons.Default.AccountCircle,
        showBottomBar = true,
        showInBottomMenu = true,
        topBar =
            { _ ->
                AppTopBarCustom(
                    title = stringResource(R.string.label_profile)
                )
            }
    ),
    CREATE_PLACE(
        route = RouteTab.CreatePlace,
        label = null,
        icon = null,
        topBar =
            { onSafeBack ->
                AppTopBarCustom(
                    title = stringResource(R.string.label_create_place),
                    showBack = true,
                    onBack = { onSafeBack() }
                )
            },
        canLoseChanges = true,
    ),
    UPDATE_PROFILE(
        route = RouteTab.UpdateProfile,
        icon = null,
        label = null,
        topBar =
            { onSafeBack ->
                AppTopBarCustom(
                    title = stringResource(R.string.title_update_profile),
                    showBack = true,
                    onBack = { onSafeBack() }
                )
            },
        canLoseChanges = true
    ),

}
