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
fun CreatorInfoCard(
    name: String,
    username: String,
    date: String,
    avatar: Int = R.drawable.avatar
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Información del creador")
        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = avatar),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.width(8.dp))
            Column {
                Text(text = name, color = Color.Black)
                Text(text = "@$username", color = Color.Gray)
                Text(text = "Creado el $date", color = Color.Gray)
            }
        }
    }
}
