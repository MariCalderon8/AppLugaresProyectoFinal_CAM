package eam.edu.co.applugaresproyectofinal.ui.screens

import kotlinx.serialization.Serializable

sealed class RouteScreen {
    @Serializable
    data object Home: RouteScreen()

    @Serializable
    data object  Auth : RouteScreen()

    @Serializable
    data object  RecoverPasswordEmail : RouteScreen()

    @Serializable
    data object  RecoverPasswordCode : RouteScreen()

    @Serializable
    data object  RecoverPasswordNewPassword : RouteScreen()


}