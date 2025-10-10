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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.ui.components.SectionHeader
import eam.edu.co.applugaresproyectofinal.ui.components.StatsCard
import eam.edu.co.applugaresproyectofinal.ui.components.ProfileOptionItem
import eam.edu.co.applugaresproyectofinal.ui.screens.LocalMainViewModel
import eam.edu.co.applugaresproyectofinal.utils.SharedPrefsUtil

@Composable
fun ProfileScreen(
    onNavigateToUpdateProfile: () -> Unit = {},
    onNavigateToMyPlaces: () -> Unit = {},
    onToggleDarkModeClick: () -> Unit = {},
    onSignOutClick: () -> Unit = {}
) {
    val context = LocalContext.current

    val usersViewModel = LocalMainViewModel.current.usersViewModel
    val user = usersViewModel.findUserById(SharedPrefsUtil.getPreferences(context)["userId"] ?: return)
    val placesViewModel = LocalMainViewModel.current.placesViewModel

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
            .verticalScroll(rememberScrollState()),
    ) {

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
            onClick = { onNavigateToUpdateProfile() },
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
                count = placesViewModel.countPlacesCreatedByUser(user?.id?:""),
                label = stringResource(R.string.label_places_created),
                modifier = Modifier.weight(1f)
            )
            StatsCard(
                count = user?.favorites?.size?:0,
                label = stringResource(R.string.label_favorite_places),
                modifier = Modifier.weight(1f)
            )
            StatsCard(
                count = placesViewModel.countCommentsPlace(user?.id?:""),
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
            onClick = { onNavigateToMyPlaces() }
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