package eam.edu.co.applugaresproyectofinal.ui.screens.user.tabs

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.util.Patterns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddAPhoto
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.mapbox.geojson.Point
import eam.edu.co.applugaresproyectofinal.R
import eam.edu.co.applugaresproyectofinal.model.Category
import eam.edu.co.applugaresproyectofinal.model.Location
import eam.edu.co.applugaresproyectofinal.model.Place
import eam.edu.co.applugaresproyectofinal.model.Schedule
import eam.edu.co.applugaresproyectofinal.ui.components.CustomButton
import eam.edu.co.applugaresproyectofinal.ui.components.InputText
import eam.edu.co.applugaresproyectofinal.ui.components.Label
import eam.edu.co.applugaresproyectofinal.ui.components.Map
import eam.edu.co.applugaresproyectofinal.ui.components.ScheduleDialog
import eam.edu.co.applugaresproyectofinal.ui.components.ScheduleItemCard
import eam.edu.co.applugaresproyectofinal.ui.screens.LocalMainViewModel
import eam.edu.co.applugaresproyectofinal.utils.SharedPrefsUtil
import java.util.UUID
import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import eam.edu.co.applugaresproyectofinal.ui.components.CustomSnackbar
import eam.edu.co.applugaresproyectofinal.ui.components.MessageType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePlaceScreen(
    onNavigateToMyPlaces: () -> Unit,
    onBack: () -> Unit = {}
) {
    var placeName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    var selectedCategory by remember { mutableStateOf<Category?>(null) }

    val placeViewModel = LocalMainViewModel.current.placesViewModel
    val places by placeViewModel.places.collectAsState()


    var showDialogSchedule by remember { mutableStateOf(false) }
    var schedules = remember { mutableStateListOf<Schedule>() }
    val validators = remember { mutableListOf<() -> Boolean>() }
    val context = LocalContext.current

    var categoryError by remember { mutableStateOf(false) }
    var scheduleError by remember { mutableStateOf(false) }

    val usersViewModel = LocalMainViewModel.current.usersViewModel
    val userId = SharedPrefsUtil.getPreferences(context)["userId"] ?: return
    usersViewModel.findUserById(userId)
    val currentUser by usersViewModel.currentUser.collectAsState()


    var clickedPoint by rememberSaveable { mutableStateOf<Point?>(null) }

    var image by remember { mutableStateOf("")}

    var message by remember { mutableStateOf<Pair<String, MessageType>?>(null) }
    var showMessage by remember { mutableStateOf(false) }


    val config = mapOf(
        "cloud_name" to "dagstejp0",
        "api_key" to "469129323531173",
        "api_secret" to "YFnzJX0A6kjLosI8FAIRlEeJuyM"
    )

    val scope = rememberCoroutineScope()
    val cloudinary = Cloudinary(config)


    val fileLaucher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){  uri: Uri? ->
        uri?.let{
            scope.launch (Dispatchers.IO){
                val inputStream = context.contentResolver.openInputStream(it)
                inputStream?.use{ stream ->
                    val result = cloudinary.uploader().upload(stream, ObjectUtils.emptyMap())
                    val imageUrl = result["secure_url"].toString()
                    image = imageUrl

                }
            }
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ){
        if(it){
            Toast.makeText(context, "Permiso concedido", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Permiso denegado", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp)
                .verticalScroll(rememberScrollState()),
        ) {

            InputText(
                value = placeName,
                isRequired = true,
                label = stringResource(R.string.label_place_name),
                supportingText = stringResource(R.string.error_name),
                onValueChange = {
                    placeName = it
                },
                onValidate = {
                    placeName.isBlank()
                },
                placeholder = stringResource(R.string.name_placeholder),
                icon = Icons.Outlined.Store,
                registerValidator = { validator -> validators.add(validator) }
            )

            Label(
                text = stringResource(R.string.label_place_category),
                isRequired = true,
            )

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Category.values().forEach { category ->
                    val isSelected = selectedCategory == category

                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            selectedCategory = category
                            if (categoryError && selectedCategory != null) {
                                categoryError = false
                            }
                                  },
                        label = {
                            Text(
                                category.displayName,
                                color = if (isSelected) Color.White else Color.Black
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = category.icon,
                                contentDescription = category.displayName,
                                modifier = Modifier.size(20.dp),
                                tint = if (isSelected) Color.White else Color.Black
                            )
                        },
                        shape = RoundedCornerShape(50),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFF6A1B9A),
                            containerColor = Color.White,
                            selectedLabelColor = Color.White,
                            selectedLeadingIconColor = Color.White,
                            labelColor = Color.Black,
                            iconColor = Color.Black
                        )
                    )
                }
            }

            if (categoryError) {
                Text(
                    text = stringResource(R.string.error_select_category),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 12.dp, top = 2.dp)
                )
            }

            InputText(
                value = description,
                isRequired = true,
                label = stringResource(R.string.label_place_description),
                supportingText = stringResource(R.string.error_description),
                onValueChange = {
                    description = it
                },
                onValidate = {
                    description.isBlank()
                },
                singleLine = false,
                placeholder = stringResource(R.string.description_placeholder),
                height = 150,
                registerValidator = { validator -> validators.add(validator) }
            )

            InputText(
                value = address,
                isRequired = true,
                label = stringResource(R.string.label_place_address),
                supportingText = stringResource(R.string.error_place_address),
                onValueChange = {
                    address = it
                },
                onValidate = {
                    address.isBlank()
                },
                placeholder = stringResource(R.string.address_placeholder),
                icon = Icons.Default.LocationOn,
                registerValidator = { validator -> validators.add(validator) }
            )

            InputText(
                value = phoneNumber,
                isRequired = true,
                label = stringResource(R.string.label_phonenumber),
                supportingText = stringResource(R.string.error_phonenumber),
                onValueChange = {
                    phoneNumber = it
                },
                onValidate = {
                    phoneNumber.isBlank() || !Patterns.PHONE.matcher(phoneNumber).matches()
                },
                placeholder = stringResource(R.string.phone_placeholder),
                icon = Icons.Outlined.Phone,
                modifier = Modifier,
                registerValidator = { validator -> validators.add(validator) }
            )


            Label(stringResource(R.string.label_place_schedule), isRequired = true)

            schedules.forEach {
                ScheduleItemCard(
                    it,
                    onRemove = {
                        schedules.remove(it)
                        if (schedules.isEmpty()) scheduleError = true
                    }
                )
            }

            if (scheduleError) {
                Text(
                    text = stringResource(R.string.error_schedule_required),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(start = 4.dp, top = 2.dp)
                )
            }


            FloatingActionButton(
                onClick = { showDialogSchedule = true },
                shape = CircleShape,
                containerColor = Color(0xFF6A1B9A),
                modifier = Modifier
                    .padding(top = 12.dp)
                    .size(48.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Icon(Icons.Outlined.Add, contentDescription = "Agregar horario", tint = Color.White)
            }

            if (showDialogSchedule) {
                ScheduleDialog(
                    addSchedule = { schedule ->
                        schedules.add(schedule)
                        scheduleError = false
                    },
                    onDimiss = { showDialogSchedule = false }
                )
            }


            Label(
                text = stringResource(R.string.label_place_images),
                isRequired = true
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                       val permissionCheckResult = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                            ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_MEDIA_IMAGES)
                       }else {
                           ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                       }
                        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                            fileLaucher.launch("image/*")
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
                            } else {
                                permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                            }
                        }
                    },
                    modifier = Modifier
                        .size(75.dp)
                        .padding(4.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.light_gray),
                        contentColor = Color.Black
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .drawBehind {
                                val stroke = Stroke(
                                    width = 3f,
                                    pathEffect = PathEffect.dashPathEffect(
                                        floatArrayOf(10f, 10f),
                                        0f
                                    )
                                )
                                drawRoundRect(
                                    color = Color.Gray,
                                    size = size,
                                    style = stroke,
                                    cornerRadius = CornerRadius(20f, 20f)
                                )
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AddAPhoto,
                            contentDescription = stringResource(R.string.add_images),
                        )
                    }
                }

