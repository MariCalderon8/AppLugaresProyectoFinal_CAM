package eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs

import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.ui.components.*
import eam.edu.co.applugaresproyectofinal.ui.screens.LocalMainViewModel
import eam.edu.co.applugaresproyectofinal.utils.formatSchedules
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetailScreen(
    placeId: String,
    onBack: () -> Unit = {}
) {
    val placesViewModel = LocalMainViewModel.current.placesViewModel
    val place = placesViewModel.findPlaceById(placeId) ?: return
    val usersViewModel = LocalMainViewModel.current.usersViewModel

//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = { Text(place.name) },
//                navigationIcon = {
//                    IconButton(onClick = { onBack() }) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
//                            contentDescription = stringResource(R.string.label_back)
//                        )
//                    }
//                }
//            )
//        }
//    ) { innerPadding ->

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color(0xFFEDEDED), RoundedCornerShape(12.dp))
        ) {
            AsyncImage(
                model = place.images[0],
                contentDescription = stringResource(R.string.label_place_image),
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingBar(rating = place.rating.roundToInt())
            TagChip(text = stringResource(R.string.label_created_by_me))
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = place.name,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = place.name,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = place.description,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(12.dp))

        PlaceInfoRow(
            icon = Icons.Filled.LocationOn,
            text = place.address
        )
        Spacer(Modifier.height(6.dp))
        PlaceInfoRow(
            icon = Icons.Filled.Phone,
            text = place.phone
        )
        Spacer(Modifier.height(6.dp))
        PlaceInfoRow(
            icon = Icons.Filled.AccessTime,
            text = formatSchedules(context = LocalContext.current, place.scheduleList)
        )

        Spacer(Modifier.height(12.dp))

        Image(
            painter = painterResource(id = R.drawable.map),
            contentDescription = stringResource(R.string.label_place_location),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(Modifier.height(16.dp))

        val creator = usersViewModel.findUserById(place.createdById)?: return
        CreatorInfoCard(
            name = creator.name,
            username = creator.username,
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
    //}
}
