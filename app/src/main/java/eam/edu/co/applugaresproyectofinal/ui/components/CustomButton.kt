package eam.edu.co.applugaresproyectofinal.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    isLarge: Boolean = false, // controla el tamaño
    modifier: Modifier = Modifier
) {
    Button (
        onClick = onClick,
        modifier = if (isLarge) {
            modifier
                .fillMaxWidth()
        } else {
            modifier
        },
        contentPadding = if (isLarge) {
            PaddingValues(vertical = 15.dp) // más espacio interno
        } else {
            PaddingValues() // tamaño normal
        },
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text)
    }
}
