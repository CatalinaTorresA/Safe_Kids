package edu.unicauca.aplimovil.safekids.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import edu.unicauca.aplimovil.safekids.InventoryApplication
import edu.unicauca.aplimovil.safekids.data.MoneyRepository
import edu.unicauca.aplimovil.safekids.ui.viewmodel.GuardianMoneyProfileViewModel
import edu.unicauca.aplimovil.safekids.ui.viewmodel.GuardianProfileViewModel
import edu.unicauca.aplimovil.safekids.ui.viewmodel.ItemEntryViewModel
import edu.unicauca.aplimovil.safekids.ui.viewmodel.LoginViewModel
import edu.unicauca.aplimovil.safekids.ui.viewmodel.TeacherProfileViewModel

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
            TeacherProfileViewModel(inventoryApplication().container.guardiansRepository,
                inventoryApplication().container.teachersRepository,
                inventoryApplication().container.studentCoursesRepository)
        }
        initializer {
            GuardianProfileViewModel(inventoryApplication().container.guardiansRepository,
                inventoryApplication().container.studentGuardiansRepository)
        }
        initializer {
            GuardianMoneyProfileViewModel(inventoryApplication().container.guardiansRepository,
                inventoryApplication().container.studentGuardiansRepository,
                inventoryApplication().container.moneyRepository)
        }

    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.inventoryApplication(): InventoryApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)
