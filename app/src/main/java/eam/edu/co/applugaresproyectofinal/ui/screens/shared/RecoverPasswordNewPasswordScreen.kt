package eam.edu.co.applugaresproyectofinal.ui.screens.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eam.edu.co.applugaresproyectofinal.ui.components.CustomButton
import eam.edu.co.applugaresproyectofinal.ui.components.InputText
import eam.edu.co.applugaresproyectofinal.R

@Composable
fun RecoverPasswordNewPasswordScreen(
    onChangePassword: (String, String) -> Unit,
    onBack: () -> Unit
) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.title_recover_password_new_password),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = stringResource(R.string.subtitle_recover_password_new_password),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        InputText(
            value = newPassword,
            label = stringResource(R.string.label_password),
            supportingText = stringResource(R.string.error_password),
            onValueChange = { newPassword = it },
            onValidate = { it.length < 5 },
            isPassword = true,
            icon = Icons.Outlined.Lock
        )

        Spacer(modifier = Modifier.height(8.dp))

        InputText(
            value = confirmPassword,
            label = stringResource(R.string.label_confirm_password),
            supportingText = stringResource(R.string.error_confirm_password),
            onValueChange = { confirmPassword = it },
            onValidate = { confirmPassword != newPassword },
            isPassword = true,
            icon = Icons.Outlined.Lock
        )

        Spacer(modifier = Modifier.height(28.dp))

        CustomButton(
            text = stringResource(R.string.password_restore),
            isLarge = true,
            onClick = { onChangePassword(newPassword, confirmPassword) }
        )
    }
}
