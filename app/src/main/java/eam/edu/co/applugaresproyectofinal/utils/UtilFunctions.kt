package eam.edu.co.applugaresproyectofinal.utils
import android.content.Context
import androidx.compose.ui.res.stringResource
import eam.edu.co.applugaresproyectofinal.R

import java.time.DayOfWeek

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