package eam.edu.co.applugaresproyectofinal.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import eam.edu.co.applugaresproyectofinal.R

@Composable
fun CommentItem(
    userName: String,
    comment: String,
    rating: Int,
    avatar: Int = R.drawable.avatar
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
}