//                Spacer(modifier = Modifier.width(12.dp))
                if (image.isNotBlank()) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .padding(4.dp)
                    ) {
                        // Caja de la imagen
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .border(
                                    width = 1.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .background(
                                    color = colorResource(id = R.color.light_gray),
                                    shape = RoundedCornerShape(8.dp),
                                ),

                            contentAlignment = Alignment.Center
                        ) {
                            if (image.isNotBlank()) {
                                AsyncImage(
                                    model = image,
                                    contentDescription = stringResource(R.string.image_text),
                                    modifier = Modifier
                                        .fillMaxSize()              // ✔ OCUPA TODO
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop   // ✔ YA NO SE VE GRIS
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Outlined.Image,
                                    contentDescription = stringResource(R.string.image_text),
                                )
                            }
                        }
                        if (image.isNotBlank()) {
                            IconButton(
                                onClick = { image = "" },
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.TopEnd)
                                    .padding(4.dp, 4.dp, 2.dp, 2.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Cancel,
                                    contentDescription = stringResource(R.string.delete_image_txt),
                                )
                            }

                        }
                    }
                }
            }

            Label(
                text = stringResource(R.string.label_place_location),
                isRequired = true,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(12.dp))
            ){
                Map(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    activateClick = true,
                    onMapClickListener = { l ->
                        clickedPoint = l
                    }

                )
            }
            
            Spacer(modifier = Modifier.height(10.dp))

            CustomButton(
                text = stringResource(R.string.btn_create_place),
                onClick = {
                    var hasErrors = false
                    validators.forEach { validator ->
                        if (validator()) hasErrors = true
                    }

                    categoryError = selectedCategory == null
                    scheduleError = schedules.isEmpty()
                    if (categoryError || scheduleError) hasErrors = true

                    if (hasErrors) {
                        message = context.getString(R.string.msg_place_missing_fields) to MessageType.ERROR
                        showMessage = true
                        return@CustomButton
                    }

                    if(clickedPoint != null) {
                        val place = Place(
                            id = UUID.randomUUID().toString(),
                            images = if (image.isNotBlank()) listOf(image) else emptyList(),
                            description = description,
                            name = placeName,
                            scheduleList = schedules,
                            phone = phoneNumber,
                            category = selectedCategory!!,
                            reviews = emptyList(),
                            createdById = currentUser?.id ?: "",
                            handledBy = null,
                            status = eam.edu.co.applugaresproyectofinal.model.Status.PENDING_FOR_APPROVAL,
                            reports = emptyList(),
                            address = address,
                            location = Location(latitude = clickedPoint!!.latitude(), longitude = clickedPoint!!.longitude())
                        )

                        placeViewModel.addPlace(place)

                        message = context.getString(R.string.msg_place_created) to MessageType.SUCCESS
                        showMessage = true

                        onNavigateToMyPlaces()
                    }else{
                        message = context.getString(R.string.msg_place_missing_location) to MessageType.ERROR
                        showMessage = true
                    }
                },
                isLarge = true
            )
            message?.let { (text, type) ->
                CustomSnackbar(
                    message = text,
                    type = type,
                    visible = showMessage,
                    onDismiss = {
                        showMessage = false
                        message = null
                    }
                )
            }
        }
    }
}
