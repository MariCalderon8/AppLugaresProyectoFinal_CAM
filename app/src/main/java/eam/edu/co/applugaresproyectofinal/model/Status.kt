package eam.edu.co.applugaresproyectofinal.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import eam.edu.co.applugaresproyectofinal.R

enum class Status (
    val code: Int,
    val description: String
){
    APPROVED(1, "Aprobado"),
    PENDING_FOR_APPROVAL(2, "Pendiente"),
    REJECTED(3, "Rechazado"),
    REPORTED(4, "Reportado")
}

@Composable
fun Status.getColor(): Color {
    return when (this) {
        Status.APPROVED -> colorResource(R.color.status_approved)
        Status.PENDING_FOR_APPROVAL -> colorResource(R.color.status_pending)
        Status.REJECTED -> colorResource(R.color.status_rejected)
        Status.REPORTED -> colorResource(R.color.status_reported)
    }
}