package pt.project.rebanpet.navigation


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import pt.project.rebanpet.fragments.ListReports
import pt.project.rebanpet.fragments.AddReportPage
import pt.project.rebanpet.fragments.MapKennel
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(innerPadding: PaddingValues) {

    val navItemList = listOf(
        BotNav("Histórico", Icons.Filled.List,0),
        BotNav("Denunciar", Icons.Filled.AddCircle,0),
        BotNav("Info", Icons.Filled.Info,0),
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

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent()
            }
        }
    ) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                currentRoute = currentRoute,
                items = navItemList,
                onOpenDrawer = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index ,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            BadgedBox(badge = {
                                if(navItem.badgeCount>0)
                                    Badge(){
                                        Text(text = navItem.badgeCount.toString())
                                    }
                            }) {
                                Icon(imageVector = navItem.icon, contentDescription = "Icon")
                            }

                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding),selectedIndex)

    }
    }
}


data class BotNav(
    val label : String,
    val icon : ImageVector,
    val badgeCount : Int
)


@Composable
fun DrawerContent(modifier: Modifier = Modifier){

    /*Image(
        painter = painterResource(R.drawable.ic_profile_tab),
        contentDescription = null
    )*/

    Text(
        text = "App Name",
        fontSize = 24.sp,
        modifier = Modifier.padding(16.dp)
    )

    HorizontalDivider()
    Spacer(modifier = Modifier.height(4.dp))
    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Inicio",
                modifier = Modifier.padding(16.dp)
            )
        },
        label = {
            Text(
                text = "Inicio",
                fontSize = 17.sp,
                modifier = Modifier.padding(8.dp)
            )
        },
        selected = false,
        onClick = {}
    )
    Spacer(modifier = Modifier.height(4.dp))
    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "Perfil",
                modifier = Modifier.padding(16.dp)
            )
        },
        label = {
            Text(
                text = "Perfil",
                fontSize = 17.sp,
                modifier = Modifier.padding(8.dp)
            )
        },
        selected = false,
        onClick = {}
    )
    Spacer(modifier = Modifier.height(4.dp))
    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Mapa",
                modifier = Modifier.padding(16.dp)
            )
        },
        label = {
            Text(
                text = "Mapa",
                fontSize = 17.sp,
                modifier = Modifier.padding(8.dp)
            )
        },
        selected = false,
        onClick = {}
    )
    Spacer(modifier = Modifier.height(4.dp))
    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.Settings,
                contentDescription = "Definições",
                modifier = Modifier.padding(16.dp)
            )
        },
        label = {
            Text(
                text = "Definições",
                fontSize = 17.sp,
                modifier = Modifier.padding(8.dp)
            )
        },
        selected = false,
        onClick = {}
    )
    HorizontalDivider()
    Spacer(modifier = Modifier.height(4.dp))
    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "Sair",
                modifier = Modifier.padding(16.dp)
            )
        },
        label = {
            Text(
                text = "Sair",
                fontSize = 17.sp,
                modifier = Modifier.padding(8.dp)
            )
        },
        selected = false,
        onClick = {}
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onOpenDrawer: () -> Unit,
    currentRoute: String?,
    items: List<BotNav>
){
    val currentItem = items.find { it.label == currentRoute }
    val title = currentItem?.label ?: "Rebanpet"

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(0.6f)
        ),

        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .size(28.dp)
                    .clickable {
                        onOpenDrawer()
                }
            )
        },
        title = {
            Text(text = title)
        },
        actions = {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Menu",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        onOpenDrawer()
                    }
            )
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Menu",
                modifier = Modifier
                    .padding(start = 8.dp, end = 16.dp)
                    .size(28.dp)
                    .clickable {
                        onOpenDrawer()
                    }
            )
        }
    )
}



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
