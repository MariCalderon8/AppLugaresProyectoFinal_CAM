package eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs

import SignOutButton
import android.util.Patterns
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Place
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.ui.components.AlertDialogCustom
import eam.edu.co.applugaresproyectofinal.ui.components.CustomButton
import eam.edu.co.applugaresproyectofinal.ui.components.DropdownMenu
import eam.edu.co.applugaresproyectofinal.ui.components.InputText

@Composable
fun UpdateProfileScreen(
    onNavitageToProfile: () -> Unit = {},
    onBack: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
            .verticalScroll(rememberScrollState()),
    ) {

        var city by remember { mutableStateOf("") }
        var country by remember { mutableStateOf("") }

        var countries = listOf("Colombia", "Peru", "Ecuador", "Venezuela")
        var cities = listOf("Bogotá", "Lima", "Quito", "Caracas")
        var name by remember { mutableStateOf("Pepito") } // Datos de usuario quemados de ejemplo
        var lastname by remember { mutableStateOf("Perez") }
        var phoneNumber by remember { mutableStateOf("2345678") }

        var showDialog by remember { mutableStateOf(false) }

        BackHandler(enabled = true) {
            showDialog = true
        }

        // Diálogo de confirmación
        if (showDialog) {
            AlertDialogCustom (
                title = stringResource(R.string.title_lose_changes),
                text = stringResource(R.string.text_lose_changes),
                labelButtonConfirm = stringResource(R.string.btn_confirm),
                labelButtonDismiss = stringResource(R.string.btn_cancel),
                onDismiss = { showDialog = false },
                onConfirm = {
                    showDialog = false
                    onBack()
                },
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = stringResource(R.string.profile_image),
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
            )

            IconButton(
                onClick = {

                },
                modifier = Modifier
                    .size(36.dp)
                    .offset(x = (-24).dp, y = (-4).dp) // Lo mueve un poquito a la izquierda
                    .background(colorResource(R.color.white), RoundedCornerShape(8.dp))
                    .border(1.dp,colorResource(R.color.light_gray), RoundedCornerShape(8.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.change_profile_pic),
                    tint = colorResource(R.color.black),
                    modifier = Modifier.size(20.dp)
                )
            }
        }


        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            InputText(
                value = name,
                label = stringResource(R.string.label_name),
                supportingText = stringResource(R.string.error_name),
                onValueChange = {
                    name = it
                },
                onValidate = {
                    name.isBlank()
                },
                icon = Icons.Outlined.Person,
                modifier = Modifier.weight(1f)

            )

            InputText(
                value = lastname,
                label = stringResource(R.string.label_lastname),
                supportingText = stringResource(R.string.error_lastname),
                onValueChange = {
                    lastname = it
                },
                onValidate = {
                    lastname.isBlank()
                },
                modifier = Modifier.weight(1f)

            )
        }

        DropdownMenu(
            label = stringResource(R.string.label_register_country),
            list = countries,
            onValueChange = {
                country = it
            },
            icon = Icons.Outlined.Home,
            supportingText = stringResource(R.string.error_country)
        )

        DropdownMenu(
            label = stringResource(R.string.label_register_city),
            list = cities,
            onValueChange = {
                city = it
            },
            icon = Icons.Outlined.Place,
            supportingText = stringResource(R.string.error_city)
        )

        InputText(
            value = phoneNumber,
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

        CustomButton(
            text = stringResource(R.string.btn_save_changes),
            onClick = {
                onNavitageToProfile()
            },
            isLarge = true
        )

        Spacer(modifier = Modifier.height(20.dp))
    }

}