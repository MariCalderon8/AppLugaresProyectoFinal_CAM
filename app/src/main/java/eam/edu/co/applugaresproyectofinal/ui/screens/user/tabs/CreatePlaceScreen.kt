package eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddAPhoto
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.LocalCafe
import androidx.compose.material.icons.outlined.Museum
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.model.Category
import eam.edu.co.applugaresproyectofinal.ui.components.CustomButton
import eam.edu.co.applugaresproyectofinal.ui.components.InputText
import eam.edu.co.applugaresproyectofinal.ui.components.Label
import eam.edu.co.applugaresproyectofinal.ui.components.ScheduleDialog
import eam.edu.co.applugaresproyectofinal.ui.components.ScheduleItemCard


@Composable
fun CreatePlaceScreen(
    onNavigateToMyPlaces: () -> Unit
) {
    var placeName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    val categories = listOf(
        Category("Restaurante", Icons.Outlined.Restaurant),
        Category("Hotel", Icons.Outlined.Store),
        Category("Museo", Icons.Outlined.Museum),
        Category("Cafetería", Icons.Outlined.LocalCafe),
        Category("Comida rápida", Icons.Outlined.Fastfood)
    )

    var selectedCategory by remember { mutableStateOf<String?>(null) }


    var showDialogSchedule by remember { mutableStateOf(false) }
    var schedule by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
            .verticalScroll(rememberScrollState()),
    ) {

        InputText(
            value = placeName,
            isRequired = true,
            label = stringResource(R.string.label_place_name),
            supportingText = stringResource(R.string.error_name),
            onValueChange = {
                placeName = it
            },
            onValidate = {
                placeName.isBlank()
            },
            placeholder = stringResource(R.string.name_placeholder),
            icon = Icons.Outlined.Store,
        )

        Label(
            text = stringResource(R.string.label_place_category),
            isRequired = true,
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            categories.forEach { category ->
                FilterChip(
                    selected = selectedCategory == category.name,
                    onClick = { selectedCategory = category.name },
                    label = { Text(category.name) },
                    leadingIcon = {
                        Icon(
                            imageVector = category.icon,
                            contentDescription = category.name,
                            modifier = Modifier.size(20.dp),
                            tint = colorResource(R.color.gray_text)
                        )
                    },
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }

        InputText(
            value = description,
            isRequired = true,
            label = stringResource(R.string.label_place_description),
            supportingText = stringResource(R.string.error_description),
            onValueChange = {
                description = it
            },
            onValidate = {
                description.isBlank()
            },
            singleLine = false,
            placeholder = stringResource(R.string.description_placeholder),
            height = 150
        )

        InputText(
            value = address,
            isRequired = true,
            label = stringResource(R.string.label_place_address),
            supportingText = stringResource(R.string.error_place_address),
            onValueChange = {
                address = it
            },
            onValidate = {
                address.isBlank()
            },
            placeholder = stringResource(R.string.address_placeholder),
            icon = Icons.Default.LocationOn,
        )

        InputText(
            value = phoneNumber,
            isRequired = true,
            label = stringResource(R.string.label_phonenumber),
            supportingText = stringResource(R.string.error_phonenumber),
            onValueChange = {
                phoneNumber = it
            },
            onValidate = {
                phoneNumber.isBlank() || !Patterns.PHONE.matcher(phoneNumber).matches()
            },
            placeholder = stringResource(R.string.phone_placeholder),
            icon = Icons.Outlined.Phone,
            modifier = Modifier
        )


        Label(stringResource(R.string.label_place_schedule), isRequired = true)

        ScheduleItemCard()

        FloatingActionButton(
            onClick = { showDialogSchedule = true },
            shape = CircleShape,
            containerColor = colorResource(id = R.color.purple_500),
            modifier = Modifier
                .padding(top = 12.dp)
                .size(48.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(Icons.Outlined.Add, contentDescription = "Agregar horario", tint = Color.White)
        }

       if(showDialogSchedule){
           ScheduleDialog(onDimiss = { showDialogSchedule = false })
       }


        Label(
            text = stringResource(R.string.label_place_images),
            isRequired = true
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(75.dp)
                    .padding(4.dp)
                    .background(
                        color = colorResource(id = R.color.light_gray),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .drawBehind {
                        val stroke = Stroke(
                            width = 3f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f),
                        )
                        drawRoundRect(
                            color = Color.Gray,
                            size = size,
                            style = stroke,
                            cornerRadius = CornerRadius(20f, 20f)
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.AddAPhoto,
                    contentDescription = stringResource(R.string.add_images),
                )
            }

//                Spacer(modifier = Modifier.width(12.dp))

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .padding(4.dp)
            ) {
                // Caja de la imagen
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            color = colorResource(id = R.color.light_gray),
                            shape = RoundedCornerShape(8.dp),
                        ),

                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Image,
                        contentDescription = stringResource(R.string.image_text),
                    )
                }

                // Botón de eliminar (X)
                IconButton(
                    onClick = { /* acción eliminar imagen */ },
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.TopEnd)
                        .padding(4.dp, 4.dp, 2.dp, 2.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = stringResource(R.string.delete_image_txt),
                    )
                }
            }
        }

        Label(
            text = stringResource(R.string.label_place_location),
            isRequired = true,
        )

        Image(
            painter = painterResource(id = R.drawable.map),
            contentDescription = stringResource(R.string.label_place_location),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomButton(
            text = stringResource(R.string.btn_create_place),
            onClick = {
                onNavigateToMyPlaces()
            },
            isLarge = true
        )

    }
}

