package eam.edu.co.applugaresproyectofinal.ui.screens.user.nav

import kotlinx.serialization.Serializable

sealed class RouteTab{
    @Serializable
    data object Map: RouteTab()

    @Serializable
    data object Favorites: RouteTab()

    @Serializable
    data object Profile: RouteTab()

    @Serializable
    data object MyPlaces: RouteTab()

    @Serializable
    data object CreatePlace: RouteTab()

    @Serializable
    data object UpdateProfile: RouteTab()

    @Serializable
    data class PlaceDetail(val placeId: String): RouteTab()

    @Serializable
    data class NewComment(val placeId: String): RouteTab()

    @Serializable
    data class NewReport(val placeId: String): RouteTab()
}