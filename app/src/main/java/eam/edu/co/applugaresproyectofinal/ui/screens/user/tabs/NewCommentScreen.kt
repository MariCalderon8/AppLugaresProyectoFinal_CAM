package eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.model.Review
import eam.edu.co.applugaresproyectofinal.ui.components.CustomButton
import eam.edu.co.applugaresproyectofinal.ui.components.InputText
import eam.edu.co.applugaresproyectofinal.ui.screens.LocalMainViewModel
import eam.edu.co.applugaresproyectofinal.utils.SharedPrefsUtil
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCommentScreen(
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
    var rating by remember { mutableStateOf(0) }

    var ratingError by remember { mutableStateOf(false) }
    val validators = remember { mutableListOf<() -> Boolean>() }


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
                value = subject,
                label = stringResource(R.string.label_comment_subject),
                placeholder = stringResource(R.string.placeholder_comment_subject),
                supportingText = "Campo requerido",
                onValueChange = { subject = it },
                onValidate = { subject.isBlank() },
                registerValidator = { validator -> validators.add(validator) }
            )

            Spacer(Modifier.height(16.dp))

            InputText(
                value = description,
                label = stringResource(R.string.label_comment_description),
                placeholder = stringResource(R.string.placeholder_comment_description),
                supportingText = "Campo requerido",
                onValueChange = { description = it },
                onValidate = { description.isBlank() },
                height = 120,
                singleLine = false,
                registerValidator = { validator -> validators.add(validator) }
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

            if (ratingError) {
                Text(
                    text = stringResource(R.string.error_rating_required),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(start = 4.dp, top = 2.dp)
                )
            }

            Spacer(Modifier.height(28.dp))

            CustomButton(
                text = stringResource(R.string.btn_submit_comment),
                onClick = {
                    if (currentUser == null) return@CustomButton

                    var hasErrors = false
                    validators.forEach { validator ->
                        if (validator()) hasErrors = true
                    }
                    ratingError = rating < 1
                    if (ratingError) hasErrors = true

                    if (hasErrors) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.error_fill_all_fields),
                            Toast.LENGTH_SHORT
                        ).show()
                        return@CustomButton
                    }

                    Toast.makeText(
                        context,
                        context.getString(R.string.label_success_review_sent),
                        Toast.LENGTH_SHORT
                    ).show()
                    val review = Review(
                        id = UUID.randomUUID().toString(),
                        subject = subject,
                        description = description,
                        rating = rating.toDouble(),
                        userId = currentUser!!.id
                    )
                    placesViewModel.addReview(placeId, review)
                    onBack()
                },
                isLarge = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }
    }
}
