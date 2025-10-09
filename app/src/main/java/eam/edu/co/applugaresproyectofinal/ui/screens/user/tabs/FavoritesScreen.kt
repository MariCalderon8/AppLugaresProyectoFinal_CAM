package eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.HeartBroken
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.model.Category
import eam.edu.co.applugaresproyectofinal.model.Place
import eam.edu.co.applugaresproyectofinal.ui.components.PlaceCard
import eam.edu.co.applugaresproyectofinal.ui.screens.LocalMainViewModel
import eam.edu.co.applugaresproyectofinal.utils.SharedPrefsUtil
import eam.edu.co.applugaresproyectofinal.utils.formatSchedules

@Composable
fun FavoritesScreen(
    onNavigateToPlaceDetail: (String) -> Unit,
) {

    val placesViewModel = LocalMainViewModel.current.placesViewModel
    val usersViewModel = LocalMainViewModel.current.usersViewModel
    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    val context = LocalContext.current
    val user = usersViewModel.findUserById(SharedPrefsUtil.getPreferences(context)["userId"]?: return)
    var userPlaces: List<Place> = emptyList()
    if (user != null ) {
        userPlaces = placesViewModel.getUserFavoritePlaces(user.favorites?.toList() ?: emptyList())
    }
    val filteredPlaces = placesViewModel.filterPlacesUserCategoryQuery(selectedCategory, "", userPlaces)

    val categories = listOf(
        null, // todas
        Category.RESTAURANT,
        Category.FAST_FOOD,
        Category.CAFETERIA,
        Category.MUSEUM,
        Category.HOTEL,
    )

    Column(
        modifier = Modifier.padding(
            PaddingValues(
            horizontal = 16.dp,
            vertical = 12.dp
            )
        )
    ) {
        if (userPlaces.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.HeartBroken,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.label_no_favorites),
                    fontWeight = Bold,
                    color = Color.Black
                )
                Text(
                    text = stringResource(R.string.label_no_favorites_description),
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(categories) { category ->
                    val isSelected = selectedCategory == category
                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            selectedCategory = if (isSelected) null else category
                        },
                        label = {
                            Text(
                                text = category?.displayName ?: "Todos",
                                color = if (isSelected) Color.White else colorResource(R.color.primary)
                            )
                        },
                        leadingIcon = {
                            if (category != null) {
                                Icon(
                                    imageVector = category.icon,
                                    contentDescription = category.displayName ,
                                    tint = if (isSelected) Color.White else colorResource(R.color.primary)
                                )
                            }
                        },
                        shape = RoundedCornerShape(50),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = colorResource(R.color.primary),
                            containerColor = Color.White,
                            selectedLabelColor = Color.White,
                            selectedLeadingIconColor = Color.White,
                            labelColor = Color.Black,
                            iconColor = Color.Black,
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = isSelected,
                            borderColor = colorResource(R.color.primary),
                            selectedBorderColor = colorResource(R.color.primary),
                            disabledBorderColor = colorResource(R.color.primary),
                            disabledSelectedBorderColor = colorResource(R.color.primary)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(filteredPlaces) { place ->
                    PlaceCard(
                        title = place.name,
                        category = place.category.displayName,
                        address = place.description,
                        createdBy = usersViewModel.findUserById(place.createdById)?.completeName
                            ?: "Desconocido",
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
            if (filteredPlaces.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FilterAlt,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(R.string.label_no_results),
                        fontWeight = Bold,
                        color = Color.Black
                    )
                    Text(
                        text = stringResource(R.string.label_no_results_description),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }



    }
}
