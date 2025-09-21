package eam.edu.co.applugaresproyectofinal.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import eam.edu.co.applugaresproyectofinal.R
@Composable
fun Label(
    text: String,
    isRequired: Boolean = false,
    textColor: Color =  colorResource(R.color.gray_text),
    fontSize: Int = 14,
) {
    val label = if (isRequired) {
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = colorResource(R.color.red_required))) {
                append("* ")
            }
            append(text)
        }
    } else {
        buildAnnotatedString {
            append(text)
        }
    }

    Text(
        modifier = Modifier.fillMaxWidth(),
        text = label,
        color = textColor,
        fontSize = fontSize.sp
    )
}
