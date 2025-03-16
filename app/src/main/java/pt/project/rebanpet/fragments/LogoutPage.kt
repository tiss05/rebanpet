package pt.project.rebanpet.fragments

import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import pt.project.rebanpet.LoginActivity
import android.content.Intent

@Composable
fun LogoutScreen(navController: NavController) {
    FirebaseAuth.getInstance().signOut()
    val context = LocalContext.current

    // Create an Intent to start your login Activity
    val intent = Intent(context, LoginActivity::class.java)

    // Add flags to clear the activity stack so the user can't go back without logging in again
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

    // Start the login Activity
    startActivity(context, intent, null)

    // Optionally, finish the current Activity if needed
    // This requires a bit more setup to get the current Activity from Compose
    // Here's one way to do it (requires importing androidx.activity.ComponentActivity):
    /*
    val activity = context.findActivity()
    activity?.finish()
    */
}