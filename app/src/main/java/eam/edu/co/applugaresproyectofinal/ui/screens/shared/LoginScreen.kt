package eam.edu.co.applugaresproyectofinal.ui.screens.shared

import android.content.pm.ModuleInfo
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eam.edu.co.applugaresproyectofinal.ui.components.InputText
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.ui.components.CheckBox
import eam.edu.co.applugaresproyectofinal.ui.components.CustomButton

@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit
) {
    var email by remember { mutableStateOf("") } // Estado mutable
    var password by remember { mutableStateOf("") } // Estado mutable
    val context = LocalContext.current
    var isChecked by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        modifier = Modifier
            .padding(18.dp)
            .verticalScroll(rememberScrollState()),
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
                isPassword = true,
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CheckBox(
                    label = stringResource(R.string.label_remember_session),
                    checked = isChecked,
                    onCheckedChange = { checked ->
                        isChecked = checked == true
                        Log.d("Login", "Remember checked")
                    },
                    textSize = 13
                )
                Text(
                    text = stringResource(R.string.link_login_forgot_password),
                    color = colorResource(R.color.blue_link),
                    textDecoration = TextDecoration.Underline,
                    fontSize = 13.sp,
                    modifier = Modifier.clickable {
                        Log.d("Login", "Recover password clicked")
                    }
                )

            }

            Spacer(modifier = Modifier.height(4.dp))

            CustomButton(
                text = stringResource(R.string.btn_login),
                isLarge = true,
                onClick = {
                    if (email == "juancho@j.com" && password == "12345") {
                        onNavigateToHome()
                        Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(context, "Los datos son incorrectos", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            )

        }

    )

}