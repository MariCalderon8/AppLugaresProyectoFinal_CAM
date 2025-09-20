package eam.edu.co.applugaresproyectofinal.ui.screens.shared

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import eam.edu.co.applugaresproyectofinal.ui.components.InputText
import eam.edu.co.applugaresproyectofinal.R

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    var email by remember { mutableStateOf("") } // Estado mutable
    var password by remember { mutableStateOf("") } // Estado mutable
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        content = {

            InputText(
                modifier = Modifier,

                value = email,
                label = stringResource(R.string.label_email),
                supportingText = stringResource(R.string.error_email),
                onValueChange = {
                    email = it
                },
                onValidate = {
                    it.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(it).matches()
                },
                icon = Icons.Outlined.Email,
            )

            InputText(
                modifier = Modifier,
                value = password,
                label = stringResource(R.string.label_password),
                supportingText = stringResource(R.string.error_password),
                onValueChange = {
                    password = it
                },
                onValidate = {
                    password.isBlank() || password.length < 5
                },
                icon = Icons.Outlined.Lock
            )

            Button(
                onClick = {
                    if (email == "juancho@j.com" && password == "12345") {
                        onNavigateToHome()
                        Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(context, "Los datos son incorrectos", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
                content = {
                    Text(
                        color = Color.White,
                        text = stringResource(R.string.btn_login)
                    )
                },
            )
            Button(
                onClick = {
                    onNavigateToRegister()
                },
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = stringResource(R.string.btn_create_account)
                    )
                }
            )

        }

    )

}