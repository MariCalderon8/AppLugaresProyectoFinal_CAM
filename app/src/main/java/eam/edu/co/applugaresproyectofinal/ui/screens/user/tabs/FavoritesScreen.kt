package eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import eam.edu.co.applugaresproyectofinal.ui.components.PlaceCard

@Composable
fun FavoritesScreen() {

    val places = listOf(
        mapOf(
            "title" to "Café Central",
            "category" to "Cafetería",
            "address" to "Calle 10 #15 - 20 Centro",
            "createdBy" to "Camilutian",
            "date" to "21/08/2025"
        ),
        mapOf(
            "title" to "Panadería El Trigal",
            "category" to "Panadería",
            "address" to "Carrera 5 #12-30",
            "createdBy" to "María",
            "date" to "15/09/2025"
        ),
        mapOf(
            "title" to "Restaurante La Terraza",
            "category" to "Restaurante",
            "address" to "Av. Siempre Viva #742",
            "createdBy" to "Andrés",
            "date" to "30/09/2025"
        )
    )

    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 12.dp
        )
    ) {
        items(places) { place ->
            PlaceCard(
                title = place["title"] ?: "",
                category = place["category"] ?: "",
                address = place["address"] ?: "",
                createdBy = place["createdBy"] ?: "",
                date = place["date"] ?: "",
                icon = Icons.Filled.Favorite,
                onIconClick = {

                }

            )
        }
    }
}
