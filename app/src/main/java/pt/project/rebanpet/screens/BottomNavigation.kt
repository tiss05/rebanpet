package pt.project.rebanpet.screens

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier

@Composable
fun BottomNavigation() {

    var currentRoute by remember {
        mutableStateOf("home")
    }

    val items = listOf(
        BottomNavigationItem(
            "Histórico",
            "list",
            Icons.Filled.List
        ),
        BottomNavigationItem(
            "Denunciar",
            "home",
            Icons.Filled.AddCircle
        ),
        BottomNavigationItem(
            "Info",
            "info",
            Icons.Filled.Info
        ),
    )

    Scaffold (
        bottomBar = {
            BottomNavigationBar(items = items, currentScreen = currentRoute) {
                currentRoute = it
            }

        }
    ) {
        paddingValues ->
        when (currentRoute){
            "list" -> ScreenOne(paddingValues, Color.Red)
            "home" -> ScreenOne(paddingValues, Color.Green)
            "info" -> ScreenOne(paddingValues, Color.Yellow)
        }

    }
}

@Composable
fun BottomNavigationBar(
    items : List<BottomNavigationItem> = listOf(),
    currentScreen : String,
    onItemClick : (String) -> Unit
){
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentScreen == item.route,
                onClick = { onItemClick(item.route) },
                label = { Text(text = item.title) },
                alwaysShowLabel = currentScreen== item.route,
                icon = { Icon(imageVector = item.icon, contentDescription = "") }
            )
        }
    }
}

data class BottomNavigationItem(
    val title : String,
    val route : String,
    val icon : ImageVector
)

@Composable
fun ScreenOne(paddingValues: PaddingValues,
              color: Color
                ){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = color)

    ) {

    }
}
