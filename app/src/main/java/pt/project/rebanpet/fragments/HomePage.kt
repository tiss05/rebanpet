package pt.project.rebanpet.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import pt.project.rebanpet.R
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import pt.project.rebanpet.ui.theme.RebanpetThemee


@Composable
fun HomeScreen(innerPadding: PaddingValues) {
    val navItemList = listOf(
        BotNav("Histórico", R.drawable.list_outline, 0),
        BotNav("Denunciar", R.drawable.plus_outline, 0),
        BotNav("Info", R.drawable.info_circle_outline, 0),
    )

    var selectedIndex by remember {
        mutableIntStateOf(1)
    }

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val scrollState = rememberScrollState()

    RebanpetThemee {
        Scaffold(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),

            bottomBar = {
                NavigationBar {
                    navItemList.forEachIndexed { index, navItem ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                            },
                            icon = {
                                BadgedBox(badge = {
                                    if (navItem.badgeCount > 0)
                                        Badge {
                                            Text(text = navItem.badgeCount.toString())
                                        }
                                }) {
                                    Icon(
                                        painter = painterResource(id = navItem.icon),
                                        modifier = Modifier.size(24.dp),
                                        contentDescription = "Icon"
                                    )
                                }

                            },
                            label = {
                                if (selectedIndex == index) {
                                    Text(text = navItem.label)
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            ContentScreen(
                modifier = Modifier
                    .padding(innerPadding),
                selectedIndex = selectedIndex
            )
        }
    }
}

data class BotNav(
    val label: String,
    val icon: Int,
    val badgeCount: Int
)

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex : Int) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        when(selectedIndex){
            0-> ListReports()
            1-> AddReportPage()
            2-> MapKennel()
        }
    }
}