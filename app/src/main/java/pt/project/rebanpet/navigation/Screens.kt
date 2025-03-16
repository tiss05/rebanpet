package pt.project.rebanpet.navigation

sealed class Screens(var route: String) {

    object  Home : Screens("home")
    object  Profile : Screens("profile")
    object  ReportsMap : Screens("reports-map")
    object  Setting : Screens("setting")
    object  Logout : Screens("logout")
}