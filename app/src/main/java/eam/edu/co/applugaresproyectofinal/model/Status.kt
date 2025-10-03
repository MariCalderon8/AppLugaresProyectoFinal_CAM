package eam.edu.co.applugaresproyectofinal.model

enum class Status (
    val code: Int,
    val description: String
){
    APPROVED(1, "Aprobado"),
    PENDING_FOR_APPROVAL(2, "Pendiente de aprobación"),
    REJECTED(3, "Rechazado"),
    CANCELED(4, "Cancelado")
}