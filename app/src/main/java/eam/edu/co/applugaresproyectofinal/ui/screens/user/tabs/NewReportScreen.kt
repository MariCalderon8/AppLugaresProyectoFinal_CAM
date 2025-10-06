package eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.ui.components.CustomButton
import eam.edu.co.applugaresproyectofinal.ui.components.InputText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewReportScreen(
    onBack: () -> Unit = {},
    placeId: String
) {
    var asunto by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.title_new_report)) },
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
                .padding(16.dp)
        ) {
            InputText(
                value = asunto,
                label = stringResource(R.string.label_report_subject),
                placeholder = stringResource(R.string.placeholder_report_subject),
                supportingText = "Campo requerido",
                onValueChange = { asunto = it },
                onValidate = { it.isEmpty() }
            )

            Spacer(Modifier.height(16.dp))

            InputText(
                value = descripcion,
                label = stringResource(R.string.label_report_description),
                placeholder = stringResource(R.string.placeholder_report_description),
                supportingText = "Campo requerido",
                onValueChange = { descripcion = it },
                onValidate = { it.isEmpty() },
                height = 120,
                singleLine = false
            )

            Spacer(Modifier.height(20.dp))

            val fullText = stringResource(R.string.info_report_text)
            val boldPart = "equipo de moderadores"

            Text(
                text = buildAnnotatedString {
                    val start = fullText.indexOf(boldPart)
                    val end = start + boldPart.length
                    if (start >= 0) {
                        append(fullText.substring(0, start))
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(fullText.substring(start, end))
                        }
                        append(fullText.substring(end))
                    } else {
                        append(fullText)
                    }
                },
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(Modifier.height(28.dp))

            CustomButton(
                text = stringResource(R.string.btn_submit_report),
                onClick = {
                    onBack()
                    println("Reporte enviado: $asunto - $descripcion")
                },
                isLarge = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
