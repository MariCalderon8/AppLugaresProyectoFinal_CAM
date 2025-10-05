package eam.edu.co.applugaresproyectofinal.ui.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import eam.edu.co.applugaresproyectofinal.model.Status
import eam.edu.co.applugaresproyectofinal.model.getColor
import eam.edu.co.applugaresproyectofinal.ui.components.PlaceCard
import eam.edu.co.applugaresproyectofinal.ui.components.TagChip
import eam.edu.co.applugaresproyectofinal.ui.screens.LocalMainViewModel
import eam.edu.co.applugaresproyectofinal.utils.formatSchedules

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModerationScreen(
    onLogout: () -> Unit = {},
    onNavigateToPlaceDetail: (String) -> Unit = {}
) {
    val placesViewModel = LocalMainViewModel.current.placesViewModel
    var searchQuery by remember { mutableStateOf("") }
    var selectedStatus by remember { mutableStateOf<Status?>(Status.PENDING_FOR_APPROVAL) }

    val places by placesViewModel.places.collectAsState()

    val filteredPlaces = placesViewModel.filterPlaces(selectedStatus, searchQuery)

    val statuses = listOf(
        null,
        Status.PENDING_FOR_APPROVAL,
        Status.APPROVED,
        Status.REJECTED,
        Status.REPORTED
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 80.dp)

    ) {

        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.label_moderation),
                    color = Color.Black,
                    fontWeight = Bold,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)
                )
            },
            actions = {
                IconButton(onClick = onLogout) {
                    Icon(
                        imageVector = Icons.Outlined.ExitToApp,
                        contentDescription = stringResource(R.string.btn_logout)
                    )
                }
            }
        )

        // Barra de búsqueda
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
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
                focusedContainerColor = Color(0xFFF3E5F5), // morado suave
                unfocusedContainerColor = Color(0xFFF3E5F5),
                disabledContainerColor = Color(0xFFF3E5F5),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        //Chips con estados quemados
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(statuses) { status ->
                val isSelected = selectedStatus == status
                FilterChip(
                    selected = isSelected,
                    onClick = {
                        selectedStatus = if (isSelected) null else status
                    },
                    label = {
                        Text(
                            text = status?.description ?: "Todos",
                            color = if (isSelected) Color.White else Color(0xFF6A1B9A)
                        )
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
                        borderColor = colorResource(R.color.primary),
                        selectedBorderColor = colorResource(R.color.primary),
                        disabledBorderColor = colorResource(R.color.primary),
                        disabledSelectedBorderColor = colorResource(R.color.primary)
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Texto de resultados dinámico
        Text(
            text = when {
                searchQuery.isNotBlank() -> stringResource(
                    R.string.label_showing_results_for,
                    searchQuery
                )

                selectedStatus == null -> stringResource(R.string.label_showing_all)
                else -> stringResource(R.string.label_filtering_by, selectedStatus!!.description)
            },
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

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
        } else {
            // Lista de tarjetas (dummy)
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredPlaces) { place ->
                    if (place.status == Status.PENDING_FOR_APPROVAL){
                        PlaceCard(
                            title = place.name,
                            category = place.category.displayName,
                            address = place.address,
                            createdBy = place.createdById,
                            date = formatSchedules(context = LocalContext.current, place.scheduleList),
                            imageUrl = place.images[0],
                            onCardClick = {
                                onNavigateToPlaceDetail(place.id)
                            },
                            iconContent = {
                                TagChip(
                                    text = place.status.description,
                                    backgroundColor = place.status.getColor(),
                                )
                            },
                            iconContentPadding = 4,
                            showActions = true,
                            onCancelClick = {},
                            onConfirmClick = {},
                            labelConfirmBtn = stringResource(R.string.btn_moderator_approve),
                            confirmBtnColor = Color(0xFF6A1B9A),
                            labelCancelBtn = stringResource(R.string.btn_moderator_reject),
                            cancelBtnColor = Color.White
                        )
                    } else if (place.status == Status.REPORTED){
                        PlaceCard(
                            title = place.name,
                            category = place.category.displayName,
                            address = place.address,
                            createdBy = place.createdById,
                            date = formatSchedules(context = LocalContext.current, place.scheduleList),
                            imageUrl = place.images[0],
                            onCardClick = {
                                onNavigateToPlaceDetail(place.id)
                            },
                            iconContent = {
                                TagChip(
                                    text = place.status.description,
                                    backgroundColor = place.status.getColor(),
                                )
                            },
                            iconContentPadding = 4,
                            showActions = true,
                            onCancelClick = {},
                            onConfirmClick = {},
                            labelCancelBtn = stringResource(R.string.label_ignore_report),
                            labelConfirmBtn = stringResource(R.string.label_delete),
                            confirmBtnColor = colorResource(R.color.status_rejected)
                        )
                    } else {
                        PlaceCard(
                            title = place.name,
                            category = place.category.displayName,
                            address = place.address,
                            createdBy = place.createdById,
                            date = formatSchedules(context = LocalContext.current, place.scheduleList),
                            imageUrl = place.images[0],
                            onCardClick = {
                                onNavigateToPlaceDetail(place.id)
                            },
                            iconContent = {
                                TagChip(
                                    text = place.status.description,
                                    backgroundColor = place.status.getColor(),
                                )
                            },
                            iconContentPadding = 4
                        )
                    }
                }
            }
        }
    }
}

