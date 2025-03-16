package pt.project.rebanpet.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pt.project.rebanpet.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import androidx.compose.ui.text.TextStyle
import coil.compose.AsyncImage

@Composable
fun NavBarHeader() {
    val currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    val displayName = currentUser?.displayName ?: "Nome"
    val email = currentUser?.email ?: "Email"
    val photoUrl = currentUser?.photoUrl

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*if (photoUrl != null) {
            AsyncImage(
                model = photoUrl,
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(100.dp)
                    .padding(top = 10.dp)
            )
        } else {*/
            Image(
                painter = painterResource(id = R.drawable.ic_profile_tab),
                contentDescription = "foto de perfil",
                modifier = Modifier
                    .size(100.dp)
                    .padding(top = 10.dp)
            )
            Text(
                text = displayName,
                modifier = Modifier
                    .padding(top = 10.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp
                )
            )
            Text(
                text = email,
                modifier = Modifier.padding(top = 10.dp)
            )

        }
    }


@Composable
fun NavBarBody(
    items: List<NavigationItem>,
    currentRoute: String?,
    onClick: (NavigationItem) -> Unit,
) {
    items.forEachIndexed { index, navigationItem ->
        NavigationDrawerItem(
            colors = NavigationDrawerItemDefaults.colors(

            ),
            label = {
                Text(text = navigationItem.title)
            }, selected = currentRoute == navigationItem.route, onClick = {
                onClick(navigationItem)
            }, icon = {
                Icon(
                    imageVector = if (currentRoute == navigationItem.route) {
                        navigationItem.selectedIcon
                    } else {
                        navigationItem.unSelectedIcon
                    }, contentDescription = navigationItem.title
                )
            },
            /*badge = {
                navigationItem.badgeCount?.let {
                    Text(text = it.toString())
                }
            },*/
            modifier = Modifier.padding(
                PaddingValues(horizontal = 12.dp,
                    vertical = 8.dp)
            ))
    }
}
