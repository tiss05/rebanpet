package pt.project.rebanpet.navigation

import androidx.activity.ComponentActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import pt.project.rebanpet.R
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter

class NavigationDrawer : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                val items = listOf(
                    NavigationItem(
                        title = "Inicio",
                        route = Screens.Home.route,
                        selectedIcon = Icons.Filled.Home,
                        unSelectedIcon = Icons.Outlined.Home,
                    ),
                    NavigationItem(
                        title = "Perfil",
                        route = Screens.Profile.route,
                        selectedIcon = Icons.Filled.Person,
                        unSelectedIcon = Icons.Outlined.Person,
                    ),
                    NavigationItem(
                        title = "Canis",
                        route = Screens.ReportsMap.route,
                        selectedIcon = ImageVector.vectorResource(R.drawable.ic_kennel_selected),
                        unSelectedIcon = ImageVector.vectorResource(R.drawable.ic_kennel_unselected),
                    ),
                    NavigationItem(
                        title = "Definições",
                        route = Screens.Setting.route,
                        selectedIcon = Icons.Filled.Settings,
                        unSelectedIcon = Icons.Outlined.Settings,
                    ),
                    NavigationItem(
                        title = "Sair",
                        route = Screens.Logout.route,
                        selectedIcon = Icons.Filled.ExitToApp,
                        unSelectedIcon = Icons.Outlined.ExitToApp,
                    ),
                )
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()
                val context = LocalContext.current
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val topBarTitle =
                    if (currentRoute != null){
                        items[items.indexOfFirst {
                            it.route == currentRoute
                        }].title
                    }else{
                        items[0].title
                    }
                ModalNavigationDrawer(
                    gesturesEnabled = drawerState.isOpen,drawerContent = {
                        ModalDrawerSheet(

                        ) {
                            NavBarHeader()
                            Spacer(modifier = Modifier.height(8.dp))
                            NavBarBody(items = items, currentRoute =currentRoute) { currentNavigationItem ->
                                if(currentNavigationItem.route == "share"){
                                    Toast.makeText(context,"Share Clicked",Toast.LENGTH_LONG).show()
                                }else{
                                    navController.navigate(currentNavigationItem.route){
                                        navController.graph.startDestinationRoute?.let { startDestinationRoute ->
                                            popUpTo(startDestinationRoute) {
                                                saveState = true
                                            }
                                        }

                                        launchSingleTop = true

                                        restoreState = true
                                    }
                                }

                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        }
                    }, drawerState = drawerState) {
                    Scaffold(
                        topBar = {
                            TopAppBar(title = {
                                Text(text = topBarTitle)
                            }, navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "menu"
                                    )
                                }
                            })
                        }
                    ) {innerPadding->
                        SetUpNavGraph(navController = navController, innerPadding = innerPadding)
                    }
                }
            }
        }
    }