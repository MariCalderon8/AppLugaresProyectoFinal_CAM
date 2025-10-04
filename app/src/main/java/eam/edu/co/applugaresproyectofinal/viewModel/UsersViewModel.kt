package eam.edu.co.applugaresproyectofinal.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import eam.edu.co.applugaresproyectofinal.model.Role
import eam.edu.co.applugaresproyectofinal.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class UsersViewModel: ViewModel() {
    private val _users = MutableStateFlow(emptyList<User>())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    init {
        loadUsers()
    }
    private fun loadUsers() {
        _users.value = listOf(
            User(
                id = "1",
                name = "Juan Pérez",
                username = "juanp",
                email = "user@example.com",
                password = "12345",
                phoneNumber = "12345",
                city = "Bogotá",
                profilePicture = null,
                role = Role.USER,
                favorites = emptyList()
            ),
            User(
                id = "2",
                name = "María Gómez",
                username = "mariag",
                email = "admin@example.com",
                password = "12345",
                phoneNumber = "12345",
                city = "Medellín",
                profilePicture = "https://example.com/maria.jpg",
                role = Role.ADMIN,
                favorites = emptyList()
            ),
            User(
                id = "3",
                name = "Carlos López",
                username = "carlitos",
                email = "carlos.lopez@example.com",
                password = "12345",
                phoneNumber = "12345",
                city = "Cali",
                profilePicture = null,
                role = Role.USER,
                favorites = emptyList()
            )
        )
    }

    // Crear usuario
    fun addUser(user: User) {
        _users.value = _users.value + user
    }

    // Editar usuario
    fun updateUser(user: User) {
        _users.value = _users.value.map { if (it.id == user.id) user else it }
    }

    // Buscar usuario por ID
    fun findUserById(id: String): User? {
        return _users.value.find { it.id == id }
    }

    // Login simple (usuario + contraseña)
    fun login(email: String, password: String): User? {
        return _users.value.find { it.email == email && it.password == password }
    }
}