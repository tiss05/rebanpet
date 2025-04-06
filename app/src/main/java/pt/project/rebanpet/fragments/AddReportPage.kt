package pt.project.rebanpet.fragments

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.colorResource
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import pt.project.rebanpet.R
import pt.project.rebanpet.report.Report
import java.io.IOException
import java.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.core.content.ContextCompat
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import coil.compose.AsyncImage


@Composable
fun AddReportPage() {
    var descriptionAnimal by remember {
        mutableStateOf("")
    }

    var locationAnimal by remember {
        mutableStateOf("")
    }

    var photoAnimal by remember {
        mutableStateOf<Uri?>(null)
    }

    val context = LocalContext.current

    // Launcher for picking an image from the gallery
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        photoAnimal = uri
    }

    fun clearInputs(){
        descriptionAnimal = ""
        locationAnimal = ""
        photoAnimal = null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

            //Section "Description"
            Text(
                text = "Descrição do animal",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp
                )
            )
            TextField(
                value = descriptionAnimal,
                onValueChange = { descriptionAnimal = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                placeholder = { Text("Escreva uma descrição do animal") },
                textStyle = TextStyle(fontSize = 15.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            //Section "Location"
            Text(
                text = "Localização",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                GetLocationButton(onLocationUpdated = { location ->
                    locationAnimal = location
                })
                Text(
                    text = "$locationAnimal",
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 10.dp, bottom = 10.dp, end = 8.dp)
                        .border(1.dp, Color.LightGray, shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
                        .padding(8.dp),
                    style = TextStyle(fontSize = 15.sp)
                )
            }

            //Section "Photo"
            Text(
                text = "Foto do animal",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                //IconButton(onClick = { galleryLauncher.launch("image/*") }) {
                    /*Image(
                        painter = painterResource(id = R.mipmap.ic_photo_camera),
                        contentDescription = "Take Photo",
                        modifier = Modifier.size(24.dp)
                    )
                }*/
                GalleryAccessButton(onImageSelected = { uri ->
                    photoAnimal = uri
                    // Do something with the selected image URI, e.g., upload it
                    if (uri != null) {
                        Toast.makeText(context, "Imagem selecionada: $uri", Toast.LENGTH_SHORT).show()
                    }
                })

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (photoAnimal != null) {
                        Text("Imagem Selecionada:")
                        AsyncImage(
                            model = photoAnimal,
                            contentDescription = "Selected Image",
                            modifier = Modifier
                                .size(200.dp)
                                .border(BorderStroke(1.dp,Color.Black))
                                .background(Color.White)
                                .padding(2.dp),
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Crop,

                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.default_photo_report),
                            contentDescription = "Animal Photo",
                            modifier = Modifier.size(200.dp),
                            alignment = Alignment.CenterStart,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            // Section "Button to add report"
            Button(
                onClick = {
                    saveDataToFirebase(context, descriptionAnimal, locationAnimal, photoAnimal)
                    clearInputs()
                    Toast.makeText(context, "Denúncia concluída com sucesso!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Denunciar",
                    style = TextStyle(fontSize = 15.sp)
                )
            }
        }

}

fun saveDataToFirebase(context: Context, description: String, location: String, imageUri: Uri?) {
    if (description.isBlank() || location.isBlank() || imageUri == null) {
        if (description.isBlank()) {
            Toast.makeText(context, "Falta a descrição do animal", Toast.LENGTH_SHORT).show()
        }
        else if (location.isBlank()) {
            Toast.makeText(context, "Falta a localização do animal", Toast.LENGTH_SHORT).show()
        }
        else if (imageUri == null) {
            Toast.makeText(context, "Falta a fotografia do animal", Toast.LENGTH_SHORT).show()
        }
        return
    }

    val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm")
    val db = FirebaseDatabase.getInstance()
    val storage = FirebaseStorage.getInstance()
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    if (currentUser == null) {
        Log.e("Firebase", "User not authenticated")
        return
    }

    val userId = currentUser.uid
    val firebaseRef = db.getReference("reports/$userId")
    val storageRef = storage.reference.child("images/$userId")
    val currentDateTime = sdf.format(Date())
    val reportId = firebaseRef.push().key!!


    val imageRef = storageRef.child("reports/$reportId")
    val uploadTask = imageRef.putFile(imageUri)

    uploadTask.addOnSuccessListener { taskSnapshot ->
        taskSnapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener { imageUrl ->
            val reportDetails = Report(
                reportId,
                description,
                location,
                currentDateTime,
                imageUrl.toString()
            )

            firebaseRef.child(reportId).setValue(reportDetails)
                .addOnSuccessListener {
                    Log.d("ReportData", "Report ID: $reportId")
                    Log.d("ReportData", "Descrição: $description")
                    Log.d("ReportData", "Localização: $location")
                    Log.d("ReportData", "Data: $currentDateTime")
                    Log.d("ReportData", "Image URL: $imageUrl")
                }
                .addOnFailureListener { e ->
                    Log.e("FirebaseDatabase", "Erro!")
                }

        }?.addOnFailureListener { e ->
            Log.w("Firebase", "Erro nos dados da denúncia.")
        }
    }.addOnFailureListener { e ->
        Log.w("Firebase", "Erro da imagem da denúncia.")
    }
}

@Composable
fun GetLocationButton(onLocationUpdated: (String) -> Unit) {
    val context = LocalContext.current
    val fusedLocationClient: FusedLocationProviderClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    val locationPermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                getLocation(context, fusedLocationClient, onLocationUpdated)
            } else {
                Toast.makeText(context, "Permissão de localização negada", Toast.LENGTH_SHORT).show()
            }
        }
    )

    IconButton(onClick = {
        locationPermissionResultLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_mylocation),
            contentDescription = "Get Location",
            modifier = Modifier.size(24.dp)
        )
    }
}

