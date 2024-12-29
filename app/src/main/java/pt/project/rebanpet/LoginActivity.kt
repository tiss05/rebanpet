package pt.project.rebanpet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pt.project.rebanpet.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding : ActivityLoginBinding
    val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)
        //setContentView(R.layout.activity_login)

        loginBinding.buttonLogin.setOnClickListener {
            val userEmail = loginBinding.loginEmail.text.toString()
            val userPassword = loginBinding.loginPassword.text.toString()

            signinWithFirebase(userEmail, userPassword)
        }

        loginBinding.buttonSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity,SignUpActivity::class.java)
            startActivity(intent)
        }

        /*loginBinding.buttonForgot.setOnClickListener {
            val intent = Intent(this,ForgetActivity::class.java)
            startActivity(intent)
        }

        loginBinding.buttonPhoneNumber.setOnClickListener {
            val intent = Intent(this,PhoneActivity::class.java)
            startActivity(intent)
            finish()
        }*/

    }

    fun signinWithFirebase(userEmail:String, userPassword:String){

        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful){

                    Toast.makeText(applicationContext,
                        "Login is successful",
                        Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {

                    Toast.makeText(applicationContext,
                        task.exception?.toString(),
                        Toast.LENGTH_SHORT).show()

                }

            }

    }

    override fun onStart() {
        super.onStart()

        val user = auth.currentUser

        if(user != null){

            val intent = Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}