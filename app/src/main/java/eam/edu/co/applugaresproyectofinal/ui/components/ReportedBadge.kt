package eam.edu.co.applugaresproyectofinal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Announcement
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eam.edu.co.applugaresproyectofinal.model.Status
import eam.edu.co.applugaresproyectofinal.R


@Composable
fun ReportedBadge(
    reportCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(Color(0xFFE5EEFF), RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .height(32.dp)
            .wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.Announcement,
            contentDescription = Status.REPORTED.description,
            tint = Color(0xFF2F2F2F)
        )

        Text(
            text = stringResource(R.string.label_reported_by),
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 13.sp,
            )
        )

        Text(
            text = "$reportCount",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2F2F2F)
            )
        )

        Text(
            text = if (reportCount == 1) stringResource(R.string.label_user) else stringResource(R.string.label_users),
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 13.sp,
                color = Color(0xFF2F2F2F)
            )
        )
    }
}
