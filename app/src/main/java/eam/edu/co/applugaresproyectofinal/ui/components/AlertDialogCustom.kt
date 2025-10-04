package eam.edu.co.applugaresproyectofinal.ui.components

import android.app.AlertDialog
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AlertDialogCustom(
    title: String,
    text: String,
    labelButtonDismiss: String,
    labelButtonConfirm: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
){
    AlertDialog(
        onDismissRequest = { onDismiss },
        title = { Text(title) },
        text = { Text(text = text) },
        confirmButton = {
            Button (onClick = {
                onConfirm()
            }) {
                Text(text = labelButtonConfirm)
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text(text = labelButtonDismiss)
            }
        }
    )
}