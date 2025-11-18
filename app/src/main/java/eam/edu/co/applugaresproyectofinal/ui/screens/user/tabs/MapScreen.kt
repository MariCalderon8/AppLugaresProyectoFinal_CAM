package eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.model.Category
import eam.edu.co.applugaresproyectofinal.model.Status
import eam.edu.co.applugaresproyectofinal.ui.components.Map
import eam.edu.co.applugaresproyectofinal.ui.components.PlaceCard
import eam.edu.co.applugaresproyectofinal.ui.screens.LocalMainViewModel
import eam.edu.co.applugaresproyectofinal.utils.SharedPrefsUtil
import eam.edu.co.applugaresproyectofinal.utils.formatSchedules
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    onSelectedCategory: (Category?) -> Unit = {},
    onNavigateToPlaceDetail: (String) -> Unit,
) {
    val context = LocalContext.current
    val placesViewModel = LocalMainViewModel.current.placesViewModel
    val usersViewModel = LocalMainViewModel.current.usersViewModel

    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    val userId = SharedPrefsUtil.getPreferences(context)["userId"] ?: return
    usersViewModel.findUserById(userId)
    val currentUser by usersViewModel.currentUser.collectAsState()


    val filteredPlaces = placesViewModel.filterPlacesUserCategoryQuery(selectedCategory, searchQuery)

    val categories = listOf(
        null, // todas
        Category.RESTAURANT,
        Category.FAST_FOOD,
        Category.CAFETERIA,
        Category.MUSEUM,
        Category.HOTEL,
    )

    val places by placesViewModel.places.collectAsState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp, max = 500.dp)
                    .fillMaxHeight()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                // Texto de resultados dinámico
                Text(
                    text = when {
                        searchQuery.isNotBlank() -> stringResource(
                            R.string.label_showing_results_for,
                            searchQuery
                        )

                        selectedCategory == null -> stringResource(R.string.label_showing_all)
                        else -> stringResource(R.string.label_filtering_by, selectedCategory!!.displayName)
                    },
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )


                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(filteredPlaces) { place ->
                        PlaceCard(
                            title = place.name,
                            category = place.category.displayName,
                            address = place.description,
                            createdBy = usersViewModel.users.value
                                .find { it.id == place.createdById }
                                ?.completeName ?: "Desconocido",
                            date = formatSchedules(context = LocalContext.current, place.scheduleList),
                            iconContent = {
                                if (currentUser != null && currentUser!!.favorites.contains(place.id)){
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
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box(modifier = modifier.fillMaxSize()) {
            Map(
                places = places.filter { it.status == Status.APPROVED },
                modifier = Modifier.fillMaxSize(),
                onMarkerClick = { placeId ->
                    onNavigateToPlaceDetail(placeId)
                }
            )
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                // Barra de búsqueda
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        if (it.isNotBlank()) {
                            scope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.FilterList,
                            contentDescription = stringResource(R.string.label_filter)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            Icons.Outlined.Search,
                            contentDescription = stringResource(R.string.label_search)
                        )
                    },
                    placeholder = { Text(text = stringResource(R.string.label_search_place)) },
                    shape = RoundedCornerShape(50),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Barra de categorías
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
                                onSelectedCategory(selectedCategory)
                                scope.launch {
                                    scaffoldState.bottomSheetState.expand()
                                }
                            },
                            label = {
                                Text(
                                    text = category?.displayName ?: "Todos",
                                    color = if (isSelected) Color.White else Color.Black
                                )
                            },
                            leadingIcon = {
                                if (category != null) {
                                    Icon(
                                        imageVector = category.icon,
                                        contentDescription = category.displayName ,
                                        tint = if (isSelected) Color.White else Color.Black
                                    )
                                }
                            },
                            shape = RoundedCornerShape(50),
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = Color(0xFF6A1B9A),
                                containerColor = Color.White,
                                selectedLabelColor = Color.White,
                                selectedLeadingIconColor = Color.White,
                                labelColor = Color.Black,
                                iconColor = Color.Black,
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = isSelected,
                                borderColor = Color.Transparent,
                                selectedBorderColor = Color.Transparent,
                                disabledBorderColor = Color.Transparent,
                                disabledSelectedBorderColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun rememberLocationPermissionState(
    permission: String = android.Manifest.permission.ACCESS_FINE_LOCATION,
    onPermissionResult: (Boolean) -> Unit
): Boolean {
    val context = LocalContext.current
    val permissionGranted = remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        permissionGranted.value = granted
        onPermissionResult(granted)
    }

    LaunchedEffect(Unit) {
        if (!permissionGranted.value) {
            launcher.launch(permission)
        }
    }

    return permissionGranted.value
}