package eam.edu.co.applugaresproyectofinal.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CheckBox(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean?) -> Unit,
    textSize: Int = 14
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { onCheckedChange(it) }
        )

        Spacer(modifier = Modifier.width(0.5.dp))

        Text(
            text = label,
            fontSize = textSize.sp
        )
    }
}
