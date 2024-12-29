package pt.project.rebanpet

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pt.project.rebanpet.databinding.ActivityLoginBinding
import pt.project.rebanpet.databinding.ActivityResetPasswordBinding

class ResetPasswordActivity : AppCompatActivity() {
    lateinit var resetPassowordBinding : ActivityResetPasswordBinding
    val auth : FirebaseAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resetPassowordBinding = ActivityResetPasswordBinding.inflate(layoutInflater)
        val view = resetPassowordBinding.root
        setContentView(view)

        resetPassowordBinding.buttonResetPassword.setOnClickListener {
            val email = resetPassowordBinding.editTextEmail.text.toString().trim()
            if (email.isEmpty()) {
                Toast.makeText(this, "Insira o seu e-mail", Toast.LENGTH_SHORT).show()
            } else {
                sendPasswordResetEmail(email)
            }
        }

        resetPassowordBinding.buttonCancel.setOnClickListener {
            val intent = Intent(this@ResetPasswordActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun sendPasswordResetEmail(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,
                        "E-mail de recuperação de conta enviado para ${email}",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "E-mail inválido", Toast.LENGTH_SHORT).show()
                }
            }
    }


}