package eam.edu.co.applugaresproyectofinal.ui.screens.shared


import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.ui.components.CustomButton
import eam.edu.co.applugaresproyectofinal.ui.components.DropdownMenu
import eam.edu.co.applugaresproyectofinal.ui.components.InputText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(onNavigateToLogin: () -> Unit) {

    var city by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }

    var countries = listOf("Colombia", "Peru", "Ecuador", "Venezuela")
    var cities = listOf("Bogotá", "Lima", "Quito", "Caracas")
    var name by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            modifier = Modifier
                .padding(18.dp)
                .verticalScroll(rememberScrollState()),
        ) {

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

            InputText(
                value = email,
                label = stringResource(R.string.label_email),
                supportingText = stringResource(R.string.error_email),
                onValueChange = {
                    email = it
                },
                onValidate = {
                    email.isBlank() || Patterns.EMAIL_ADDRESS.matcher(email).matches()
                },
                icon = Icons.Outlined.Email,
                modifier = Modifier
            )

            InputText(
                value = password,
                isPassword = true,
                label = stringResource(R.string.label_password),
                supportingText = stringResource(R.string.error_password),
                onValueChange = {
                    password = it
                },
                onValidate = {
                    password.isBlank() || password.length < 5
                },
                icon = Icons.Outlined.Lock,
                modifier = Modifier

            )


            InputText(
                value = confirmPassword,
                isPassword = true,
                label = stringResource(R.string.label_confirm_password),
                supportingText = stringResource(R.string.error_confirm_password),
                onValueChange = {
                    confirmPassword = it
                },
                onValidate = {
                    password != confirmPassword
                },
                icon = Icons.Outlined.Lock,
                modifier = Modifier
            )

            CustomButton(
                text = stringResource(R.string.btn_register),
                onClick = {
                    onNavigateToLogin()
                },
                isLarge = true
            )

            Spacer(modifier = Modifier.height(60.dp))


        }
    }