package eam.edu.co.applugaresproyectofinal.viewModel

import androidx.lifecycle.ViewModel
import eam.edu.co.applugaresproyectofinal.model.Category
import eam.edu.co.applugaresproyectofinal.model.Location
import eam.edu.co.applugaresproyectofinal.model.Place
import eam.edu.co.applugaresproyectofinal.model.Schedule
import eam.edu.co.applugaresproyectofinal.model.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.UUID

class PlacesViewModel: ViewModel() {
    private val _places = MutableStateFlow(emptyList<Place>())
    val places: StateFlow<List<Place>> = _places.asStateFlow()

    init {
        loadPlaces()
    }

    private fun loadPlaces() {
        val schedule = listOf(
            Schedule(DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(17, 0)),
            Schedule(DayOfWeek.TUESDAY, LocalTime.of(9, 0), LocalTime.of(17, 0)),
            Schedule(DayOfWeek.WEDNESDAY, LocalTime.of(14, 0), LocalTime.of(20, 0))
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
                scheduleList = schedule + Schedule(DayOfWeek.SUNDAY, LocalTime.of(20, 0), LocalTime.of(23, 0)),
                category = Category.CAFETERIA,
                reviews = emptyList(),
                reports = emptyList(),
                address = "Calle 123, Ciudad",
                location = Location(1.2345, 6.7890),
                rating = 4.5
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
                scheduleList = schedule,
                category = Category.RESTAURANT,
                reviews = emptyList(),
                reports = emptyList(),
                address = "Avenida 456, Ciudad",
                location = Location(2.3456, 7.8901),
                rating = 3.8
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
                scheduleList = schedule,
                category = Category.MUSEUM,
                reviews = emptyList(),
                reports = emptyList(),
                address = "Calle 789, Ciudad",
                location = Location(3.4567, 8.9012),
                rating = 4.2
            ),
            Place(
                id = UUID.randomUUID().toString(),
                images = listOf("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/19/68/1b/4e/museo-nacional-de-columbia.jpg?w=900&h=500&s=1","https://dynamic-media-cdn.tripadvisor.com/media/photo-o/12/51/77/a2/intro-restaurant-plaza.jpg?w=900&h=500&s=1","https://dosg.net/wp-content/uploads/2018/03/cafeteria.jpg"),
                description = "Un museo recreacional ideal para familias",
                name = "Museo de la Alegría",
                phone = "111-222-333",
                createdById = "3",
                approvedById = null,
                status = Status.REJECTED,
                scheduleList = schedule,
                category = Category.FAST_FOOD,
                reviews = emptyList(),
                reports = emptyList(),
                address = "Calle 789, Ciudad",
                location = Location(3.4567, 8.9012),
                rating = 2.2
            ),
            Place(
                id = UUID.randomUUID().toString(),
                images = listOf("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/19/68/1b/4e/museo-nacional-de-columbia.jpg?w=900&h=500&s=1","https://dynamic-media-cdn.tripadvisor.com/media/photo-o/12/51/77/a2/intro-restaurant-plaza.jpg?w=900&h=500&s=1","https://dosg.net/wp-content/uploads/2018/03/cafeteria.jpg"),
                description = "Un museo recreacional ideal para familias",
                name = "Museo de la Alegría",
                phone = "111-222-333",
                createdById = "3",
                approvedById = null,
                status = Status.REPORTED,
                scheduleList = schedule,
                category = Category.HOTEL,
                reviews = emptyList(),
                reports = emptyList(),
                address = "Calle 789, Ciudad",
                location = Location(3.4567, 8.9012),
                rating = 1.2
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

    // filtrar por estados y query
    fun filterPlaces(
        status: Status?,
        query: String
    ): List<Place> {
        return _places.value.filter { place ->
            (status == null || place.status == status) &&
                    (query.isBlank() || place.name.contains(query, ignoreCase = true))
        }
    }

    fun filterPlacesCategoryQuery(
        category: Category?,
        query: String
    ): List<Place> {
        return _places.value.filter { place ->
            (category == null || place.category == category) &&
                    (query.isBlank() || place.name.contains(query, ignoreCase = true))
        }
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

    fun getPlacesCreatedByUser(userId: String): List<Place> {
        return _places.value.filter { it.createdById == userId }
    }

    fun isPlaceOpen(schedules: List<Schedule>): Boolean {
        val now = LocalDateTime.now()
        val currentDay = now.dayOfWeek
        val currentTime = now.toLocalTime()

        // Busca los horarios del día actual
        val todaySchedules = schedules.filter { it.dayOfWeek == currentDay }

        // Verifica si la hora actual está dentro de alguno de los intervalos
        return todaySchedules.any { schedule ->
            currentTime.isAfter(schedule.startTime) && currentTime.isBefore(schedule.endTime)
        }
    }
}