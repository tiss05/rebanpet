package pt.project.rebanpet.fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import pt.project.rebanpet.R
import java.io.IOException
import java.util.Locale

@Composable
fun MapWithCountrySelector() {
    val context = LocalContext.current
    val countries = stringArrayResource(id = R.array.countries_array)
    var expanded by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf(countries[0]) } // Default selection
    var cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 2f) // Default world view
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = selectedCountry,
                onValueChange = { /* Do nothing, readonly */ },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = { Text("Select Country") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                countries.forEach { country ->
                    DropdownMenuItem(
                        text = { Text(text = country) },
                        onClick = {
                            selectedCountry = country
                            expanded = false
                            // Move camera to selected country
                            val countryLatLng = getLatLngFromCountry(context, country)
                            if (countryLatLng != null) {
                                cameraPositionState.move(
                                    CameraUpdateFactory.newCameraPosition(
                                        CameraPosition.fromLatLngZoom(countryLatLng, 5f) // Zoom level 5 - adjust as needed
                                    )
                                )
                            } else {
                                // Handle case where geocoding fails (e.g., show a Toast)
                                // Or fallback to a default location/zoom
                                // Toast.makeText(context, "Could not find location for $country", Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }
            }
        }

        // Country Dropdown Button (Trigger) - to expand the menu
        Button(
            onClick = { expanded = true },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        ) {
            Text(text = selectedCountry) // You can remove this Button if using OutlinedTextField as trigger
        }
    }
}

// Function to get LatLng from country name using Geocoder
fun getLatLngFromCountry(context: Context, countryName: String): LatLng? {
    val geocoder = Geocoder(context, Locale.getDefault())
    try {
        val addresses = geocoder.getFromLocationName(countryName, 1) // Get up to 1 address
        if (addresses != null && addresses.isNotEmpty()) {
            val address = addresses[0]
            return LatLng(address.latitude, address.longitude)
        }
    } catch (e: IOException) {
        e.printStackTrace() // Handle Geocoder exceptions appropriately
    }
    return null // Return null if location not found or error
}

// Function to check if location permission is granted (for MyLocation feature)
fun isLocationPermissionGranted(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}