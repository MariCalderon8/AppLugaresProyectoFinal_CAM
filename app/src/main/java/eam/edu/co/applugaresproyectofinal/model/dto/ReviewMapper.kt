package eam.edu.co.applugaresproyectofinal.model.dto

import eam.edu.co.applugaresproyectofinal.model.Review

fun Review.toDTO(): ReviewDTO =
    ReviewDTO(
        id = id,
        userId = userId,
        subject = subject,
        description = description,
        rating = rating,
        creatorReply = creatorReply
    )

fun ReviewDTO.toModel(): Review =
    Review(
        id = id,
        userId = userId,
        subject = subject,
        description = description,
        rating = rating,
        creatorReply = creatorReply
    )
