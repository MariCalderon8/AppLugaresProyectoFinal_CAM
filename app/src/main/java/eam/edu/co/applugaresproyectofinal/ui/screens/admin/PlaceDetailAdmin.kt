package eam.edu.co.applugaresproyectofinal.ui.screens.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import eam.edu.co.applugaresproyectofinal.model.Status
import eam.edu.co.applugaresproyectofinal.model.getColor
import eam.edu.co.applugaresproyectofinal.ui.components.CommentItem
import eam.edu.co.applugaresproyectofinal.ui.components.CreatorInfoCard
import eam.edu.co.applugaresproyectofinal.ui.components.PlaceInfoRow
import eam.edu.co.applugaresproyectofinal.ui.components.RatingBar
import eam.edu.co.applugaresproyectofinal.ui.components.ReportCard
import eam.edu.co.applugaresproyectofinal.ui.components.ReportedBadge
import eam.edu.co.applugaresproyectofinal.ui.components.TagChip
import eam.edu.co.applugaresproyectofinal.ui.components.TopBarCustom
import eam.edu.co.applugaresproyectofinal.ui.screens.LocalMainViewModel
import eam.edu.co.applugaresproyectofinal.utils.formatSchedules
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetailAdminScreen(
    placeId: String,
    onBack: () -> Unit = {}
) {
    val placesViewModel = LocalMainViewModel.current.placesViewModel
    val place = placesViewModel.findPlaceById(placeId) ?: return
    val usersViewModel = LocalMainViewModel.current.usersViewModel
    val creator = usersViewModel.findUserById(place.createdById)?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        TopBarCustom (
            showBack = true,
            onBack = onBack,
            actions = {
                TagChip(
                    text = place.status.description,
                    backgroundColor = place.status.getColor()
                )
            }
        )

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

        if (place.status == Status.APPROVED || place.status == Status.REPORTED) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RatingBar(rating = place.rating.roundToInt())
            }
            Spacer(Modifier.height(8.dp))
        }

        if (place.status == Status.REPORTED) {
            ReportedBadge(15, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))

        }

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

        Image(
            painter = painterResource(id = R.drawable.map),
            contentDescription = stringResource(R.string.label_place_location),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(Modifier.height(16.dp))

        CreatorInfoCard(
            name = creator.name,
            username = creator.username,
            date = stringResource(R.string.label_creator_date),
            avatar = R.drawable.avatar
        )

        Spacer(Modifier.height(16.dp))
        HorizontalDivider()

        Spacer(Modifier.height(12.dp))

        if (place.status != Status.REJECTED) {
            CommentItem(
                userName = stringResource(R.string.sample_comment_user),
                comment = stringResource(R.string.sample_comment_text),
                rating = 5
            )
        }

        if (place.status == Status.REPORTED) {
            Button(
                onClick = {},
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ){
                Text(
                    text = stringResource(R.string.label_show_more)
                )
            }

            Spacer(Modifier.height(16.dp))

            ReportCard(
                reason = "Dirección Falsa",
                description = "Descripción, bla bla bla",
                reporterName = "Juacho",
                reporterUsername = "Juanchito"
            )

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.status_rejected),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = stringResource(R.string.label_delete)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(50),
                    border = ButtonDefaults.outlinedButtonBorder
                ) {
                    Text(
                        text = stringResource(R.string.label_ignore_report)
                    )
                }

            }
        }

        if (place.status == Status.PENDING_FOR_APPROVAL) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.primary),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = stringResource(R.string.btn_moderator_approve)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(50),
                    border = ButtonDefaults.outlinedButtonBorder
                ) {
                    Text(
                        text = stringResource(R.string.btn_moderator_reject)
                    )
                }
            }
        }

        Spacer(Modifier.height(100.dp))
    }
}
