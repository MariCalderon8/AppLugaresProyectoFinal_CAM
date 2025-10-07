package eam.edu.co.applugaresproyectofinal.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import eam.edu.co.applugaresproyectofinal.model.ScheduleDay
import java.time.DayOfWeek


@Composable
fun DaySelector(
    days: List<ScheduleDay> = listOf(
        ScheduleDay("L", DayOfWeek.MONDAY),
        ScheduleDay("M", DayOfWeek.TUESDAY),
        ScheduleDay("X", DayOfWeek.WEDNESDAY),
        ScheduleDay("J", DayOfWeek.THURSDAY),
        ScheduleDay("V", DayOfWeek.FRIDAY),
        ScheduleDay("S", DayOfWeek.SATURDAY),
        ScheduleDay("D", DayOfWeek.SUNDAY)
    ),
    selectedDays: Set<ScheduleDay>,
    onDayToggle: (ScheduleDay) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
        ) {
        days.forEach {day ->
            val isSelected = day in selectedDays
            CircleButton(
                onClick = { onDayToggle(day) },
                size = 40,
                text = day.displayDay,
                containerColor = if (isSelected) Color(0xFF7A4EE5) else Color.Transparent,
                contentColor = if (isSelected) Color.White else Color(0xFF7A4EE5),
                borderColor = Color(0xFF7A4EE5) // siempre borde morado
            )
        }
    }
}