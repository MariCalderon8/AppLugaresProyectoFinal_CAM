package eam.edu.co.applugaresproyectofinal.model

import java.util.UUID

data class Place(
    val id: UUID,
    val images: List<String>,
    val description: String,
    val name: String,
    val schedule: Schedule,
    val phone: String,
    val category: Category,
    val reviews: List<Review>,
    val createdById: UUID,
    val approvedById: UUID?,
    val status: Status,
    val reports: List<Report>
)