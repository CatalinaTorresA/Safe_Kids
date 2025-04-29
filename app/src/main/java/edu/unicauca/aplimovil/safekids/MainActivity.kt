package edu.unicauca.aplimovil.safekids

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {

    // Usamos `by viewModels()` para que Android gestione la creación del ViewModel
    private val syncViewModel: SyncViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(Unit) {
                // Llamamos a las funciones de sincronización o escuchar cambios en tiempo real
                syncViewModel.syncTeachersFromFirestoreToRoom()
                syncViewModel.syncGuardiansFromFirestoreToRoom()
                syncViewModel.listenForTeachersRealTimeChanges()
                syncViewModel.listenForGuardiansRealTimeChanges()
            }

            AppNavigation()
        }
    }
}


