package eam.edu.co.applugaresproyectofinal.model.dto

import eam.edu.co.applugaresproyectofinal.model.Report
import eam.edu.co.applugaresproyectofinal.model.Review

data class PlaceDTO(
    val id: String = "",
    val images: List<String> = emptyList(),
    val description: String = "",
    val name: String = "",
    val phone: String = "",
    val category: String = "",
    val reviews: List<ReviewDTO> = emptyList(),
    val createdById: String = "",
    val handledBy: String? = null,
    val status: String = "",
    val reports: List<ReportDTO> = emptyList(),
    val address: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val scheduleList: List<ScheduleDTO> = emptyList(),
    val creationDate: Long = 0L
)
