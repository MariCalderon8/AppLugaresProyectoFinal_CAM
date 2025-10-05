package eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import eam.edu.co.applugaresproyectofinal.ui.components.PlaceCard
import eam.edu.co.applugaresproyectofinal.ui.screens.LocalMainViewModel
import eam.edu.co.applugaresproyectofinal.utils.convertDayToString
import eam.edu.co.applugaresproyectofinal.utils.formatSchedules

@Composable
fun FavoritesScreen(
    onNavigateToPlaceDetail: (String) -> Unit,
) {

    val placesViewModel = LocalMainViewModel.current.placesViewModel
    val usersViewModel = LocalMainViewModel.current.usersViewModel
    val places = placesViewModel.places.collectAsState()
    val context = LocalContext.current

    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 12.dp
        )
    ) {
        items(places.value) { place ->
            PlaceCard(
                title = place.name,
                category = place.category.displayName,
                address = place.description,
                createdBy = usersViewModel.findUserById(place.createdById)?.name ?: "Desconocido",
                date = formatSchedules(context = LocalContext.current, place.scheduleList),
                iconContent = {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color.White, CircleShape)
                            .padding(6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                },
                imageUrl = place.images[0],
                onCardClick = {
                    onNavigateToPlaceDetail(place.id)
                }
            )
        }
    }
}
