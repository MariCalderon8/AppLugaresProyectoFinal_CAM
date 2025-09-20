package eam.edu.co.applugaresproyectofinal.ui.screens.shared


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
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
import eam.edu.co.applugaresproyectofinal.ui.components.DropdownMenu


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit
) {
    var city by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxSize(),
    ) {
        DropdownMenu(
            label = stringResource(
                R.string.placeholder_select_country
            ),
            list = listOf("Colombia", "Peru", "Costa Rica"),
            onValueChange = {
                city = it
            }
        )
        DropdownMenu(
            label = stringResource(
                R.string.placeholder_select_city
            ),
            list = listOf("Armenia", "Pereira", "Manizales"),
            onValueChange = {
                country = it
            }
        )
        Button(
            onClick = {
                onNavigateToLogin()
                Log.d("RegisterForm", "City: $city, Country: $country")
            }
        ) {
            Text(
                text = stringResource(R.string.btn_register)
            )
        }
    }

}