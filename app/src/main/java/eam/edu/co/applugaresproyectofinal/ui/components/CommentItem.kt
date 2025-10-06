package eam.edu.co.applugaresproyectofinal.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import eam.edu.co.applugaresproyectofinal.R

@Composable
fun CommentItem(
    userName: String,
    comment: String,
    rating: Int,
    avatar: Int = R.drawable.avatar,
    canAnswer: Boolean = false,
    onSend: (String) -> Unit = {}
) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Image(
            painter = painterResource(id = avatar),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(Modifier.width(12.dp))
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = userName, color = Color.Black)
                Spacer(Modifier.width(8.dp))
                RatingBar(rating = rating)
            }
            Text(text = comment, color = Color.Gray)
        }
    }
    if (canAnswer) {

        val showReplyBox = remember { mutableStateOf(false) }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    showReplyBox.value = !showReplyBox.value
                }
                .padding(end = 12.dp)
        ) {
            IconButton(
                onClick = { showReplyBox.value = !showReplyBox.value },
            ) {

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.label_answer),
                    tint = Color.Gray
                )
            }
            Text(
                text = stringResource(R.string.label_answer), color = Color.Gray
            )
        }
        if (showReplyBox.value) {
            ReplyBox(onSend = {
                onSend
            })
        }
    }
}
