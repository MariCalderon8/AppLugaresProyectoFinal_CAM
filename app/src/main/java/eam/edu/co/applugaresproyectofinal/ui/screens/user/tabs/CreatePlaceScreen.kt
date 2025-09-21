package eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.ui.components.CustomButton
import eam.edu.co.applugaresproyectofinal.ui.components.InputText
import eam.edu.co.applugaresproyectofinal.ui.components.Label

@Composable
fun CreatePlaceScreen(
    onNavigateToMyPlaces: () -> Unit
) {
    var placeName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

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
            icon = Icons.Outlined.Store,
        )

        Label(
            text = stringResource(R.string.label_place_category),
            isRequired = true,
        )

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
            placeholder = "Describe tu lugar, trata de incluir los detalles más relevantes de tu negocio",
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
            icon = Icons.Outlined.Phone,
            modifier = Modifier
        )



        Label(
            text = stringResource(R.string.label_place_images),
            isRequired = true
        )

        Label(
            text = stringResource(R.string.label_place_location),
            isRequired = true,
        )

        CustomButton(
            text = stringResource(R.string.btn_create_place),
            onClick = {
                onNavigateToMyPlaces()
            },
            isLarge = true
        )

        Spacer(modifier = Modifier.height(60.dp))

    }
}