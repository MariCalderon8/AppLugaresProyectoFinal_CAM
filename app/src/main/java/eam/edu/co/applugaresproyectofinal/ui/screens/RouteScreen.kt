package eam.edu.co.applugaresproyectofinal.ui.screens

import kotlinx.serialization.Serializable

sealed class RouteScreen {
    @Serializable
    data object Home: RouteScreen()

    @Serializable
    data object  Auth : RouteScreen()

}