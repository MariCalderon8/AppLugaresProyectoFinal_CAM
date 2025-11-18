package eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.model.Report
import eam.edu.co.applugaresproyectofinal.ui.components.CustomButton
import eam.edu.co.applugaresproyectofinal.ui.components.CustomSnackbar
import eam.edu.co.applugaresproyectofinal.ui.components.InputText
import eam.edu.co.applugaresproyectofinal.ui.components.MessageType
import eam.edu.co.applugaresproyectofinal.ui.screens.LocalMainViewModel
import eam.edu.co.applugaresproyectofinal.utils.SharedPrefsUtil
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewReportScreen(
    onBack: () -> Unit = {},
    placeId: String
) {
    val placesViewModel = LocalMainViewModel.current.placesViewModel
    val usersViewModel = LocalMainViewModel.current.usersViewModel
    val context = LocalContext.current

    val userId = SharedPrefsUtil.getPreferences(context)["userId"] ?: return
    usersViewModel.findUserById(userId)
    val currentUser by usersViewModel.currentUser.collectAsState()


    var subject by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val validators = remember { mutableListOf<() -> Boolean>() }

    var message by remember { mutableStateOf<Pair<String, MessageType>?>(null) }
    var showMessage by remember { mutableStateOf(false) }

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
                value = subject,
                label = stringResource(R.string.label_report_subject),
                placeholder = stringResource(R.string.placeholder_report_subject),
                supportingText = stringResource(R.string.error_field_required),
                onValueChange = { subject = it },
                onValidate = { subject.isBlank() },
                registerValidator = { validator -> validators.add(validator) }
            )

            Spacer(Modifier.height(16.dp))

            InputText(
                value = description,
                label = stringResource(R.string.label_report_description),
                placeholder = stringResource(R.string.placeholder_report_description),
                supportingText = stringResource(R.string.error_field_required),
                onValueChange = { description = it },
                onValidate = { description.isBlank() },
                height = 120,
                singleLine = false,
                registerValidator = { validator -> validators.add(validator) }
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
                    if (currentUser == null) return@CustomButton

                    var hasErrors = false
                    validators.forEach { validator ->
                        if (validator()) hasErrors = true
                    }

                    if (hasErrors) {
                        message=context.getString(R.string.error_fill_all_fields) to MessageType.ERROR
                        showMessage = true
                        return@CustomButton
                    }

                    placesViewModel.addReport(placeId, Report(
                        id = UUID.randomUUID().toString(),
                        subject = subject,
                        description = description,
                        userId = currentUser!!.id
                    ))

                    message = context.getString(R.string.label_success_report_sent) to MessageType.SUCCESS
                    onBack()
                },
                isLarge = true,
                modifier = Modifier.fillMaxWidth()
            )
            message?.let { (text, type) ->
                CustomSnackbar(
                    message = text,
                    type = type,
                    visible = showMessage,
                    onDismiss = {
                        showMessage = false
                        message = null
                    }
                )
            }
        }
    }
}
