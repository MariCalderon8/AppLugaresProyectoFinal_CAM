package eam.edu.co.applugaresproyectofinal.ui.screens.user.nav

import kotlinx.serialization.Serializable

sealed class RouteTab{
    @Serializable
    data object Map: RouteTab()

    @Serializable
    data object Favorites: RouteTab()

    @Serializable
    data object Profile: RouteTab()
}