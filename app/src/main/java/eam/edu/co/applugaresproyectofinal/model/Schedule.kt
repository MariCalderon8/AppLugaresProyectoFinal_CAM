package eam.edu.co.applugaresproyectofinal.model

import java.time.DayOfWeek
import java.time.LocalTime

data class Schedule(
    val dayOfWeek: DayOfWeek,
    val startTime: LocalTime,
    val endTime: LocalTime
)