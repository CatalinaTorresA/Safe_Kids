package edu.unicauca.aplimovil.safekids.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import edu.unicauca.aplimovil.safekids.InventoryApplication
import edu.unicauca.aplimovil.safekids.ui.viewmodel.ItemEntryViewModel
import edu.unicauca.aplimovil.safekids.ui.viewmodel.LoginViewModel
import edu.unicauca.aplimovil.safekids.ui.viewmodel.ProfileViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEntryViewModel
        initializer {
            ItemEntryViewModel(inventoryApplication().container.itemsRepository)
        }
        initializer {
            LoginViewModel(inventoryApplication().container.guardiansRepository,
                inventoryApplication().container.teachersRepository)
        }
        initializer {
            ProfileViewModel(inventoryApplication().container.guardiansRepository,
                inventoryApplication().container.teachersRepository,
                inventoryApplication().container.studentCoursesRepository)
        }

    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.inventoryApplication(): InventoryApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)
