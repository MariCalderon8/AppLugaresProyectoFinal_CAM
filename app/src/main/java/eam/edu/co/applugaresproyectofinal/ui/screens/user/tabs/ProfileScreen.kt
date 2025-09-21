package eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs

import SignOutButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.ui.components.SectionHeader
import eam.edu.co.applugaresproyectofinal.ui.components.StatsCard
import eam.edu.co.applugaresproyectofinal.ui.components.ProfileOptionItem

@Composable
fun ProfileScreen(
    onEditProfileClick: () -> Unit = {},
    onViewMyPlacesClick: () -> Unit = {},
    onToggleDarkModeClick: () -> Unit = {},
    onSignOutClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        //IMAGEN DE PERFIL PROVICIONAL
        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = stringResource(R.string.profile_image),
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )

        Spacer(Modifier.height(16.dp))

        ProfileOptionItem(
            text = stringResource(R.string.btn_edit_profile),
            onClick = { onEditProfileClick() },
            cornerRadius = 24,
            fullWidth = false,
            modifier = Modifier.align(Alignment.CenterHorizontally)

        )
        Spacer(Modifier.height(24.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatsCard(
                count = 2,
                label = stringResource(R.string.label_places_created),
                modifier = Modifier.weight(1f)
            )
            StatsCard(
                count = 12,
                label = stringResource(R.string.label_favorite_places),
                modifier = Modifier.weight(1f)
            )
            StatsCard(
                count = 55,
                label = stringResource(R.string.label_received_comments),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(Modifier.height(24.dp))

        SectionHeader(text = stringResource(R.string.label_my_places))
        ProfileOptionItem(
            icon = Icons.Filled.LocationOn,
            text = stringResource(R.string.btn_view_my_places),
            fullWidth = true,
            onClick = { onViewMyPlacesClick() }
        )
        Spacer(Modifier.height(16.dp))

        SectionHeader(text = stringResource(R.string.label_settings))
        ProfileOptionItem(
            icon = Icons.Filled.DarkMode,
            text = stringResource(R.string.btn_toggle_dark_mode),
            fullWidth = true,
            onClick = { onToggleDarkModeClick() }
        )
        Spacer(Modifier.height(16.dp))

        Spacer(Modifier.height(16.dp))

        SignOutButton(
            icon = Icons.AutoMirrored.Filled.Logout,
            text = stringResource(R.string.btn_logout),
            onClick = { onSignOutClick() }
        )

    }
}