package pt.project.rebanpet.fragments


import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil.setContentView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import pt.project.rebanpet.LoginActivity
import pt.project.rebanpet.R
import pt.project.rebanpet.SignUpActivity
import pt.project.rebanpet.databinding.FragmentReportBinding
import pt.project.rebanpet.report.Report

class ReportFragment : Fragment() {

    lateinit var reportBinding : FragmentReportBinding
    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference : DatabaseReference = database.reference.child("MyReports")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        reportBinding = FragmentReportBinding.inflate(inflater, container, false)
        val view = reportBinding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reportBinding.buttonAddUser.setOnClickListener {
            addReportToDatabase()
        }
    }


    fun addReportToDatabase(){

        val name : String = reportBinding.editTextName.text.toString()
        val age : String = reportBinding.editTextAge.text.toString()

        val id : String = myReference.push().key.toString()
        val report = Report(id,name,age)

        myReference.child(id).setValue(report).addOnCompleteListener { task ->

            if(task.isSuccessful){
                Toast.makeText(context,
                    "The new report has been added to the database",
                    Toast.LENGTH_SHORT).show()
                //finish()
                clearInputs()
            } else {
                Toast.makeText(context,
                    task.exception.toString(),
                    Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun clearInputs() {
        reportBinding.editTextName.text.clear()
        reportBinding.editTextAge.text.clear()
    }
}
