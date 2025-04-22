package edu.unicauca.aplimovil.safekids.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import edu.unicauca.aplimovil.safekids.data.Guardian
import edu.unicauca.aplimovil.safekids.data.GuardiansRepository
import edu.unicauca.aplimovil.safekids.data.Teacher
import edu.unicauca.aplimovil.safekids.data.TeachersRepository
import kotlinx.coroutines.flow.firstOrNull
import edu.unicauca.aplimovil.safekids.ui.UserSession

class ProfileViewModel(
    private val guardiansRepository: GuardiansRepository,
    private val teachersRepository: TeachersRepository
) : ViewModel() {

    var profileUiState by mutableStateOf(ProfileUiState())
        private set

    fun updateUserId(id: String) {
        Log.d("ProfileViewModel", "updateUserId called with id: $id")
        UserSession.updateUserId(id)
    }

    suspend fun loadGuardian(): UserBasicInfo? {
        val userId = UserSession.userId
        Log.d("ProfileViewModel", "loadGuardian called with userId: $userId")
        return if (userId.isNotBlank()) {
            Log.d("ProfileViewModel", "UserId is not blank, querying guardian...")
            guardiansRepository.getGuardianStream(userId).firstOrNull()?.let {
                Log.d("ProfileViewModel", "Guardian found: ${it.name}")
                UserBasicInfo(id = userId, name = it.name)
            } ?: run {
                Log.d("ProfileViewModel", "No guardian found for userId: $userId")
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
}

data class ProfileUiState(
    val details: UserBasicInfo = UserBasicInfo(),
)

data class UserBasicInfo(
    val id: String = "",
    val name: String = ""
)
