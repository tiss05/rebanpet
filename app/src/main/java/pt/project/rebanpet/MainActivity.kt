package pt.project.rebanpet

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.content.DialogInterface
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation
import pt.project.rebanpet.fragments.HistoricalFragment
import pt.project.rebanpet.fragments.InfoFragment
import pt.project.rebanpet.fragments.ProfileFragment
import pt.project.rebanpet.fragments.HomeFragment
import pt.project.rebanpet.fragments.SettingsFragment

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    lateinit var auth : FirebaseAuth

    private val onBackPressedCallback = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            onBackPressedMethod()
        }

    }

    private fun onBackPressedMethod() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            finish()
        }
    }

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var test: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawerLayout)

        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        val header = navigationView.getHeaderView(0)
        val userNameTxt = header.findViewById<TextView>(R.id.userNameTxt)
        val emailTxt = header.findViewById<TextView>(R.id.emailTxt)
        val profileImg = header.findViewById<ImageView>(R.id.profileImg)

        auth = FirebaseAuth.getInstance()
        //uid = auth.currentUser?.uid.toString()

        val user = auth.currentUser

        if(user != null){

            val uid = user.uid
            val name = user.displayName
            val email = user.email

            emailTxt.text=email
            userNameTxt.text=name
        }


        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this,drawerLayout,toolbar,R.string.nav_open,R.string.nav_close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        /// Default Navigation bar Tab Selected
        replaceFragment(HomeFragment())
        navigationView.setCheckedItem(R.id.nav_home)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navFragment,fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> {
                replaceFragment(HomeFragment())
                title = "Denúncia"
            }
            R.id.nav_profile -> {
                replaceFragment(ProfileFragment())
                title = "Perfil"
            }
            R.id.nav_settings -> {
                replaceFragment(SettingsFragment())
                title = "Definições"
            }
            R.id.nav_logout -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this@MainActivity,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    /*fun userInfo(){
        auth = FirebaseAuth.getInstance()
        //uid = auth.currentUser?.uid.toString()

        val user = auth.currentUser

        if(user != null){

            val uid = user.uid
            val email = user.email

            test.user.text=email
            profileBinding.contentContactProfile.text=email
            profileBinding.contentNameProfile.text=uid

        }
    }*/

    /*fun showDialogMessage() {
        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        val dialogMessage = AlertDialog.Builder(this)
        dialogMessage.setTitle("Sair")
        dialogMessage.setMessage("Tem a certeza?")
        dialogMessage.setNegativeButton("No", DialogInterface.OnClickListener {
                dialogInterface, i -> dialogInterface.cancel()
        })
        dialogMessage.setPositiveButton("Yes", DialogInterface.OnClickListener {
                dialogInterface, i -> myReference.removeValue().addOnCompleteListener{ task ->
            if(task.isSuccessful){
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this@MainActivity,LoginActivity::class.java)
                startActivity(intent)
                finish()
                //usersAdapter.notifyDataSetChanged()
                //Toast.makeText(applicationContext,"All users were deleted", Toast.LENGTH_SHORT).show()
            }
        }
        })

        dialogMessage.create().show()

    }*/

    override fun onBackPressed() {
        super.onBackPressed()
    }
}