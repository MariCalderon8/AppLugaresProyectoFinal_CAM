package eam.edu.co.applugaresproyectofinal.viewModel

import androidx.lifecycle.ViewModel
import eam.edu.co.applugaresproyectofinal.model.Category
import eam.edu.co.applugaresproyectofinal.model.Place
import eam.edu.co.applugaresproyectofinal.model.Schedule
import eam.edu.co.applugaresproyectofinal.model.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.DayOfWeek
import java.time.LocalTime
import java.util.UUID

class PlacesViewModel: ViewModel() {
    private val _places = MutableStateFlow(emptyList<Place>())
    val places: StateFlow<List<Place>> = _places.asStateFlow()

    init {
        loadPlaces()
    }

    private fun loadPlaces() {
        val schedule = Schedule(
            dayOfWeek = DayOfWeek.MONDAY,
            startTime = LocalTime.of(9, 0),   // 09:00 AM
            endTime = LocalTime.of(18, 0)     // 06:00 PM
        )

        _places.value = listOf(
            Place(
                id = UUID.randomUUID().toString(),
                images = listOf("https://dosg.net/wp-content/uploads/2018/03/cafeteria.jpg","https://dynamic-media-cdn.tripadvisor.com/media/photo-o/12/51/77/a2/intro-restaurant-plaza.jpg?w=900&h=500&s=1","https://dynamic-media-cdn.tripadvisor.com/media/photo-o/19/68/1b/4e/museo-nacional-de-columbia.jpg?w=900&h=500&s=1"),
                description = "Un café acogedor en el centro de la ciudad",
                name = "Café Central",
                phone = "123-456-789",
                createdById = "1",
                approvedById = null,
                status = Status.APPROVED,
                schedule = schedule,
                category = Category.CAFETERIA,
                reviews = emptyList(),
                reports = emptyList(),
                address = "Calle 123, Ciudad"
            ),
            Place(
                id = UUID.randomUUID().toString(),
                images = listOf("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/12/51/77/a2/intro-restaurant-plaza.jpg?w=900&h=500&s=1", "https://dosg.net/wp-content/uploads/2018/03/cafeteria.jpg","https://dynamic-media-cdn.tripadvisor.com/media/photo-o/19/68/1b/4e/museo-nacional-de-columbia.jpg?w=900&h=500&s=1"),
                description = "Un restaurante especializado en comida italiana",
                name = "Trattoria Roma",
                phone = "321-654-987",
                createdById = "1",
                approvedById = UUID.randomUUID().toString(),
                status = Status.PENDING_FOR_APPROVAL,
                schedule = schedule,
                category = Category.RESTAURANT,
                reviews = emptyList(),
                reports = emptyList(),
                address = "Avenida 456, Ciudad"
            ),
            Place(
                id = UUID.randomUUID().toString(),
                images = listOf("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/19/68/1b/4e/museo-nacional-de-columbia.jpg?w=900&h=500&s=1","https://dynamic-media-cdn.tripadvisor.com/media/photo-o/12/51/77/a2/intro-restaurant-plaza.jpg?w=900&h=500&s=1","https://dosg.net/wp-content/uploads/2018/03/cafeteria.jpg"),
                description = "Un museo recreacional ideal para familias",
                name = "Museo de la Alegría",
                phone = "111-222-333",
                createdById = "3",
                approvedById = null,
                status = Status.PENDING_FOR_APPROVAL,
                schedule = schedule,
                category = Category.MUSEUM,
                reviews = emptyList(),
                reports = emptyList(),
                address = "Calle 789, Ciudad"
            )
        )
    }

    // Crear lugar
    fun addPlace(place: Place) {
        _places.value = _places.value + place
    }

    // Editar lugar
    fun updatePlace(place: Place) {
        _places.value = _places.value.map { if (it.id == place.id) place else it }
    }

    // Eliminar lugar
    fun deletePlace(id: String) {
        _places.value = _places.value.filterNot { it.id == id }
    }

    // Buscar lugar por ID
    fun findPlaceById(id: String): Place? {
        return _places.value.find { it.id == id }
    }

    // Buscar lugares por estado (Approved, Pending, etc.)
    fun findPlacesByStatus(status: Status): List<Place> {
        return _places.value.filter { it.status == status }
    }

    // Buscar lugares por id de usuario
    fun findPlacesByUserId(userId: String): List<Place> {
        return _places.value.filter { it.createdById == userId }
    }

    // Buscar lugares por id de usuario
    fun findPlacesByUserModerator(userId: String): List<Place> {
        return _places.value.filter { it.approvedById == userId }
    }

}