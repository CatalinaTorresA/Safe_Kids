package edu.unicauca.aplimovil.safekids.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.unicauca.aplimovil.safekids.data.GuardiansRepository
import edu.unicauca.aplimovil.safekids.data.StudentGuardiansRepository
import edu.unicauca.aplimovil.safekids.data.MoneyRepository
import kotlinx.coroutines.flow.firstOrNull
import edu.unicauca.aplimovil.safekids.ui.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GuardianMoneyProfileViewModel(
    private val guardiansRepository: GuardiansRepository,
    private val studentGuardiansRepository: StudentGuardiansRepository,
    private val moneyRepository: MoneyRepository
) : ViewModel() {

    var GuardianMoneyprofileUiState by mutableStateOf(GuardianMoneyProfileUiState())
        private set

    private val _students = MutableStateFlow<List<StudentUiState>>(emptyList())
    val students: StateFlow<List<StudentUiState>> = _students

    private val _moneyList = MutableStateFlow<List<MoneyUiState>>(emptyList())
    val moneyList: StateFlow<List<MoneyUiState>> = _moneyList

    init {
        viewModelScope.launch {
            val result = loadStudents()
            _students.value = result
            Log.d("ProfileViewModel", "updating courses equal to: $_students")
        }
    }

    fun updateStudentId(newId: String) {
        viewModelScope.launch {
            _moneyList.value = loadMoneyForStudent(newId) // Actualiza la lista de dinero para el estudiante
        }
    }

    suspend fun loadStudents(): List<StudentUiState> {
        val userId = UserSession.userId
        Log.d("ProfileViewModel", "loadStudentsByCourse called with userId: $userId")

        return if (userId.isNotBlank()) {
            val studentEntities = studentGuardiansRepository.getStudentsOfGuardian(userId).firstOrNull() ?: emptyList()

            val studentsList = studentEntities.map { student ->
                StudentUiState(name = student.name, student_id = student.student_id)
            }
            Log.d("ProfileViewModel", "Students loaded: $studentsList")
            studentsList
        } else {
            Log.d("ProfileViewModel", "UserId is blank")
            emptyList()
        }
    }

    suspend fun loadMoneyForStudent(studentId: String): List<MoneyUiState> {
        Log.d("ProfileViewModel", "loadMoneyForStudent called with studentId: $studentId")

        return if (studentId.isNotBlank()) {
            // Obtener las transacciones de dinero para el estudiante
            val moneyRecords = moneyRepository.getAllMoneyForStudent(studentId).firstOrNull() ?: emptyList()

            // Convertir los registros de dinero en MoneyUiState
            val moneyList = moneyRecords.map { money ->
                MoneyUiState(
                    transactionId = money.transactionId,
                    studentId = money.studentId,
                    amount = money.amount,
                    transactionDate = money.transactionDate,
                    teacherId = money.teacherId,
                    guardianId = money.guardianId
                )
            }

            Log.d("ProfileViewModel", "Money records loaded: $moneyList")
            moneyList
        } else {
            Log.d("ProfileViewModel", "StudentId is blank")
            emptyList()
        }
    }

}

data class GuardianMoneyProfileUiState(
    val details: GuardianBasicInfo = GuardianBasicInfo()
)

data class MoneyUiState(
    val transactionId: Long,
    val studentId: String,
    val amount: Double,
    val transactionDate: String,  // Puedes mantener el formato de fecha como string si es necesario
    val teacherId: String?,
    val guardianId: String?
)


