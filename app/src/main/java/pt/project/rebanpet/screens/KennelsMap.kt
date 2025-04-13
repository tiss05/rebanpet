package pt.project.rebanpet.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import pt.project.rebanpet.kennels.KennelsMarker
import pt.project.rebanpet.kennels.KennelsDistricts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapKennel(innerPadding: PaddingValues) {
    val countryCoordinates = LatLng(39.3999, -8.2245)
    var selectedCountry by remember {
        mutableStateOf(
            KennelsDistricts
                .countryDistricts[0]
        )
    }
    var expanded by remember { mutableStateOf(false) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(selectedCountry.coordinates, 5f)
    }
    LaunchedEffect(Unit) {
        cameraPositionState.position = CameraPosition.fromLatLngZoom(countryCoordinates, 7f)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it }
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    readOnly = true,
                    value = selectedCountry.name,
                    onValueChange = {},
                    label = { Text("Canis disponíveis no distrito de") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    KennelsDistricts.countryDistricts.forEach { country ->
                        DropdownMenuItem(
                            text = { Text(text = country.name) },
                            onClick = {
                                selectedCountry = country
                                expanded = false
                                cameraPositionState.position = CameraPosition.fromLatLngZoom(
                                    country.coordinates,
                                    10f
                                )
                            }
                        )
                    }
                }
            }
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            KennelsMarker.markers.forEach { marker ->
                Marker(
                    state = MarkerState(position = marker.coordinates),
                    title = marker.name
                )
            }
        }
    }
}