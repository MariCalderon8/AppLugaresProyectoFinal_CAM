package eam.edu.co.applugaresproyectofinal.model

import java.util.UUID

data class Report(
    val id: String,
    val userId: String,
    val subject: String,
    val description: String
)