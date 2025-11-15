package eam.edu.co.applugaresproyectofinal.model

data class User(
    var id: String = "",
    var name: String = "",
    var lastName: String = "",
    val username: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val password: String = "",
    val country: String = "Colombia",
    val city: String = "Armenia",
    val profilePicture: String? = "",
    val role: Role = Role.USER,
    var favorites: List<String> = emptyList<String>()
){
    val completeName: String
        get() = "$name $lastName"
}