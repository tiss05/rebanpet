package pt.project.rebanpet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    lateinit var name : EditText
    lateinit var age : EditText
    lateinit var send : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //name = findViewById(R.id.loginEmail)
        //age = findViewById(R.id.loginPassword)
        send = findViewById(R.id.buttonLogin)

        send.setOnClickListener {

            //var userName : String = name.text.toString()
            //var userAge : Int = age.text.toString().toInt()

            var intent = Intent(this@LoginActivity, MainActivity::class.java)

            //intent.putExtra("username",userName)
            //intent.putExtra("userage",userAge)

            startActivity(intent)

        }

    }
}