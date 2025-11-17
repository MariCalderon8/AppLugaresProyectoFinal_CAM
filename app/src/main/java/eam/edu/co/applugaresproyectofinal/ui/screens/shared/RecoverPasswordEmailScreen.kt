package eam.edu.co.applugaresproyectofinal.ui.screens.shared

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eam.edu.co.applugaresproyectofinal.ui.components.CustomButton
import eam.edu.co.applugaresproyectofinal.ui.components.InputText
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.ui.screens.LocalMainViewModel

@Composable
fun RecoverPasswordEmailScreen(
    onContinue: (String) -> Unit,
    onBack: () -> Unit
) {
    var email by remember { mutableStateOf("") }

    val context = LocalContext.current
    val usersViewModel = LocalMainViewModel.current.usersViewModel

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement =  Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.title_recover_password_email),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = stringResource(R.string.subtitle_recover_password_email),
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(28.dp))

        InputText(
            value = email,
            label = stringResource(R.string.label_email),
            supportingText = stringResource(R.string.error_email),
            onValueChange = { email = it },
            onValidate = { it.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(it).matches() },
            icon = Icons.Outlined.Email
        )

        Spacer(modifier = Modifier.height(28.dp))

        CustomButton(
            text = stringResource(R.string.btn_continue),
            isLarge = true,
            onClick = {
                if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(context, "Ingresa un correo válido", Toast.LENGTH_LONG).show()
                    return@CustomButton
                }
                usersViewModel.sendPasswordReset(email) { success, msg ->
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                    if (success) onBack()
                }
            }
        )
    }
}