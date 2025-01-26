package pt.project.rebanpet

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pt.project.rebanpet.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class SignUpActivity : AppCompatActivity() {
    lateinit var signupBinding: ActivitySignupBinding
    val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivitySignupBinding.inflate(layoutInflater)
        val view = signupBinding.root
        setContentView(view)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title="Registo"

        signupBinding.buttonSignupUser.setOnClickListener {

            val userName = signupBinding.editTextNameSignup.text.toString()
            val userLocal = signupBinding.editTextLocalSignup.text.toString()
            val userEmail = signupBinding.editTextEmailSignup.text.toString()
            val userPassword = signupBinding.editTextPasswordSignup.text.toString()

            if (userName.isEmpty() || userLocal.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Falta preencher os dados", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                signupWithFirebase(userName, userEmail, userPassword)
            }

        }

    }

    fun signupWithFirebase(userName:String, userEmail:String, userPassword:String){

        auth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener{task ->

            if (task.isSuccessful){

                val user = auth.currentUser
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(userName)
                    .build()

                user?.updateProfile(profileUpdates)
                    ?.addOnCompleteListener { profileTask ->
                        if (profileTask.isSuccessful) {
                            Toast.makeText(applicationContext,
                                "Your account has been created",
                                Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(applicationContext,
                                "Failed to update profile: ${profileTask.exception?.message}",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this,
                    "Registration failed: ${task.exception?.message}",
                    Toast.LENGTH_SHORT).show()
            }
                /*
                Toast.makeText(applicationContext,
                    "Your account has been created",
                    Toast.LENGTH_SHORT).show()
                finish()

            } else {

                Toast.makeText(applicationContext,
                    task.exception?.toString(),
                    Toast.LENGTH_SHORT).show()

            }*/

        }

    }


}