@SuppressLint("MissingPermission")
private fun getLocation(
    context: Context,
    fusedLocationClient: FusedLocationProviderClient,
    onLocationUpdated: (String) -> Unit
) {
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        Toast.makeText(context, "Permissão de localização não concedida", Toast.LENGTH_SHORT).show()
        return
    }

    val locationTask: Task<Location> = fusedLocationClient.getCurrentLocation(
        Priority.PRIORITY_HIGH_ACCURACY,
        null
    )
    locationTask.addOnSuccessListener { location ->
        if (location != null) {
            getAddressFromLocation(context, location, onLocationUpdated)
        } else {
            onLocationUpdated("Tente novamente! Verifique se a localização está ativada.")
            startActivity(context, Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), null)
        }
    }.addOnFailureListener {
        Toast.makeText(context, "Falha na localização", Toast.LENGTH_SHORT).show()
    }
}

private fun getAddressFromLocation(
    context: Context,
    location: Location,
    onLocationUpdated: (String) -> Unit
) {
    val geocoder = Geocoder(context, Locale.getDefault())
    try {
        val addresses: List<Address>? =
            geocoder.getFromLocation(location.latitude, location.longitude, 1)
        if (addresses != null && addresses.isNotEmpty()) {
            val address = addresses[0]
            onLocationUpdated(address.getAddressLine(0))
        } else {
            onLocationUpdated("Localização não encontrada")
        }
    } catch (e: IOException) {
        e.printStackTrace()
        onLocationUpdated("Erro na procura de localização")
    }
}

@Composable
fun GalleryAccessButton(onImageSelected: (Uri?) -> Unit) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val permissionGranted = remember { mutableStateOf(false) }
    val permissionToRequest = remember {
        mutableStateOf(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Manifest.permission.READ_MEDIA_IMAGES
            } else {
                Manifest.permission.READ_EXTERNAL_STORAGE
            }
        )
    }

    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageUri = uri
        onImageSelected(uri) // Callback to send the selected URI back
        if (uri == null) {
            Toast.makeText(context, "Nenhuma imagem selecionada", Toast.LENGTH_SHORT).show()
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        permissionGranted.value = isGranted
        if (isGranted) {
            galleryLauncher.launch("image/*")
        } else {
            Toast.makeText(context, "Permissão negada", Toast.LENGTH_SHORT).show()
        }
    }


    IconButton(onClick = {
        val permissionCheckResult = ContextCompat.checkSelfPermission(context, permissionToRequest.value)
        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
            permissionGranted.value = true
            galleryLauncher.launch("image/*")
        } else {
            permissionLauncher.launch(permissionToRequest.value)
        }
    }) {
        Image(
            painter = painterResource(id = R.mipmap.ic_photo_camera),
            contentDescription = "Get Photo",
            modifier = Modifier.size(24.dp)
        )
    }

    /*
    if (imageUri != null) {
        AsyncImage(model = imageUri, contentDescription = "Selected Image")
    }
    */
}