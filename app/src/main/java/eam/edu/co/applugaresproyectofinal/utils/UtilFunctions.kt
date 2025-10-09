package eam.edu.co.applugaresproyectofinal.utils
import android.content.Context
import androidx.compose.ui.res.stringResource
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.model.Schedule

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun convertDayToString(context: Context, day: DayOfWeek): String {
    return when (day) {
        DayOfWeek.MONDAY -> context.getString(R.string.MONDAY)
        DayOfWeek.TUESDAY -> context.getString(R.string.TUESDAY)
        DayOfWeek.WEDNESDAY -> context.getString(R.string.WEDNESDAY)
        DayOfWeek.THURSDAY -> context.getString(R.string.THURSDAY)
        DayOfWeek.FRIDAY -> context.getString(R.string.FRIDAY)
        DayOfWeek.SATURDAY -> context.getString(R.string.SATURDAY)
        DayOfWeek.SUNDAY -> context.getString(R.string.SUNDAY)
    }
}

fun formatSchedules(context: Context, schedules: List<Schedule>): String {
    if (schedules.isEmpty()) return ""

    // Ordena los horarios por día de la semana
    val ordered = schedules.sortedBy { it.dayOfWeek.value }

    // Formateador de hora tipo "5:00 p.m."
    val timeFormatter = DateTimeFormatter.ofPattern("h:mm a", Locale.getDefault())

    // Agrupa los días que tienen el mismo horario
    val grouped = ordered.groupBy { it.startTime to it.endTime }

    // Construye el texto
    val parts = grouped.map { (timeRange, days) ->
        val dayNames = days.joinToString(", ") { schedule ->
            convertDayToString(context, schedule.dayOfWeek)
        }

        val (start, end) = timeRange
        val timeText = "${start.format(timeFormatter)} - ${end.format(timeFormatter)}"
            .replace("AM", "a.m.")
            .replace("PM", "p.m.")

        "$dayNames $timeText"
    }

    return parts.joinToString(" | ")
}

// Formato de fecha "'día' de 'mes' del 'año'"
fun formatDate(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", Locale("es", "ES"))
    return dateTime.format(formatter)
}