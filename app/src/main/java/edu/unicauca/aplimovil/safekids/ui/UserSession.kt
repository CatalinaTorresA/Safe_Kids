package edu.unicauca.aplimovil.safekids.ui

object UserSession {
    var userId: String = ""
        private set

    fun updateUserId(id: String) {
        userId = id
    }
}
