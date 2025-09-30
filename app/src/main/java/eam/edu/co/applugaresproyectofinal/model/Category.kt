package eam.edu.co.applugaresproyectofinal.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.LocalCafe
import androidx.compose.material.icons.outlined.LocalHotel
import androidx.compose.material.icons.outlined.Museum
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.ui.graphics.vector.ImageVector

enum class Category(
    val displayName: String,
    val icon: ImageVector
) {
    RESTAURANT("Restaurante", Icons.Outlined.Restaurant),
    CAFETERIA("Cafetería", Icons.Outlined.LocalCafe),
    FAST_FOOD("Comida rápida", Icons.Outlined.Fastfood),
    HOTEL("Hotel", Icons.Outlined.LocalHotel),
    MUSEUM("Museo", Icons.Outlined.Museum)
}
