package eam.edu.co.applugaresproyectofinal.model

import java.util.UUID

data class User(
    val id: String,
    val name: String,
    val username: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val city: String,
    val profilePicture: String? = "",
    val role: Role,
    val favorites: List<String>? = emptyList<String>()
)