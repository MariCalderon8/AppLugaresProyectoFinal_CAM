package eam.edu.co.applugaresproyectofinal.viewModel

import androidx.lifecycle.ViewModel
import eam.edu.co.applugaresproyectofinal.model.Category
import eam.edu.co.applugaresproyectofinal.model.Location
import eam.edu.co.applugaresproyectofinal.model.Place
import eam.edu.co.applugaresproyectofinal.model.Report
import eam.edu.co.applugaresproyectofinal.model.Review
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
                handledBy = null,
                status = Status.APPROVED,
                scheduleList = schedule + Schedule(DayOfWeek.SUNDAY, LocalTime.of(20, 0), LocalTime.of(23, 0)),
                category = Category.CAFETERIA,
                address = "Calle 123, Ciudad",
                location = Location(4.533169, -75.673897),
                reviews = listOf(
                    Review(
                        id = UUID.randomUUID().toString(),
                        subject = "Excelente atención",
                        description = "El personal fue muy amable y el lugar estaba limpio.",
                        rating = 5.5,
                        userId = "3"
                    ),
                    Review(
                        id = UUID.randomUUID().toString(),
                        subject = "Buena comida",
                        description = "La comida estaba deliciosa, pero el servicio fue un poco lento.",
                        rating = 4.0,
                        userId = "3"
                    ),
                    Review(
                        id = UUID.randomUUID().toString(),
                        subject = "No fue lo que esperaba",
                        description = "El lugar se veía diferente a las fotos y el ambiente no fue agradable.",
                        rating = 2.5,
                        userId = "3"
                    )
                ),
                creationDate = LocalDateTime.of(2024,5, 5,12,30)
            ),
            Place(
                id = UUID.randomUUID().toString(),
                images = listOf("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/12/51/77/a2/intro-restaurant-plaza.jpg?w=900&h=500&s=1", "https://dosg.net/wp-content/uploads/2018/03/cafeteria.jpg","https://dynamic-media-cdn.tripadvisor.com/media/photo-o/19/68/1b/4e/museo-nacional-de-columbia.jpg?w=900&h=500&s=1"),
                description = "Un restaurante especializado en comida italiana",
                name = "Trattoria Roma",
                phone = "321-654-987",
                createdById = "1",
                handledBy = UUID.randomUUID().toString(),
                status = Status.PENDING_FOR_APPROVAL,
                scheduleList = schedule,
                category = Category.RESTAURANT,
                reviews = emptyList(),
                reports = emptyList(),
                address = "Avenida 456, Ciudad",
                location = Location(4.543210, -75.663421),
            ),
            Place(
                id = UUID.randomUUID().toString(),
                images = listOf("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/19/68/1b/4e/museo-nacional-de-columbia.jpg?w=900&h=500&s=1","https://dynamic-media-cdn.tripadvisor.com/media/photo-o/12/51/77/a2/intro-restaurant-plaza.jpg?w=900&h=500&s=1","https://dosg.net/wp-content/uploads/2018/03/cafeteria.jpg"),
                description = "Un museo recreacional ideal para familias",
                name = "Museo de la Alegría",
                phone = "111-222-333",
                createdById = "3",
                handledBy = null,
                status = Status.PENDING_FOR_APPROVAL,
                scheduleList = schedule,
                category = Category.MUSEUM,
                reviews = emptyList(),
                reports = emptyList(),
                address = "Calle 789, Ciudad",
                location = Location(4.541685, -75.675312),
            ),
            Place(
                id = UUID.randomUUID().toString(),
                images = listOf("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/19/68/1b/4e/museo-nacional-de-columbia.jpg?w=900&h=500&s=1","https://dynamic-media-cdn.tripadvisor.com/media/photo-o/12/51/77/a2/intro-restaurant-plaza.jpg?w=900&h=500&s=1","https://dosg.net/wp-content/uploads/2018/03/cafeteria.jpg"),
                description = "Un museo recreacional ideal para familias",
                name = "Museo de la Alegría",
                phone = "111-222-333",
                createdById = "3",
                handledBy = null,
                status = Status.REJECTED,
                scheduleList = schedule,
                category = Category.FAST_FOOD,
                reviews = emptyList(),
                reports = emptyList(),
                address = "Calle 789, Ciudad",
                location = Location(4.554901, -75.655042),
            ),
            Place(
                id = UUID.randomUUID().toString(),
                images = listOf("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/19/68/1b/4e/museo-nacional-de-columbia.jpg?w=900&h=500&s=1","https://dynamic-media-cdn.tripadvisor.com/media/photo-o/12/51/77/a2/intro-restaurant-plaza.jpg?w=900&h=500&s=1","https://dosg.net/wp-content/uploads/2018/03/cafeteria.jpg"),
                description = "Un museo recreacional ideal para familias",
                name = "Museo de la Alegría",
                phone = "111-222-333",
                createdById = "3",
                handledBy = null,
                status = Status.REPORTED,
                scheduleList = schedule,
                category = Category.HOTEL,
                reviews = emptyList(),
                reports = listOf(
                    Report(
                        id = UUID.randomUUID().toString(),
                        subject = "Información incorrecta",
                        description = "El número de teléfono publicado no corresponde al negocio.",
                        userId = "1"
                    ),
                    Report(
                        id = UUID.randomUUID().toString(),
                        subject = "Contenido inapropiado",
                        description = "Las imágenes del lugar no son apropiadas para todo público.",
                        userId = "2"
                    ),
                    Report(
                        id = UUID.randomUUID().toString(),
                        subject = "Ubicación errónea",
                        description = "El mapa muestra el lugar en una dirección diferente a la real.",
                        userId = "2"
                    )
                ),
                address = "Calle 789, Ciudad",
                location = Location(4.540726, -75.667563),
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

    // ADMIN - Muestra todos los lugares
    fun filterPlaces(
        status: Status?,
        query: String
    ): List<Place> {
        return _places.value.filter { place ->
            (status == null || place.status == status) &&
                    (query.isBlank() || place.name.contains(query, ignoreCase = true))
        }
    }

    // USER - No muestra los rechazados ni pendientes por aprobar
    fun filterPlacesUserCategoryQuery(
        category: Category?,
        query: String,
        placesList: List<Place> = _places.value,
        ): List<Place> {
        return placesList.filter { place ->
            ((place.status != Status.REJECTED && place.status != Status.PENDING_FOR_APPROVAL) && ((category == null || place.category == category)  &&
                    (query.isBlank() || place.name.contains(query, ignoreCase = true))))
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
        return _places.value.filter { it.handledBy == userId }
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

    fun addReview(placeId: String, review: Review){
        val place = findPlaceById(placeId)
        if (place != null) {
            place.reviews = place.reviews + review
            return
        }
        throw IllegalArgumentException("Lugar no encontrado")
    }

    fun addReport(placeId: String, report: Report){
        val place = findPlaceById(placeId)
        if (place != null) {
            place.reports = place.reports + report
            if (place.status != Status.REPORTED) {
                place.status = Status.REPORTED
            }
            return
        }
        throw IllegalArgumentException("Lugar no encontrado")
    }

    fun getPlaceRating(placeId: String): Double {
        val place = findPlaceById(placeId)
        if (place != null) {
            val reviews = place.reviews
            if (reviews.isEmpty()) return 0.0
            val total = reviews.sumOf { it.rating }
            return (total / reviews.size)
        }
        return 0.0
    }

    fun moderatePlace(placeId: String, moderatorId: String, newStatus: Status) {
        val updatedPlaces = _places.value.map { place ->
            if (place.id == placeId) place.copy(status = newStatus, handledBy = moderatorId) else place
        }
        _places.value = updatedPlaces
    }

    fun updatePlaceStatus(placeId: String, newStatus: Status) {
        val updatedPlaces = _places.value.map { place ->
            if (place.id == placeId) place.copy(status = newStatus) else place
        }
        _places.value = updatedPlaces
    }

    fun countReports(placeId: String): Int {
        val place = findPlaceById(placeId)
        if (place != null) {
            return place.reports.map { it.userId }.distinct().size
        }
        return 0
    }

    fun getUserFavoritePlaces(placesId: List<String>):List<Place>{
        return _places.value.filter { place -> place.id in placesId }
    }

    fun countCommentsPlace(userId: String):Int{
        var count = 0
        val userPlaces =  _places.value.filter { it.createdById == userId }
        for (place in userPlaces) {
            count += place.reviews.size
        }
        return count
    }

    fun countPlacesCreatedByUser(userId: String):Int {
        var count = 0
        val userPlaces =  _places.value.filter { it.createdById == userId }
        for (place in userPlaces) {
            count++
        }
        return count
    }
}