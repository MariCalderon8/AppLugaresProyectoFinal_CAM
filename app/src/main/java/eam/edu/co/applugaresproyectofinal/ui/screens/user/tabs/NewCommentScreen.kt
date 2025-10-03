package eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.ui.components.CustomButton
import eam.edu.co.applugaresproyectofinal.ui.components.InputText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCommentScreen(
    onBack: () -> Unit = {} // 👈 callback para manejar la flecha atrás
) {
    var asunto by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.title_new_comment)) },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = stringResource(R.string.label_back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.subtitle_new_comment),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(16.dp))

            InputText(
                value = asunto,
                label = stringResource(R.string.label_comment_subject),
                placeholder = stringResource(R.string.placeholder_comment_subject),
                supportingText = "Campo requerido",
                onValueChange = { asunto = it },
                onValidate = { it.isEmpty() }
            )

            Spacer(Modifier.height(16.dp))

            InputText(
                value = descripcion,
                label = stringResource(R.string.label_comment_description),
                placeholder = stringResource(R.string.placeholder_comment_description),
                supportingText = "Campo requerido",
                onValueChange = { descripcion = it },
                onValidate = { it.isEmpty() },
                height = 120,
                singleLine = false
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.label_comment_rating),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(6.dp))

            Row {
                for (i in 1..5) {
                    Icon(
                        imageVector = if (i <= rating) Icons.Filled.Star else Icons.Outlined.Star,
                        contentDescription = "Star $i",
                        tint = if (i <= rating) MaterialTheme.colorScheme.primary else Color.LightGray,
                        modifier = Modifier
                            .size(36.dp)
                            .clickable { rating = i }
                            .padding(2.dp)
                    )
                }
            }

            Spacer(Modifier.height(28.dp))

            CustomButton(
                text = stringResource(R.string.btn_submit_comment),
                onClick = { println("Comentario enviado: $asunto - $descripcion - $rating⭐") },
                isLarge = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }
    }
}
