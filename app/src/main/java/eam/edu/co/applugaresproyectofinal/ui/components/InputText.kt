package eam.edu.co.applugaresproyectofinal.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    supportingText: String,
    onValueChange: (String) -> Unit,
    onValidate: (String) -> Boolean,
    icon: ImageVector? = null,
    fontSize: Int = 14,
    textColor: Color = colorResource(R.color.gray_text),
    iconColor: Color = colorResource(R.color.gray_text),
    borderColors: androidx.compose.material3.TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor  = colorResource(R.color.teal_700),
        unfocusedBorderColor  = colorResource(R.color.light_gray),
    )
) {
    var isError by rememberSaveable { mutableStateOf(false) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        label = {
            Text(
                text = label,
                color = textColor,
                fontSize = fontSize.sp
            )
        },
        value = value,
        isError = isError,
        colors = borderColors,
        supportingText = {
            if (isError) {
                Text(text = supportingText)
            }
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
                    tint = iconColor,
                )
            }
        } else null,
        trailingIcon = {
            if (isPassword) {
                val visibilityIcon =
                    if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton (onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        tint = iconColor,
                        imageVector = visibilityIcon,
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            }
        },
        onValueChange = {
            onValueChange(it)
            isError = onValidate(it)
        },
        singleLine = true
    )
}
