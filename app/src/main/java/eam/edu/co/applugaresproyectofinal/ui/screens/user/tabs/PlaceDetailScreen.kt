package eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetailScreen(
    onBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.sample_place_name)) },
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0xFFEDEDED), RoundedCornerShape(12.dp))
            ) {
                Text(
                    stringResource(R.string.label_place_image),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RatingBar(rating = 4)
                TagChip(text = stringResource(R.string.label_created_by_me))
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.sample_place_name),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = stringResource(R.string.sample_place_category),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.sample_place_description),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(12.dp))

            PlaceInfoRow(
                icon = Icons.Filled.LocationOn,
                text = stringResource(R.string.sample_place_address)
            )
            Spacer(Modifier.height(6.dp))
            PlaceInfoRow(
                icon = Icons.Filled.Phone,
                text = stringResource(R.string.sample_place_phone)
            )
            Spacer(Modifier.height(6.dp))
            PlaceInfoRow(
                icon = Icons.Filled.AccessTime,
                text = stringResource(R.string.sample_place_schedule)
            )

            Spacer(Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color(0xFFD6EAF8), RoundedCornerShape(12.dp))
            ) {
                Text(
                    stringResource(R.string.label_map),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(Modifier.height(16.dp))

            CreatorInfoCard(
                name = stringResource(R.string.label_creator_name),
                username = stringResource(R.string.label_creator_username),
                date = stringResource(R.string.label_creator_date),
                avatar = R.drawable.avatar
            )

            Spacer(Modifier.height(16.dp))
            HorizontalDivider()

            Spacer(Modifier.height(12.dp))

            CommentItem(
                userName = stringResource(R.string.sample_comment_user),
                comment = stringResource(R.string.sample_comment_text),
                rating = 5
            )

            Spacer(Modifier.height(8.dp))

            ReplyBox(
                onSend = { respuesta ->
                    println("Respuesta enviada: $respuesta")
                }
            )

            Spacer(Modifier.height(100.dp))
        }
    }
}
