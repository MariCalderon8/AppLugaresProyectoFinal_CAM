package eam.edu.co.applugaresproyectofinal.model

data class User(
    val id: String,
    var name: String,
    var lastName: String,
    val completeName: String,
    val username: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val country: String = "Colombia",
    val city: String,
    val profilePicture: String? = "",
    val role: Role,
    var favorites: List<String> = emptyList<String>()
)