package pt.project.rebanpet.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pt.project.rebanpet.fragments.ReportsMapScreen
import pt.project.rebanpet.fragments.ProfileScreen
import pt.project.rebanpet.fragments.SettingScreen
import pt.project.rebanpet.fragments.HomeScreen
import pt.project.rebanpet.fragments.LogoutScreen

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
            ReportsMapScreen(innerPadding = innerPadding)
        }
        composable(Screens.Setting.route){
            SettingScreen(innerPadding = innerPadding)
        }
        composable(Screens.Logout.route){
            LogoutScreen(navController = navController)
        }
    }
}