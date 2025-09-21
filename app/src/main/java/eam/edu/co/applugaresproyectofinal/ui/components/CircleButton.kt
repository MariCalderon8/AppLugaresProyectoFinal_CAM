package eam.edu.co.applugaresproyectofinal.ui.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CircleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Int = 60, // tamaño fijo
    text: String? = null,
    icon: ImageVector? = null,
    contentDescription: String? = null
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        modifier = modifier.size(size.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size((size * 0.6).dp)
        ) {
            when {
                icon != null ->
                {
                    Icon(
                        imageVector = icon,
                        contentDescription = contentDescription
                    )
                }
                text != null -> Text(text = text)
                else -> { }
            }
        }
    }
}