package edu.unicauca.aplimovil.safekids.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first

class FirestoreRepository {

    private val db = FirebaseFirestore.getInstance()

    // Sincronizar los Teachers de Firestore a Room
    fun syncTeachersFromFirestoreToRoom(teacherDao: TeacherDao) {
        db.collection("teachers")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val teacher = document.toObject(Teacher::class.java)
                    // Insertar o actualizar el teacher en la base de datos local (Room)
                    GlobalScope.launch(Dispatchers.IO) {
                        teacherDao.insert(teacher)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w("FirestoreSync", "Error getting documents: ", exception)
            }
    }

    // Sincronizar los Guardians de Firestore a Room
    fun syncGuardiansFromFirestoreToRoom(guardianDao: GuardianDao) {
        db.collection("guardians")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val guardian = document.toObject(Guardian::class.java)
                    // Insertar o actualizar el guardian en la base de datos local (Room)
                    GlobalScope.launch(Dispatchers.IO) {
                        guardianDao.insert(guardian)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w("FirestoreSync", "Error getting documents: ", exception)
            }
    }

    // Sincronizar los Teachers de Room a Firestore
    fun syncTeachersFromRoomToFirestore(teacherDao: TeacherDao) {
        GlobalScope.launch(Dispatchers.IO) {
            val teachers = teacherDao.getAllTeachers().first() // Obtener todos los Teachers desde Room
            for (teacher in teachers) {
                val teacherData = hashMapOf(
                    "teacher_id" to teacher.teacher_id,
                    "name" to teacher.name,
                    "password" to teacher.password
                )

                db.collection("teachers").document(teacher.teacher_id)
                    .set(teacherData)
                    .addOnSuccessListener {
                        Log.d("Firebase", "Teacher successfully added!")
                    }
                    .addOnFailureListener { e ->
                        Log.w("Firebase", "Error adding teacher", e)
                    }
            }
        }
    }

    // Sincronizar los Guardians de Room a Firestore
    fun syncGuardiansFromRoomToFirestore(guardianDao: GuardianDao) {
        GlobalScope.launch(Dispatchers.IO) {
            val guardians = guardianDao.getAllGuardians().first() // Obtener todos los Guardians desde Room
            for (guardian in guardians) {
                val guardianData = hashMapOf(
                    "guardian_id" to guardian.guardian_id,
                    "name" to guardian.name,
                    "password" to guardian.password,
                    "balance" to guardian.balance
                )

                db.collection("guardians").document(guardian.guardian_id)
                    .set(guardianData)
                    .addOnSuccessListener {
                        Log.d("Firebase", "Guardian successfully added!")
                    }
                    .addOnFailureListener { e ->
                        Log.w("Firebase", "Error adding guardian", e)
                    }
            }
        }
    }

    // Escuchar cambios en tiempo real de los Teachers en Firestore y actualizarlos en Room
    fun listenForRealTimeChangesForTeacher(teacherDao: TeacherDao) {
        db.collection("teachers")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w("FirestoreSync", "Listen failed.", e)
                    return@addSnapshotListener
                }
                for (doc in snapshots!!) {
                    val teacher = doc.toObject(Teacher::class.java)
                    GlobalScope.launch(Dispatchers.IO) {
                        teacherDao.insert(teacher)  // Insertar o actualizar el teacher en Room
                    }
                }
            }
    }

    // Escuchar cambios en tiempo real de los Guardians en Firestore y actualizarlos en Room
    fun listenForRealTimeChangesForGuardian(guardianDao: GuardianDao) {
        db.collection("guardians")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w("FirestoreSync", "Listen failed.", e)
                    return@addSnapshotListener
                }
                for (doc in snapshots!!) {
                    val guardian = doc.toObject(Guardian::class.java)
                    GlobalScope.launch(Dispatchers.IO) {
                        guardianDao.insert(guardian)  // Insertar o actualizar el guardian en Room
                    }
                }
            }
    }
}
