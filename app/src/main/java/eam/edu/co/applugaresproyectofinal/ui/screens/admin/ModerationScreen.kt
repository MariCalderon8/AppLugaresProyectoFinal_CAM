package eam.edu.co.applugaresproyectofinal.ui.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eam.edu.co.applugaresproyectofinal.R

@Composable
fun ModerationScreen (){
    var searchQuery by remember { mutableStateOf("") }
    var selectedStatus by remember { mutableStateOf("Pendientes") }
//    var selectedStatus by remember { mutableStateOf<Status>(null) }

    // 🔹 Data quemada de lugares
    val allPlaces = listOf("Café Central", "Casa nueva", "Hotel Azul")

    // 🔹 Data quemada de estados
    val statuses = listOf("Pendientes", "Aprobados", "Rechazados", "Reportados")

    // 🔹 Filtro simple
    val filteredPlaces = allPlaces.filter {
        (searchQuery.isBlank() || it.contains(searchQuery, ignoreCase = true)) &&
                (selectedStatus.isBlank() || selectedStatus == "Pendientes" || it.contains(selectedStatus, true))
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 16.dp, end = 16.dp)

    ) {

        Text(
            text = stringResource(R.string.label_moderation),
            color = Color.Black,
            fontWeight = Bold,
            fontSize = 25.sp,
            modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)
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
                        selectedStatus = if (isSelected) "Pendientes" else status
                    },
                    label = {
                        Text(
                            text = status,
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
                        borderColor = Color(0xFF6A1B9A),
                        selectedBorderColor = Color(0xFF6A1B9A),
                        disabledBorderColor = Color(0xFF6A1B9A),
                        disabledSelectedBorderColor = Color(0xFF6A1B9A)
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Texto de resultados dinámico
        Text(
            text = when {
                searchQuery.isNotBlank() -> "Mostrando resultados para: $searchQuery"
                selectedStatus.isNotBlank() -> "Filtrando por: $selectedStatus"
                else -> "Mostrando todos los lugares"
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
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFF5F5F5)
                    ) {
                        Box(modifier = Modifier.padding(16.dp)) {
                            Text(text = place)
                        }
                    }
                }
            }
        }
    }
}

