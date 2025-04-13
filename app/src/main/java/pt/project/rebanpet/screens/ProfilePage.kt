package pt.project.rebanpet.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.project.rebanpet.R


@Composable
fun ProfileScreen(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_profile_tab),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(100.dp)
                .padding(top = 16.dp)
        )

        ProfileInfoRow(label = "Nome :", value = "Roberto Lopes")
        ProfileInfoRow(label = "Morada :", value = "Estrada Nacional 2")
        ProfileInfoRow(label = "Contato :", value = "962147536")
        ProfileInfoRow(label = "Email :", value = "roberto.lopes@gmail.com")

        Button(
            onClick = { /* Ação para eliminar conta */ },
            colors = ButtonDefaults.buttonColors(Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            androidx.compose.material.Text(text = "Eliminar conta", color = Color.White)
        }
    }
}

@Composable
fun ProfileInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        androidx.compose.material.Text(
            text = label,
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.padding(start = 24.dp, top = 24.dp)
        )
        androidx.compose.material.Text(
            text = value,
            fontFamily = FontFamily(Font(R.font.montserrat_regular)),
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}