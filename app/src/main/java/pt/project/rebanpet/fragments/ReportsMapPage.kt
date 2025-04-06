package pt.project.rebanpet.fragments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.fillMaxSize
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.google.maps.android.compose.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import pt.project.rebanpet.report.Report
import android.content.Context
import android.location.Geocoder
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.google.firebase.database.FirebaseDatabase
import com.google.maps.android.compose.*
import com.google.firebase.database.ktx.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale
import androidx.compose.ui.unit.dp
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import pt.project.rebanpet.R
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor


@Composable
fun ReportsMapScreen(innerPadding : PaddingValues) {

    val defaultLocation = LatLng(39.3999, -8.2245)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 7f)
    }
    val reports = remember { mutableStateListOf<Report>() }
    //val customMarkerIcon = BitmapIBitmapDescriptorFactoryDescriptorFactory.fromResource(R.drawable.marker_pet)
    val context = LocalContext.current


    val reportsRef = FirebaseDatabase.getInstance().getReference("reports")

    LaunchedEffect(Unit) {
        reportsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tempList = mutableListOf<Report>()
                for (userSnapshot in snapshot.children) {
                    // Iterate over each userID
                    for (reportSnapshot in userSnapshot.children) {
                        // Iterate over each reportID
                        val report = reportSnapshot.getValue(Report::class.java)
                        if (report != null) {
                            tempList.add(report)
                            println("Fetched report: ${report.reportLocal} - ${report.reportDescription}")
                        } else {
                            println("Skipping invalid report: ${reportSnapshot.value}")
                        }
                    }
                }
                reports.clear()
                reports.addAll(tempList)
                println("Total reports fetched: ${reports.size}")
            }

            override fun onCancelled(error: DatabaseError) {
                println("Firebase read failed: ${error.message}")
            }
        })
    }


    fun getLatLngFromAddress(context: Context, address: String): LatLng? {
        if (address.isEmpty()) {
            println("Geocoding skipped: Address is empty")
            return null
        }

        val geocoder = Geocoder(context, Locale.getDefault())
        return try {
            val addresses = geocoder.getFromLocationName(address, 1)
            if (addresses?.isNotEmpty() == true) {
                val location = addresses[0]
                println("Geocoding successful: $address -> (${location.latitude}, ${location.longitude})")
                LatLng(location.latitude, location.longitude)
            } else {
                println("Geocoding failed: No results for address: $address")
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println("Geocoding error: ${e.message}")
            null
        }
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        cameraPositionState = cameraPositionState
    ) {
        reports.forEach { report ->
            val latLng = getLatLngFromAddress(context, report.reportLocal)
            latLng?.let {
                MarkerInfoWindowContent(
                    state = MarkerState(position = latLng),
                    title = "Denúncia: ${report.reportDateTime}",
                    snippet = report.reportDateTime,
                    icon = resizeMarkerIcon(context, R.drawable.ic_marker_pet)
                ) { marker ->
                    Column(
                        modifier = Modifier.padding(top = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Date/time
                        Text(
                            text = marker.title ?: "Denúncia",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )

                        report.reportPhotoUrl?.let { url ->
                            AsyncImage(
                                modifier = Modifier
                                    .padding(top = 8.dp)
                                    .size(200.dp)
                                    .border(
                                        BorderStroke(1.dp, Color.LightGray),
                                        shape = RoundedCornerShape(4.dp)
                                    ),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(url)
                                    .crossfade(true)
                                    .diskCachePolicy(CachePolicy.ENABLED)
                                    .memoryCachePolicy(CachePolicy.ENABLED)
                                    .allowHardware(false)
                                    .build(),
                                contentDescription = "Report image",
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }
    }
}

fun resizeMarkerIcon(context: Context, resId: Int): BitmapDescriptor {
    val originalBitmap = BitmapFactory.decodeResource(context.resources, resId)
    val scaledBitmap = Bitmap.createScaledBitmap(
        originalBitmap,
        80,
        80,
        false
    )
    return BitmapDescriptorFactory.fromBitmap(scaledBitmap)
}