package eam.edu.co.applugaresproyectofinal.ui.screens.admin.nav

import kotlinx.serialization.Serializable

sealed class RoutesAdmin {
    @Serializable
    data object ModerationScreen : RoutesAdmin()

    @Serializable
    data class PlaceDetailAdmin(val placeId: String): RoutesAdmin()
}