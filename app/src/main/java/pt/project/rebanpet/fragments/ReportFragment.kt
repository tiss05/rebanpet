package pt.project.rebanpet.fragments


import android.content.DialogInterface
import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil.setContentView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth
import pt.project.rebanpet.R
import pt.project.rebanpet.SignUpActivity
import pt.project.rebanpet.databinding.FragmentReportBinding
import pt.project.rebanpet.report.Report
import pt.project.rebanpet.databinding.CustomAlertDialogBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class ReportFragment : Fragment() {

    lateinit var reportBinding : FragmentReportBinding
    val auth : FirebaseAuth = FirebaseAuth.getInstance()
    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference : DatabaseReference = database.reference.child("MyReports")
    lateinit var mRef : DatabaseReference

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

        val descriptionAnimal : String = reportBinding.editTextName.text.toString()
        val localAnimal : String = reportBinding.editTextAge.text.toString()

        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm")
        val currentDateTime = sdf.format(Date())

        val id : String = myReference.push().key.toString()
        val report = Report(id,descriptionAnimal,localAnimal,currentDateTime)

        val currentUser = auth.currentUser
        if (currentUser != null) {
            mRef = FirebaseDatabase.getInstance().getReference("reports/${currentUser.uid}")
        }
        if (descriptionAnimal.isNotEmpty() && localAnimal.isNotEmpty()) {
            mRef.child(id).setValue(report).addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    /*Toast.makeText(context,
                    "The new report has been added to the database",
                    Toast.LENGTH_SHORT).show()*/
                    //finish()
                    clearInputs()
                    showDialogMessage()
                }

            }
        } else {
            Toast.makeText(
                context,
                "Dados em falta ou incorretos",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun clearInputs() {
        reportBinding.editTextName.text.clear()
        reportBinding.editTextAge.text.clear()
    }

    /*private fun showCustomAlertDialog() {
        val dialogBinding = DialogCustomBinding.inflate(layoutInflater)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .setCancelable(true)

        val alertDialog = dialogBuilder.create()
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // Optional: Set custom click behavior for the dialog
        dialogBinding.textViewDialogMessage.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }*/

    private fun showDialogMessage() {

        val dialogBinding = CustomAlertDialogBinding.inflate(LayoutInflater.from(requireContext()))

        // Create the AlertDialog Builder
        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .setCancelable(false)
            .create()

        // Set Button Click Listeners
        dialogBinding.dialogTitle.text = "Denúncia concluida!"
        dialogBinding.dialogMessage.text = "Adicionada no histórico de denúncias"

        dialogBinding.btnDone.setOnClickListener { //dialog, _ ->
            /*Toast.makeText(context, "You clicked OK!", Toast.LENGTH_SHORT).show()*/
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentReport, HistoricalFragment())
            fragmentTransaction.addToBackStack(null)  // Enables back navigation
            fragmentTransaction.commit()
            alertDialog.dismiss()
        }

        /*alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel") { dialog, _ ->
            dialog.dismiss()
        }*/

        // Show the AlertDialog
        alertDialog.show()

        /*val dialogMessage = AlertDialog.Builder(requireContext())
        dialogMessage.setTitle("Sair")
        dialogMessage.setMessage("Tem a certeza?")
        dialogMessage.setNegativeButton("No", DialogInterface.OnClickListener {
                dialogInterface, i -> dialogInterface.cancel()
        })
        dialogMessage.setPositiveButton("Yes", DialogInterface.OnClickListener {
                dialogInterface, i -> myReference.removeValue().addOnCompleteListener{ task ->
            if(task.isSuccessful){
                /*FirebaseAuth.getInstance().signOut()
                val intent = Intent(this@MainActivity,LoginActivity::class.java)
                startActivity(intent)
                finish()
                usersAdapter.notifyDataSetChanged()*/
                Toast.makeText(context,"Denuncia feita", Toast.LENGTH_SHORT).show()
            }
        }
        })

        dialogMessage.create().show()*/

    }

}
