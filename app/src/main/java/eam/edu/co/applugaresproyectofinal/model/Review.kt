package eam.edu.co.applugaresproyectofinal.model

import java.util.UUID

data class Review(
    val id: String,
    val userId: String,
    val subject: String,
    val description: String,
    val rating: Double,
    var creatorReply: String? = null
)
