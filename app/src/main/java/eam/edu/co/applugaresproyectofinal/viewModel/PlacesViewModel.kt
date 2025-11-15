package eam.edu.co.applugaresproyectofinal.viewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import eam.edu.co.applugaresproyectofinal.model.Category
import eam.edu.co.applugaresproyectofinal.model.Place
import eam.edu.co.applugaresproyectofinal.model.Report
import eam.edu.co.applugaresproyectofinal.model.Review
import eam.edu.co.applugaresproyectofinal.model.Schedule
import eam.edu.co.applugaresproyectofinal.model.Status
import eam.edu.co.applugaresproyectofinal.model.dto.toDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import eam.edu.co.applugaresproyectofinal.model.dto.PlaceDTO
import eam.edu.co.applugaresproyectofinal.model.dto.toPlace


class PlacesViewModel: ViewModel() {

    val db = Firebase.firestore
    private val _places = MutableStateFlow(emptyList<Place>())
    val places: StateFlow<List<Place>> = _places.asStateFlow()

    init {
        loadPlacesFromFirestore()
    }

    private fun loadPlacesFromFirestore() {
        db.collection("places")
            .addSnapshotListener { snapshot, error ->
                if (error != null || snapshot == null) return@addSnapshotListener

                val dtoList = snapshot.toObjects(PlaceDTO::class.java)
                _places.value = dtoList.map { it.toPlace() }
            }
    }


    // Crear lugar
    fun addPlace(place: Place) {
        val dto = place.toDTO()
        db.collection("places")
            .document(place.id)
            .set(dto)
    }

    // Editar lugar
    fun updatePlace(place: Place) {
        val dto = place.toDTO()
        db.collection("places")
            .document(place.id)
            .set(dto)
    }


    // Eliminar lugar
    fun deletePlace(id: String) {
        db.collection("places")
            .document(id)
            .delete()
    }

    // Buscar lugar por ID
    fun findPlaceById(id: String): Place? {
        return _places.value.find { it.id == id }
    }

    // ADMIN - Muestra todos los lugares
    fun filterPlaces(status: Status?, query: String): List<Place> {
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
            place.status !in listOf(Status.REJECTED, Status.PENDING_FOR_APPROVAL) &&
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
        return _places.value.filter { it.handledBy == userId }
    }

    fun getPlacesCreatedByUser(userId: String): List<Place> {
        return _places.value.filter { it.createdById == userId }
    }

    fun isPlaceOpen(schedules: List<Schedule>): Boolean {
        val now = java.time.LocalDateTime.now()
        val currentDay = now.dayOfWeek
        val currentTime = now.toLocalTime()

        val todaySchedules = schedules.filter { it.dayOfWeek == currentDay }

        return todaySchedules.any { s ->
            currentTime.isAfter(s.startTime) && currentTime.isBefore(s.endTime)
        }
    }


    fun addReview(placeId: String, review: Review) {
        val place = findPlaceById(placeId) ?: return

        val updatedReviews = place.reviews + review
        val updatedPlace = place.copy(reviews = updatedReviews)

        updatePlace(updatedPlace)
    }

    fun addReport(placeId: String, report: Report) {
        val place = findPlaceById(placeId) ?: return

        val newStatus =
            if (place.status != Status.REPORTED) Status.REPORTED else place.status

        val updatedPlace = place.copy(
            reports = place.reports + report,
            status = newStatus
        )

        updatePlace(updatedPlace)
    }

    fun getPlaceRating(placeId: String): Double {
        val place = findPlaceById(placeId) ?: return 0.0
        val reviews = place.reviews
        if (reviews.isEmpty()) return 0.0
        return reviews.sumOf { it.rating } / reviews.size
    }

    fun moderatePlace(placeId: String, moderatorId: String, newStatus: Status) {
        val place = findPlaceById(placeId) ?: return
        val updatedPlace = place.copy(status = newStatus, handledBy = moderatorId)
        updatePlace(updatedPlace)
    }

    fun updatePlaceStatus(placeId: String, newStatus: Status) {
        val place = findPlaceById(placeId) ?: return
        val updatedPlace = place.copy(status = newStatus)
        updatePlace(updatedPlace)
    }

    fun countReports(placeId: String): Int {
        return findPlaceById(placeId)?.reports
            ?.map { it.userId }
            ?.distinct()
            ?.size ?: 0
    }

    fun getUserFavoritePlaces(placesId: List<String>): List<Place> {
        return _places.value.filter { it.id in placesId }
    }

    fun countCommentsPlace(userId: String): Int {
        return _places.value
            .filter { it.createdById == userId }
            .sumOf { it.reviews.size }
    }

    fun countPlacesCreatedByUser(userId: String): Int {
        return _places.value.count { it.createdById == userId }
    }

    fun addCreatorReply(placeId: String, reviewId: String, replyText: String) {
        val place = findPlaceById(placeId) ?: return

        val updatedReviews = place.reviews.map { r ->
            if (r.id == reviewId) r.copy(creatorReply = replyText)
            else r
        }

        val updatedPlace = place.copy(reviews = updatedReviews)

        updatePlace(updatedPlace)
    }

}