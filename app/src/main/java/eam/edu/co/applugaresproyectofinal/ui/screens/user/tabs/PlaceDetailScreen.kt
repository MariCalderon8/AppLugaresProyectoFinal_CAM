package eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.AssignmentLate
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.ui.components.*
import eam.edu.co.applugaresproyectofinal.ui.screens.LocalMainViewModel
import eam.edu.co.applugaresproyectofinal.utils.SharedPrefsUtil
import eam.edu.co.applugaresproyectofinal.utils.formatDate
import eam.edu.co.applugaresproyectofinal.utils.formatSchedules
import kotlin.math.roundToInt
import eam.edu.co.applugaresproyectofinal.ui.components.Map

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetailScreen(
    placeId: String,
    onBack: () -> Unit = {},
    onNavigateToNewComment: (String) -> Unit,
    onNavigateToNewReport: (String) -> Unit,
) {
    val context = LocalContext.current

    val placesViewModel = LocalMainViewModel.current.placesViewModel
    val place = placesViewModel.findPlaceById(placeId) ?: return
    val usersViewModel = LocalMainViewModel.current.usersViewModel
    val user = usersViewModel.findUserById(SharedPrefsUtil.getPreferences(context)["userId"] ?: return)
    var userFavoritesList = user?.favorites ?: emptyList()

    val creator = usersViewModel.findUserById(place.createdById) ?: return
    val isOpen = placesViewModel.isPlaceOpen(place.scheduleList)

    var replyToReview by remember { mutableStateOf<String?>(null) } // 👈 id del comentario al que se responde
    var replyText by remember { mutableStateOf("") }

    val isCreator = user?.id == place.createdById

    BackHandler(enabled = replyToReview != null) {
        replyToReview = null
    }

    fun toggleFavorite(userId: String, placeId: String) {
        if (user != null) {
            if (userFavoritesList.contains(placeId)) {
                userFavoritesList = userFavoritesList - placeId
            } else {
                userFavoritesList = userFavoritesList + placeId
            }
            usersViewModel.updateUserFavoriteList(user.id, userFavoritesList)
        }
    }

    Scaffold(
        topBar = {
            TopBarCustom(
                showBack = true,
                onBack = onBack,
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                    ){
                        TagChip(
                            text = if (isOpen) stringResource(R.string.label_place_open) else stringResource(R.string.label_place_closed),
                            backgroundColor = if (isOpen) colorResource(R.color.status_approved) else Color.Gray
                        )
                    }
                }
            )
        },
        bottomBar = {
            if (replyToReview != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    ReplyBox(
                        onSend = { text ->
                            if (text.isNotBlank()) {
                                // Guarda la respuesta
                                val review = place.reviews.find { it.id == replyToReview }
                                review?.creatorReply = text
                                replyText = ""
                                replyToReview = null
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
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
                RatingBar(rating = placesViewModel.getPlaceRating(placeId).roundToInt())

                if (user != null) {
                    if (user.id == place.createdById) {
                        TagChip(text = stringResource(R.string.label_created_by_me))
                    } else {
                        var isFavorite by remember { mutableStateOf(user.favorites.contains(place.id)) }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            IconButton(
                                modifier = Modifier.size(30.dp),
                                onClick = {
                                    if (isFavorite) {
                                        toggleFavorite(user.id, place.id)
                                    } else {
                                        toggleFavorite(user.id, place.id)
                                    }
                                    isFavorite = user.favorites.contains(place.id)
                                }
                            ) {
                                Icon(
                                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                    contentDescription = if (isFavorite)
                                        stringResource(R.string.label_remove_favorite)
                                    else
                                        stringResource(R.string.label_add_to_favorite),
                                    tint = colorResource(R.color.primary),
                                    modifier = Modifier.size(30.dp)
                                )
                            }

                            IconButton(
                                modifier = Modifier
                                    .size(40.dp),
                                onClick = {
                                    onNavigateToNewReport(place.id)
                                }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(colorResource(R.color.primary), CircleShape)
                                        .padding(6.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.AssignmentLate,
                                        contentDescription = stringResource(R.string.label_reject_place),
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }


                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = place.name,
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = place.category.displayName,
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(12.dp))
            ){
                Map(
                    places = listOf(place),
                    activateClick = false,
                    followUserLocation = false,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(Modifier.height(16.dp))

            CreatorInfoCard(
                name = creator.completeName,
                username = creator.username,
                date = formatDate(place.creationDate),
                avatar = R.drawable.avatar
            )

            Spacer(Modifier.height(16.dp))
            HorizontalDivider()

            Spacer(Modifier.height(12.dp))

            val toastMsg = stringResource(R.string.label_review_already_replied)
            val replyBackgroundColor = colorResource(R.color.gray_more_light)

            place.reviews.forEach { review ->
                CommentItem(
                    userName = usersViewModel.findUserById(review.userId)?.completeName ?: "",
                    subject = review.subject,
                    comment = review.description,
                    rating = review.rating.toInt(),
                    canAnswer = isCreator,
                    onClick = {
                        if (review.creatorReply.isNullOrBlank()) {
                            replyToReview = review.id
                        } else {
                            Toast.makeText(
                                context,
                                toastMsg,
                                Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                )

                // Si ya existe una respuesta
                if (!review.creatorReply.isNullOrBlank()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 56.dp, bottom = 8.dp)
                            .background(replyBackgroundColor, RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "${stringResource(R.string.label_creator_msg)}: ${review.creatorReply}",
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                HorizontalDivider(thickness = 1.dp, color = Color(0xFFE0E0E0))
            }

            if (user != null && user.id != place.createdById) {
                Spacer(Modifier.height(12.dp))
                Button(
                    onClick = {
                        onNavigateToNewComment(place.id)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.primary),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.align (Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = stringResource(R.string.label_add_new_comment)
                    )
                }
            }

            Spacer(Modifier.height(20.dp))
        }

    }
}
