package edu.unicauca.aplimovil.safekids

import android.app.Application
import com.google.firebase.FirebaseApp
import edu.unicauca.aplimovil.safekids.data.AppContainer
import edu.unicauca.aplimovil.safekids.data.AppDataContainer

class InventoryApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        // Inicializa Firebase
        FirebaseApp.initializeApp(this)  // Asegúrate de que esta línea esté presente

        container = AppDataContainer(this)
    }
}
