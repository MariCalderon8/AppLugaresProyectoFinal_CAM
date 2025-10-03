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
                id = UUID.randomUUID(),
                name = "Juan Pérez",
                username = "juanp",
                email = "juan.perez@example.com",
                password = "12345",
                city = "Bogotá",
                profilePicture = null,
                role = Role.USER,
                favorites = emptyList()
            ),
            User(
                id = UUID.randomUUID(),
                name = "María Gómez",
                username = "mariag",
                email = "maria.gomez@example.com",
                password = "12345",
                city = "Medellín",
                profilePicture = "https://example.com/maria.jpg",
                role = Role.ADMIN,
                favorites = listOf(UUID.randomUUID(), UUID.randomUUID())
            ),
            User(
                id = UUID.randomUUID(),
                name = "Carlos López",
                username = "carlitos",
                email = "carlos.lopez@example.com",
                password = "12345",
                city = "Cali",
                profilePicture = null,
                role = Role.USER,
                favorites = listOf(UUID.randomUUID())
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
    fun findUserById(id: UUID): User? {
        return _users.value.find { it.id == id }
    }

    // Login simple (usuario + contraseña)
    fun login(username: String, password: String): User? {
        return _users.value.find { it.username == username && it.password == password }
    }
}