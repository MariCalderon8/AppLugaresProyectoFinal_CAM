package eam.edu.co.applugaresproyectofinal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import eam.edu.co.applugaresproyectofinal.R
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleDialog(onDimiss: () -> Unit) {

    var selectedDays by remember { mutableStateOf(setOf<String>()) }

    // Estados para los dos pickers
    val openTimeState = rememberTimePickerState(
        initialHour = 7,
        initialMinute = 0,
        is24Hour = false
    )
    val closeTimeState = rememberTimePickerState(
        initialHour = 17,
        initialMinute = 0,
        is24Hour = false
    )

    Dialog(onDismissRequest = { onDimiss() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEDE0F8), RoundedCornerShape(16.dp))
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    stringResource(R.string.add_schedule),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                IconButton( onClick = { onDimiss() }) {
                    Icon(Icons.Default.Close, contentDescription = stringResource(R.string.close_dialog))
                }
            }

            Text(stringResource(R.string.select_days))
            DaySelector(
                selectedDays = selectedDays,
                onDayToggle = { dia ->
                    selectedDays = if (dia in selectedDays) {
                        selectedDays - dia // desmarcar
                    } else {
                        selectedDays + dia // marcar
                    }
                }
            )

            Spacer(Modifier.height(30.dp))

            // TimePicker de apertura
            Text(stringResource(R.string.open_hour_txt), fontWeight = FontWeight.SemiBold)
            TimeInput(state = openTimeState)

            Spacer(Modifier.height(30.dp))

            // TimePicker de cierre
            Text(stringResource(R.string.close_hour_txt), fontWeight = FontWeight.SemiBold)
            TimeInput(state = closeTimeState)

            Spacer(Modifier.height(30.dp))

            Button(
                onClick = { onDimiss() },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7A4EE5))
            ) {
                Text(stringResource(R.string.add_schedule), color = Color.White)
            }
        }
    }

}