package pt.project.rebanpet.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pt.project.rebanpet.screens.ProfileScreen
import pt.project.rebanpet.screens.SettingScreen
import pt.project.rebanpet.screens.HomeScreen
import pt.project.rebanpet.screens.LogoutScreen
import pt.project.rebanpet.screens.MapKennel

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(navController = navController,
        startDestination = Screens.Home.route){
        composable(Screens.Home.route){
            HomeScreen(innerPadding = innerPadding)
        }
        composable(Screens.Profile.route){
            ProfileScreen(innerPadding = innerPadding)
        }
        composable(Screens.ReportsMap.route){
            MapKennel(innerPadding = innerPadding)
        }
        composable(Screens.Setting.route){
            SettingScreen(innerPadding = innerPadding)
        }
        composable(Screens.Logout.route){
            LogoutScreen(navController = navController)
        }
    }
}