package pt.project.rebanpet.fragments

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ValueEventListener
import pt.project.rebanpet.R
import pt.project.rebanpet.databinding.CustomAlertDialogBinding
import pt.project.rebanpet.databinding.FragmentEmptyBinding
import pt.project.rebanpet.databinding.FragmentHistoricalBinding
import pt.project.rebanpet.report.Report
import pt.project.rebanpet.report.ReportAdapter

class HistoricalFragment : Fragment() {

    lateinit var historicalBinding: FragmentHistoricalBinding
    lateinit var emptyBinding: FragmentEmptyBinding
    val auth : FirebaseAuth = FirebaseAuth.getInstance()
    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val currentUser = auth.currentUser
    lateinit var mRef : DatabaseReference
    //= database.reference.child("MyReports/${currentUser.uid}")

    val reportList = ArrayList<Report>()
    lateinit var reportAdapter: ReportAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        historicalBinding = FragmentHistoricalBinding.inflate(inflater, container, false)
        val view = historicalBinding.root



        setupFirebase()
        retrieveDataFromDatabase()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historicalBinding.infoButton.setOnClickListener {
            test()
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val id = reportAdapter.getReportId(viewHolder.adapterPosition)

                mRef.child(id).removeValue()

                Toast.makeText(context,
                    "Denúncia eliminada com sucesso",
                    Toast.LENGTH_SHORT).show()

            }

        }).attachToRecyclerView(historicalBinding.recyclerView)

        setupFirebase()
        retrieveDataFromDatabase()


    }

    private fun setupFirebase() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            mRef = FirebaseDatabase.getInstance().getReference("reports/${currentUser.uid}")
        }
    }

    fun retrieveDataFromDatabase(){

        mRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists() && snapshot.childrenCount > 0) {
                    reportList.clear()

                    for(eachUser in snapshot.children){
                        val report = eachUser.getValue(Report::class.java)
                        if(report != null){
                            reportList.add(report)
                        }

                        reportAdapter = ReportAdapter(this@HistoricalFragment, reportList)
                        historicalBinding.recyclerView.layoutManager = LinearLayoutManager(context)
                        historicalBinding.recyclerView.adapter = reportAdapter

                    }

            } else {
                    // If no reports, stay on the empty state layout
                    val fragmentTransaction = parentFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fragmentHistorical, EmptyHistoricalFragment())
                    fragmentTransaction.addToBackStack(null)  // Enables back navigation
                    fragmentTransaction.commit()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }



    private fun showDialogMessage() {

        val dialogBinding = CustomAlertDialogBinding.inflate(LayoutInflater.from(requireContext()))

        // Create the AlertDialog Builder
        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)  // Set the custom view
            .setCancelable(false)         // Prevent dismiss by tapping outside
            .create()

        // Set Button Click Listeners
        dialogBinding.dialogTitle.text = "Eliminar denúncia"
        dialogBinding.dialogMessage.text = "Deseja eliminar a denúncia do seu histórico?"

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Sim") { dialog, _ ->
            //val id = reportAdapter.getReportId(viewHolder.adapterPosition)

            //mRef.child(id).removeValue()

            Toast.makeText(context,
                "Denúncia eliminada com sucesso",
                Toast.LENGTH_SHORT).show()
        }

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Não") { dialog, _ ->
            dialog.dismiss()
        }

        // Show the AlertDialog
        alertDialog.show()

    }



    fun test() {

        /*val dialogBinding = CustomAlertDialogBinding.inflate(LayoutInflater.from(requireContext()))

        // Create the AlertDialog Builder
        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)  // Set the custom view
            .setCancelable(false)         // Prevent dismiss by tapping outside
            .create()

        // Set Button Click Listeners
        dialogBinding.dialogTitle.text = "Info"
        dialogBinding.dialogMessage.text = "Arrasta para o lado para eliminar uma denúncia"

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar") { dialog, _ ->
            //val id = reportAdapter.getReportId(viewHolder.adapterPosition)

            //mRef.child(id).removeValue()

            /*Toast.makeText(context,
                "Denúncia eliminada com sucesso",
                Toast.LENGTH_SHORT).show()*/
            dialog.dismiss()
        }*/

        val dialogMessage = AlertDialog.Builder(requireContext())
        dialogMessage.setTitle("Info")
        dialogMessage.setMessage("Arrasta para um dos lados para eliminar uma denúncia")
        dialogMessage.setNegativeButton("Ok", DialogInterface.OnClickListener {
                dialogInterface, i -> dialogInterface.cancel()
        })

        dialogMessage.create().show()

    }
}
