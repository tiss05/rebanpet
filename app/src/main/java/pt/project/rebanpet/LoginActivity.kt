package pt.project.rebanpet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import pt.project.rebanpet.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding : ActivityLoginBinding
    val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var googleSignInClient: GoogleSignInClient

    companion object {
        private const val RC_SIGN_IN = 9001
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)

        loginBinding.buttonLogin.setOnClickListener {
            val userEmail = loginBinding.loginEmail.text.toString()
            val userPassword = loginBinding.loginPassword.text.toString()

            signinWithFirebase(userEmail, userPassword)
        }

        loginBinding.buttonSignupGoogle.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            googleSignInClient = GoogleSignIn.getClient(this, gso)
            signIn()
        }


        loginBinding.buttonSignupEmail.setOnClickListener {
            val intent = Intent(this@LoginActivity,SignUpActivity::class.java)
            startActivity(intent)
        }

        loginBinding.buttonResetPassword.setOnClickListener {
            val intent = Intent(this@LoginActivity,ResetPasswordActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun signinWithFirebase(userEmail:String, userPassword:String){
        if (userEmail.isNotEmpty() && userPassword.isNotEmpty()) {
            auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {

                        /*Toast.makeText(
                            applicationContext,
                            "Login is successful",
                            Toast.LENGTH_SHORT
                        ).show()*/
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                }
        } else {

            Toast.makeText(
                applicationContext,
                "O teu e-mail ou palavra-passe estão incorretos",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d("MainActivity", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w("MainActivity", "Google sign in failed", e)
                Toast.makeText(this, "Google Sign-In Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Log.w("MainActivity", "signInWithCredential:failure", task.exception)
                    Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("name", user.displayName)
            intent.putExtra("email", user.email)
            intent.putExtra("photoUrl", user.photoUrl.toString())
            startActivity(intent)
            finish()
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

    /*override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Rebanpet")
        builder.setIcon(R.mipmap.ic_logo)
        builder.setMessage("Deseja sair da aplicação?")
        builder.setPositiveButton("Sim") { _, _ ->
            super.onBackPressed()
        }
        builder.setNegativeButton("Não") { dialog, _ ->
            dialog.dismiss()
        }
        builder.setCancelable(false)
        builder.show()
    }*/
}