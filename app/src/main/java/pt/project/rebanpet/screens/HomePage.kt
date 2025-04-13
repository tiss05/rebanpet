package pt.project.rebanpet.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pt.project.rebanpet.R
import pt.project.rebanpet.ui.theme.RebanpetThemee


@Composable
fun HomeScreen(innerPadding: PaddingValues) {
    val navItemList = listOf(
        BotNav("Histórico", R.drawable.list_outline, 0),
        BotNav("Denunciar", R.drawable.plus_outline, 0),
        BotNav("Mapa Geral", R.drawable.ic_map_pets, 0),
    )

    var selectedIndex by remember {
        mutableIntStateOf(1)
    }

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
            2-> ReportsMapScreen()
        }
    }
}