package eam.edu.co.applugaresproyectofinal.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

enum class MessageType { SUCCESS, ERROR, WARNING, INFO }

@Composable
fun CustomSnackbar(
    message: String,
    type: MessageType,
    visible: Boolean,
    onDismiss: () -> Unit
) {

    LaunchedEffect(visible) {
        if (visible) {
            delay(2500)
            onDismiss()
        }
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(initialOffsetY = { -80 }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { -80 }) + fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .shadow(4.dp, RoundedCornerShape(12.dp))
                .background(Color(0xFFE5ECFF), RoundedCornerShape(12.dp)) // azul claro
                .padding(14.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                val icon = when (type) {
                    MessageType.SUCCESS -> Icons.Outlined.CheckCircle
                    MessageType.ERROR -> Icons.Outlined.Error
                    MessageType.WARNING -> Icons.Outlined.Warning
                    MessageType.INFO -> Icons.Outlined.Info
                }

                val color = when (type) {
                    MessageType.SUCCESS -> Color(0xFF2E7D32)
                    MessageType.ERROR -> Color(0xFFC62828)
                    MessageType.WARNING -> Color(0xFFF9A825)
                    MessageType.INFO -> Color(0xFF1976D2)
                }

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = message,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}