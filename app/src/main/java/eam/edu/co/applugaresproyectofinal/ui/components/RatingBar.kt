package eam.edu.co.applugaresproyectofinal.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RatingBar(
    rating: Int,
    maxRating: Int = 5,
    modifier: Modifier = Modifier,
    filledColor: Color = Color(0xFFFFC107),
    emptyColor: Color = Color.Gray
) {
    Row(modifier = modifier) {
        for (i in 1..maxRating) {
            if (i <= rating) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Star",
                    tint = filledColor
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Empty Star",
                    tint = emptyColor
                )
            }
        }
    }
}
