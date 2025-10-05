package eam.edu.co.applugaresproyectofinal.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import eam.edu.co.applugaresproyectofinal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCustom(
    title: String = "",
    showBack: Boolean = false,
    onBack: () -> Unit = {},
    actions: (@Composable () -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        title = {
            if (title != "") {
                Text(
                    text = title
                )
            }
        },
        navigationIcon = {
            if (showBack) {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = stringResource(R.string.label_back)
                    )
                }
            }
        },
        actions = {
            actions?.invoke()
        }
    )
}