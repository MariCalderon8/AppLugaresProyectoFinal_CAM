//package eam.edu.co.applugaresproyectofinal
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import eam.edu.co.applugaresproyectofinal.ui.screens.Navigation
//import eam.edu.co.applugaresproyectofinal.ui.theme.AppLugaresProyectoFinalTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            AppLugaresProyectoFinalTheme {
//                Navigation()
//            }
//        }
//    }
//}

package eam.edu.co.applugaresproyectofinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs.NewCommentScreen
import eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs.NewReportScreen
import eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs.PlaceDetailScreen
import eam.edu.co.applugaresproyectofinal.ui.theme.AppLugaresProyectoFinalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppLugaresProyectoFinalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 👇 Aquí mostramos directamente PlaceDetailScreen
                    PlaceDetailScreen()
                }
            }
        }
    }
}
