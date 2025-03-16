package pt.project.rebanpet.fragments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import pt.project.rebanpet.report.Report


@Composable
fun ListReports() {
    val reportList = remember { mutableStateListOf<Report>() }
    var isLoading by remember { mutableStateOf(true) }
    var isEmpty by remember { mutableStateOf(false) }
    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance()
    val currentUser = auth.currentUser
    val mRef = currentUser?.uid?.let { database.getReference("reports/$it") }

    LaunchedEffect(key1 = Unit) {
        if (mRef != null) {
            mRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    isLoading = false
                    if (snapshot.exists() && snapshot.childrenCount > 0) {
                        reportList.clear()
                        for (eachUser in snapshot.children) {
                            val report = eachUser.getValue(Report::class.java)
                            report?.let {
                                reportList.add(it)
                            }
                        }
                        isEmpty = reportList.isEmpty()
                    } else {
                        isEmpty = true
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    isLoading = false
                    // Handle error, maybe set isEmpty to true or show an error message
                    isEmpty = true // Consider showing an error message instead of just empty state
                }
            })
        } else {
            isLoading = false
            isEmpty = true // No user logged in, treat as empty
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
        ) {

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (isEmpty) {
                EmptyHistoricalScreen()
            } else {
                ReportList(reports = reportList)
            }
        }

        /*IconButton(
            onClick = { },
            modifier = Modifier
                .align(Alignment.TopEnd) // Align to top end (right)
                .padding(5.dp) // margin 5dp
        ) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Info",
                tint = Color.DarkGray
            )
        }*/
    }
}

@Composable
fun ReportList(reports: List<Report>) {
    LazyColumn(
        contentPadding = PaddingValues(6.dp)
    ) {
        items(reports) { report ->
            ReportItem(report = report)
        }
    }
}

@Composable
fun ReportItem(report: Report) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp)
            .background(Color.Transparent),
        shape = RoundedCornerShape(7.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image
            Image(
                painter = rememberImagePainter(data = report.reportPhotoUrl),
                contentDescription = "Report Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(7.dp))
                    .border(1.dp, Color.Gray, RoundedCornerShape(7.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(10.dp))

            // Text Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Local
                Row {
                    Text(
                        text = "Local: ",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = report.reportLocal,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Date
                Row {
                    Text(
                        text = "Data: ",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = report.reportDateTime,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Description
                Row {
                    Text(
                        text = "Descrição: ",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = report.reportDescription,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}


@Composable
fun EmptyHistoricalScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No reports available yet.",
            style = MaterialTheme.typography.bodyLarge)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ListReports()
}