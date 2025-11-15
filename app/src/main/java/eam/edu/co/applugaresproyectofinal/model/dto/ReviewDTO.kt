package eam.edu.co.applugaresproyectofinal.model.dto

data class ReviewDTO(
    val id: String = "",
    val userId: String = "",
    val subject: String = "",
    val description: String = "",
    val rating: Double = 0.0,
    val creatorReply: String? = null
)