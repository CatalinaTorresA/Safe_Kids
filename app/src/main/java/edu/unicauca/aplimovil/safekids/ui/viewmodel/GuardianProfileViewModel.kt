package edu.unicauca.aplimovil.safekids.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.unicauca.aplimovil.safekids.data.GuardiansRepository
import edu.unicauca.aplimovil.safekids.data.StudentGuardiansRepository
import kotlinx.coroutines.flow.firstOrNull
import edu.unicauca.aplimovil.safekids.ui.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GuardianProfileViewModel(
    private val guardiansRepository: GuardiansRepository,
    private val studentGuardiansRepository: StudentGuardiansRepository
) : ViewModel() {

    var GuardianprofileUiState by mutableStateOf(GuardianProfileUiState())
        private set

    private val _students = MutableStateFlow<List<StudentUiState>>(emptyList())
    val students: StateFlow<List<StudentUiState>> = _students

    init {
        viewModelScope.launch {
            val result = loadStudents()
            _students.value = result
            Log.d("ProfileViewModel", "updating courses equal to: $_students")
        }
    }

    fun updateUserId(id: String) {
        Log.d("ProfileViewModel", "updateUserId called with id: $id")
        UserSession.updateUserId(id)
    }

    suspend fun loadGuardian(): GuardianBasicInfo? {
        val userId = UserSession.userId
        Log.d("ProfileViewModel", "loadGuardian called with userId: $userId")
        return if (userId.isNotBlank()) {
            Log.d("ProfileViewModel", "UserId is not blank, querying guardian...")
            guardiansRepository.getGuardianStream(userId).firstOrNull()?.let {
                Log.d("ProfileViewModel", "Guardian found: ${it.name}")
                GuardianBasicInfo(id = userId, name = it.name)
            } ?: run {
                Log.d("ProfileViewModel", "No guardian found for userId: $userId")
                null
            }
        } else {
            Log.d("ProfileViewModel", "UserId is blank")
            null
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
}

data class StudentUiState(
    val name: String,
    val student_id: String
)

data class GuardianProfileUiState(
    val details: GuardianBasicInfo = GuardianBasicInfo(),
)

data class GuardianBasicInfo(
    val id: String = "",
    val name: String = ""
)
