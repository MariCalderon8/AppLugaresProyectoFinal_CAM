package eam.edu.co.applugaresproyectofinal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ScheduleItemCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE6DAF7), RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.DateRange,
                    contentDescription = "Horario",
                    tint = Color(0xFF4A2C92)
                )
                Spacer(Modifier.width(8.dp))
                Column {
                    Text("Lunes", fontWeight = FontWeight.Bold, color = Color(0xFF4A2C92))
                    Text("7:00 am - 5:00 pm", color = Color.DarkGray)
                }
            }
            IconButton(onClick = { /* en futuro: eliminar */ }) {
                Icon(Icons.Default.Close, contentDescription = "Eliminar horario")
            }
        }
    }
}
