package edu.unicauca.aplimovil.safekids.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import edu.unicauca.aplimovil.safekids.data.Guardian
import edu.unicauca.aplimovil.safekids.data.GuardiansRepository
import edu.unicauca.aplimovil.safekids.data.Teacher
import edu.unicauca.aplimovil.safekids.data.TeachersRepository
import kotlinx.coroutines.flow.firstOrNull

class LoginViewModel(
    private val guardiansRepository: GuardiansRepository,
    private val teachersRepository: TeachersRepository
) : ViewModel() {

    var loginUiState by mutableStateOf(LoginUiState())
        private set

    fun updateUiState(details: LoginDetails) {
        loginUiState = LoginUiState(
            details = details,
            isLoginValid = validateInput(details)
        )
    }

    private fun validateInput(details: LoginDetails): Boolean {
        return details.id.isNotBlank() && details.password.isNotBlank()
    }

    /**
     * Intenta loguear al usuario verificando en la tabla de Guardianes primero, luego en Teachers.
     * @return Par (tipoUsuario, objeto) si es válido; si no, devuelve null.
     */
    suspend fun attemptGuardianLogin(): Guardian? {
        val id = loginUiState.details.id
        val password = loginUiState.details.password

        if (!validateInput(loginUiState.details)) return null

        val guardian = guardiansRepository.getGuardianStream(id).firstOrNull()
        return if (guardian != null && guardian.password == password) guardian else null
    }

    suspend fun attemptTeacherLogin(): Teacher? {
        val id = loginUiState.details.id
        val password = loginUiState.details.password

        if (!validateInput(loginUiState.details)) return null

        val teacher = teachersRepository.getTeacherStream(id).firstOrNull()
        return if (teacher != null && teacher.password == password) teacher else null
    }

}

data class LoginUiState(
    val details: LoginDetails = LoginDetails(),
    val isLoginValid: Boolean = false
)

data class LoginDetails(
    val id: String = "",
    val password: String = ""
)
