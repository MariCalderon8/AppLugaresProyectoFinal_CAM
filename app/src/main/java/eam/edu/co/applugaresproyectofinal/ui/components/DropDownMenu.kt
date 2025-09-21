package eam.edu.co.applugaresproyectofinal.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eam.edu.co.applugaresproyectofinal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(
    label: String,
    list: List<String>,
    onValueChange: (String) -> Unit,
    icon: ImageVector ?= null,
    supportingText: String,
    fontSize: Int = 14,
    textColor: Color = colorResource(R.color.gray_text),
    iconColor: Color = colorResource(R.color.gray_text),
    borderColors: androidx.compose.material3.TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor  = colorResource(R.color.teal_700),
        unfocusedBorderColor  = colorResource(R.color.light_gray),
    )
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }

    var isError by rememberSaveable { mutableStateOf(false) }

    Column {
        Text(
            text = label,
            color = textColor,
            fontSize = fontSize.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(
                readOnly = true,
                value = selectedItem,
                colors = borderColors,
                onValueChange = {},
                supportingText = {
                    if (isError) {
                        Text(supportingText)
                    }
                },
                leadingIcon = if (icon != null) {
                    {
                        Icon(
                            imageVector = icon,
                            contentDescription = label,
                            tint = iconColor,
                        )
                    }
                } else null,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                list.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it) },
                        onClick = {
                            selectedItem = it
                            onValueChange(selectedItem)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}