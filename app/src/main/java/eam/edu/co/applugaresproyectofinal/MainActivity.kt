package eam.edu.co.applugaresproyectofinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import eam.edu.co.applugaresproyectofinal.ui.screens.Navigation
import eam.edu.co.applugaresproyectofinal.ui.theme.AppLugaresProyectoFinalTheme
import eam.edu.co.applugaresproyectofinal.viewModel.MainViewModel
import eam.edu.co.applugaresproyectofinal.viewModel.PlacesViewModel
import eam.edu.co.applugaresproyectofinal.viewModel.UsersViewModel

class MainActivity : ComponentActivity() {
    private val userViewModel: UsersViewModel by viewModels()
    private val placesViewModel: PlacesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val mainViewModel = MainViewModel(
            placesViewModel = placesViewModel,
            usersViewModel = userViewModel
        )
        setContent {
            AppLugaresProyectoFinalTheme {
                Navigation(mainViewModel = mainViewModel)
            }
        }
    }
}
