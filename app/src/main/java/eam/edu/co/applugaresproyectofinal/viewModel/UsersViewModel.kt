package eam.edu.co.applugaresproyectofinal.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import eam.edu.co.applugaresproyectofinal.model.Role
import eam.edu.co.applugaresproyectofinal.model.User
import eam.edu.co.applugaresproyectofinal.utils.RequestResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID

class UsersViewModel : ViewModel() {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

    private val _userResult = MutableStateFlow<RequestResult?>(null)
    val userResult: StateFlow<RequestResult?> = _userResult.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    init {
        loadUsersFromFirestore()
    }

    private fun loadUsersFromFirestore() {
        db.collection("users").addSnapshotListener { snapshot, error ->
            if (error != null || snapshot == null) return@addSnapshotListener

            _users.value = snapshot.documents.mapNotNull { doc ->
                doc.toObject(User::class.java)?.apply { id = doc.id }
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _userResult.value = RequestResult.Loading

            if (email.isBlank() || password.isBlank()) {
                _userResult.value = RequestResult.Failure("Por favor complete todos los campos")
                return@launch
            }

            _userResult.value = runCatching { loginFirebase(email, password) }
                .fold(
                    onSuccess = {
                        RequestResult.Success("Login exitoso")
                    },
                    onFailure = {
                        RequestResult.Failure( "Datos de acceso incorrectos")
                    }
                )
        }
    }

    private suspend fun loginFirebase(email: String, password: String){
        val responseUser = auth.signInWithEmailAndPassword(email, password).await()
        val userId = responseUser.user?.uid ?: throw Exception("usuario no encontrado login")
        findUserById(userId)
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            _userResult.value = RequestResult.Loading
            try {
                addUserFirebase(user)
                _userResult.value = RequestResult.Success("Usuario registrado correctamente")
            } catch (e: Exception) {
                _userResult.value = RequestResult.Failure(e.message ?: "Error registrando usuario")
            }
        }
    }

    private suspend fun addUserFirebase(user: User) {
        val responseUser = auth.createUserWithEmailAndPassword(user.email, user.password).await()
        val userId = responseUser.user?.uid?: ""

        val userCopy = user.copy(id = userId, password = "")


        db.collection("users")
            .document(userId)
            .set(userCopy)
            .await()
    }

    fun updateUserFavoriteList(userId: String, newFavoritesList: List<String>) {
        viewModelScope.launch {
            try {
                db.collection("users")
                    .document(userId)
                    .update("favorites", newFavoritesList)
                    .await()

                _currentUser.value = _currentUser.value?.copy(favorites = newFavoritesList)
            } catch (e: Exception) {
                println("Error actualizando favoritos: ${e.message}")
            }
        }
    }

    fun resetOperationResult() {
        _userResult.value = null
    }

    fun logout() {
        auth.signOut()
        _currentUser.value = null
    }

    fun findUserById(userId: String) {
        viewModelScope.launch {
            val doc = db.collection("users").document(userId).get().await()
            if (doc.exists()) {
                _currentUser.value = doc.toObject(User::class.java)?.apply { id = doc.id }
            }
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            _userResult.value = RequestResult.Loading

            try {
                db.collection("users")
                    .document(user.id)
                    .set(user)
                    .await()

                // Actualizar el usuario actual en memoria
                _currentUser.value = user

                _userResult.value = RequestResult.Success("Perfil actualizado correctamente")
            } catch (e: Exception) {
                _userResult.value = RequestResult.Failure(
                    e.message ?: "Error actualizando el usuario"
                )
            }
        }
    }

}
