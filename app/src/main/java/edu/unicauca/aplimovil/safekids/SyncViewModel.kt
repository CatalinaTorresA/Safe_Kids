package edu.unicauca.aplimovil.safekids

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import edu.unicauca.aplimovil.safekids.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SyncViewModel(application: Application) : AndroidViewModel(application) {

    private val teacherDao = InventoryDatabase.getDatabase(application).teacherDao()
    private val guardianDao = InventoryDatabase.getDatabase(application).guardianDao()
    private val firestoreRepository = FirestoreRepository()

    // Sincronización de los Teachers desde Firestore a Room
    fun syncTeachersFromFirestoreToRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            firestoreRepository.syncTeachersFromFirestoreToRoom(teacherDao)
        }
    }

    // Sincronización de los Guardians desde Firestore a Room
    fun syncGuardiansFromFirestoreToRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            firestoreRepository.syncGuardiansFromFirestoreToRoom(guardianDao)
        }
    }

    // Sincronización de los Teachers desde Room a Firestore
    fun syncTeachersFromRoomToFirestore() {
        viewModelScope.launch(Dispatchers.IO) {
            firestoreRepository.syncTeachersFromRoomToFirestore(teacherDao)
        }
    }

    // Sincronización de los Guardians desde Room a Firestore
    fun syncGuardiansFromRoomToFirestore() {
        viewModelScope.launch(Dispatchers.IO) {
            firestoreRepository.syncGuardiansFromRoomToFirestore(guardianDao)
        }
    }

    // Escuchar cambios en tiempo real de Firestore
    fun listenForTeachersRealTimeChanges() {
        firestoreRepository.listenForRealTimeChangesForTeacher(teacherDao)
    }

    fun listenForGuardiansRealTimeChanges() {
        firestoreRepository.listenForRealTimeChangesForGuardian(guardianDao)
    }
}


