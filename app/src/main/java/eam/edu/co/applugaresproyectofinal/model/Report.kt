package eam.edu.co.applugaresproyectofinal.model

import java.util.UUID

data class Report(
    val id: UUID,
    val userId: UUID,
    val subject: String,
    val description: String
)