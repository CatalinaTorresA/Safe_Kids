package edu.unicauca.aplimovil.safekids.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.unicauca.aplimovil.safekids.data.GuardiansRepository
import edu.unicauca.aplimovil.safekids.data.TeachersRepository
import edu.unicauca.aplimovil.safekids.data.StudentCoursesRepository
import kotlinx.coroutines.flow.firstOrNull
import edu.unicauca.aplimovil.safekids.ui.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TeacherProfileViewModel(
    private val guardiansRepository: GuardiansRepository,
    private val teachersRepository: TeachersRepository,
    private val studentCoursesRepository: StudentCoursesRepository
) : ViewModel() {

    var teacherprofileUiState by mutableStateOf(teacherProfileUiState())
        private set

    private val _cursos = MutableStateFlow<List<CourseUiState>>(emptyList())
    val cursos: StateFlow<List<CourseUiState>> = _cursos

    init {
        // Llama la función dentro de una corrutina
        viewModelScope.launch {
            val result = loadCourses()
            _cursos.value = result
            Log.d("TeacherProfileViewModel", "updating courses equal to: $_cursos")
        }
    }

    fun updateUserId(id: String) {
        Log.d("TeacherProfileViewModel", "updateUserId called with id: $id")
        UserSession.updateUserId(id)
    }

    suspend fun loadGuardian(): UserBasicInfo? {
        val userId = UserSession.userId
        Log.d("TeacherProfileViewModel", "loadGuardian called with userId: $userId")
        return if (userId.isNotBlank()) {
            Log.d("TeacherProfileViewModel", "UserId is not blank, querying guardian...")
            guardiansRepository.getGuardianStream(userId).firstOrNull()?.let {
                Log.d("TeacherProfileViewModel", "Guardian found: ${it.name}")
                UserBasicInfo(id = userId, name = it.name)
            } ?: run {
                Log.d("TeacherProfileViewModel", "No guardian found for userId: $userId")
                null
            }
        } else {
            Log.d("ProfileViewModel", "UserId is blank")
            null
        }
    }

    suspend fun loadTeacher(): UserBasicInfo? {
        val userId = UserSession.userId
        Log.d("ProfileViewModel", "loadTeacher called with userId: $userId")
        return if (userId.isNotBlank()) {
            Log.d("ProfileViewModel", "UserId is not blank, querying teacher...")
            teachersRepository.getTeacherStream(userId).firstOrNull()?.let {
                Log.d("ProfileViewModel", "Teacher found: ${it.name}")
                UserBasicInfo(id = userId, name = it.name)
            } ?: run {
                Log.d("ProfileViewModel", "No teacher found for userId: $userId")
                null
            }
        } else {
            Log.d("ProfileViewModel", "UserId is blank")
            null
        }
    }

    suspend fun loadCourses(): List<CourseUiState> {
        val userId = UserSession.userId
        Log.d("ProfileViewModel", "loadCourses called with userId: $userId")

        return if (userId.isNotBlank()) {
            val courseNames = studentCoursesRepository.getCourseNamesByTeacher(userId).firstOrNull() ?: emptyList()

            val coursesUiList = courseNames.map { course ->
                val count = studentCoursesRepository.countStudentsByCourseName(course).firstOrNull() ?: 0
                CourseUiState(name = course, students = count)
            }
            Log.d("ProfileViewModel", "Courses loaded: $coursesUiList")
            coursesUiList
        } else {
            Log.d("ProfileViewModel", "UserId is blank")
            emptyList()
        }
    }


}

data class CourseUiState(
    val name: String,
    val students: Int
)

data class teacherProfileUiState(
    val details: UserBasicInfo = UserBasicInfo(),
)

data class UserBasicInfo(
    val id: String = "",
    val name: String = ""
)
