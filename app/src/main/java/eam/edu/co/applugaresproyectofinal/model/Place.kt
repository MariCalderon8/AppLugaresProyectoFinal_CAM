package eam.edu.co.applugaresproyectofinal.model

data class Place(
    val id: String,
    var images: List<String>,
    var description: String,
    var name: String,
    var scheduleList: List<Schedule>,
    var phone: String,
    var category: Category,
    var reviews: List<Review> = emptyList(),
    var createdById: String,
    var handledBy: String?,
    var status: Status,
    var reports: List<Report> = emptyList(),
    var address: String,
    var location: Location,
)