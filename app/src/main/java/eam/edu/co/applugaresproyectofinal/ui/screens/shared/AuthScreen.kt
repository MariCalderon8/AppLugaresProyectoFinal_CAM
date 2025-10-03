package eam.edu.co.applugaresproyectofinal.ui.screens.shared


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.ui.screens.user.RegisterScreen

@Composable
fun AuthScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToRecoverPasswordEmail: () -> Unit
){

    var showLogin by remember{ mutableStateOf(true) }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
            modifier = Modifier
                .padding(30.dp)
        ){
            Text(
                text = stringResource(R.string.title_header_auth),
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = stringResource(R.string.txt_header_auth),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = colorResource(R.color.gray_text)
            )
        }

        Row(
            modifier = Modifier.background(
                color = colorResource(R.color.gray),
                shape = RoundedCornerShape(6.dp)
            )
                .padding(6.dp)
            ,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.Black,
                    containerColor = colorResource(R.color.gray),
                    contentColor = colorResource(R.color.gray_text),
                ),
                shape = RoundedCornerShape(6.dp),
                enabled = !showLogin,
                onClick = {
                    showLogin = true
                }
            ) {
                Text(
                    text = stringResource(R.string.btn_login)
                )
            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.Black,
                    containerColor = colorResource(R.color.gray),
                    contentColor = colorResource(R.color.gray_text)
                ),
                shape = RoundedCornerShape(6.dp),
                enabled = showLogin,
                onClick = {
                    showLogin = false
                }
            ) {
                Text(
                    text = stringResource(R.string.btn_register)
                )
            }
        }
        if (showLogin) {
            LoginScreen(
                onNavigateToHome = { onNavigateToHome() },
                onNavigateToRecoverPasswordEmail = { onNavigateToRecoverPasswordEmail() }
            )
        } else {
            RegisterScreen (
                onNavigateToLogin = { showLogin = true }
            )
        }
    }

}