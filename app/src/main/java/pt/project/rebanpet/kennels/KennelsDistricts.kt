package pt.project.rebanpet.kennels

import com.google.android.gms.maps.model.LatLng

object KennelsDistricts {
    val countryDistricts = listOf(
        District("Açores", LatLng(37.74, -25.67)),
        District("Aveiro", LatLng(40.64, -8.64)),
        District("Beja", LatLng(38.0167, -7.8633)),
        District("Braga", LatLng(41.5469,-8.4289)),
        District("Bragança", LatLng(41.8061,-6.7592)),
        District("Castelo Branco", LatLng(39.8231, -7.4914)),
        District("Coimbra", LatLng(40.2111, -8.4291)),
        District("Évora", LatLng(38.5744, -7.9097)),
        District("Faro", LatLng(37.0194, -7.9306)),
        District("Guarda", LatLng(40.5350, -7.2675)),
        District("Leiria", LatLng(39.7436, -8.8072)),
        District("Lisboa", LatLng(38.7223, -9.1393)),
        District("Madeira ", LatLng(32.6667, -16.9083)),
        District("Portalegre", LatLng(39.2933, -7.4350)),
        District("Porto", LatLng(41.1579, -8.6291)),
        District("Santarém", LatLng(39.2356, -8.6844)),
        District("Setúbal", LatLng(38.5225, -8.8917)),
        District("Viana do Castelo", LatLng(41.6931, -8.8253)),
        District("Vila Real", LatLng(41.3000, -7.7333)),
        District("Viseu", LatLng(40.6625, -7.9097))
    )
}

data class District(
    val name: String,
    val coordinates: LatLng
)