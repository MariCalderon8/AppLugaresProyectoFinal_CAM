package eam.edu.co.applugaresproyectofinal.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eam.edu.co.applugaresproyectofinal.R

@Composable
fun InputText(
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    value: String,
    label: String,
    placeholder: String = "",
    isRequired: Boolean = false,
    supportingText: String,
    onValueChange: (String) -> Unit,
    onValidate: (String) -> Boolean,
    icon: ImageVector? = null,
    fontSize: Int = 14,
    textColor: Color = colorResource(R.color.gray_text),
    iconColor: Color = colorResource(R.color.gray_text),
    borderColors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = colorResource(R.color.teal_700),
        unfocusedBorderColor = colorResource(R.color.light_gray),
    ),
    singleLine: Boolean = true,
    height: Int? = null,
    registerValidator: ((() -> Boolean) -> Unit)? = null
) {
    var showError by rememberSaveable { mutableStateOf(false) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    // Permite que el padre ejecute esta validación más tarde (al presionar el botón)
    LaunchedEffect(Unit) {
        registerValidator?.invoke {
            val isError = onValidate(value)
            showError = isError
            isError
        }
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Label(
            text = label,
            isRequired = isRequired,
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .then(if (height != null) Modifier.height(height.dp) else Modifier),

            value = value,
            onValueChange = {
                onValueChange(it)
                showError = onValidate(it)
            },
            placeholder = {
                Text(text = placeholder, fontSize = fontSize.sp, color = colorResource(R.color.light_gray))
            },
            visualTransformation = if (isPassword && !passwordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            leadingIcon = if (icon != null) {
                {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = iconColor
                    )
                }
            } else null,
            trailingIcon = {
                if (isPassword) {
                    val visibilityIcon =
                        if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = visibilityIcon,
                            tint = iconColor,
                            contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                        )
                    }
                }
            },
            isError = showError,
            supportingText = {
                if (showError) {
                    Text(text = supportingText)
                }
            },
            singleLine = singleLine,
            colors = borderColors,
        )
    }
}
