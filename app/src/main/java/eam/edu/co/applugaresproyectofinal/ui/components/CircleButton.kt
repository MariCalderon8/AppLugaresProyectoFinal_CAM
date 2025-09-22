package eam.edu.co.applugaresproyectofinal.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CircleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Int = 40,
    text: String? = null,
    icon: ImageVector? = null,
    contentDescription: String? = null,
    containerColor: Color = Color.Transparent,
    contentColor: Color = Color.Black,
    borderColor: Color = Color.Gray
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        modifier = modifier.size(size.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        border = BorderStroke(1.dp, borderColor),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            when {
                icon != null -> Icon(
                    imageVector = icon,
                    contentDescription = contentDescription
                )
                text != null -> Text(
                    text = text,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}