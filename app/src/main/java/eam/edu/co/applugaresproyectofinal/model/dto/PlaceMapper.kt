package eam.edu.co.applugaresproyectofinal.model.dto

import eam.edu.co.applugaresproyectofinal.model.*
import java.time.*

fun Place.toDTO(): PlaceDTO {
    return PlaceDTO(
        id = id,
        images = images,
        description = description,
        name = name,
        phone = phone,
        category = category.name,
        reviews = reviews.map { it.toDTO() },
        createdById = createdById,
        handledBy = handledBy,
        status = status.name,
        reports = reports.map { it.toDTO() },
        address = address,
        latitude = location.latitude,
        longitude = location.longitude,
        scheduleList = scheduleList.map {
            ScheduleDTO(
                dayOfWeek = it.dayOfWeek.name,
                startTime = it.startTime.toString(),
                endTime = it.endTime.toString()
            )
        },
        creationDate = creationDate.toEpochSecond(ZoneOffset.UTC)
    )
}

/**
 * Convierte un PlaceDTO (Firestore) a Place (modelo UI)
 */
fun PlaceDTO.toPlace(): Place {
    return Place(
        id = id,
        images = images,
        description = description,
        name = name,
        phone = phone,
        category = Category.valueOf(category),
        reviews = reviews.map { it.toModel() },      // ✔ convertir DTO → modelo UI
        createdById = createdById,
        handledBy = handledBy,
        status = Status.valueOf(status),
        reports = reports.map { it.toModel() },       // ✔ convertir DTO → modelo UI
        address = address,
        location = Location(latitude, longitude),
        scheduleList = scheduleList.map {
            Schedule(
                dayOfWeek = DayOfWeek.valueOf(it.dayOfWeek),
                startTime = LocalTime.parse(it.startTime),
                endTime = LocalTime.parse(it.endTime)
            )
        },
        creationDate = LocalDateTime.ofEpochSecond(
            creationDate,
            0,
            ZoneOffset.UTC
        )
    )
}
