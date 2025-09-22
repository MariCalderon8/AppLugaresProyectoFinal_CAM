package eam.edu.co.applugaresproyectofinal.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun DaySelector(
    days: List<String> = listOf("L", "M", "X", "J", "V", "S", "D"),
    selectedDays: Set<String>,
    onDayToggle: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
        ) {
        days.forEach { day ->
            val isSelected = day in selectedDays
            CircleButton(
                onClick = { onDayToggle(day) },
                size = 40,
                text = day,
                containerColor = if (isSelected) Color(0xFF7A4EE5) else Color.Transparent,
                contentColor = if (isSelected) Color.White else Color(0xFF7A4EE5),
                borderColor = Color(0xFF7A4EE5) // siempre borde morado
            )
        }
    }
}