package eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import eam.edu.co.applugaresproyectofinal.ui.components.PlaceCard
import eam.edu.co.applugaresproyectofinal.ui.screens.LocalMainViewModel
import eam.edu.co.applugaresproyectofinal.utils.SharedPrefsUtil
import eam.edu.co.applugaresproyectofinal.utils.formatSchedules

@Composable
fun MyPlacesScreen(
    onNavigateToPlaceDetail: (String) -> Unit,
){

    val placesViewModel = LocalMainViewModel.current.placesViewModel
    val usersViewModel = LocalMainViewModel.current.usersViewModel

    val context = LocalContext.current
    val userId = SharedPrefsUtil.getPreferences(context)["userId"] ?: return
    usersViewModel.findUserById(userId)
    val currentUser by usersViewModel.currentUser.collectAsState()


    if (currentUser != null) {
        val userPlaces = placesViewModel.getPlacesCreatedByUser(currentUser?.id ?: "")
        if (userPlaces.isEmpty()) {
            Text(text = "No hay lugares creados por este usuario")
        } else {
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(userPlaces) { place ->
                    PlaceCard(
                        title = place.name,
                        category = place.category.displayName,
                        address = place.description,
                        createdBy = usersViewModel.users.value
                            .find { it.id == place.createdById }
                            ?.completeName ?: "Desconocido",
                        date = formatSchedules(context = LocalContext.current, place.scheduleList),
                        imageUrl = place.images[0],
                        onCardClick = {
                            onNavigateToPlaceDetail(place.id)
                        }
                    )
                }
            }
        }
    }

}