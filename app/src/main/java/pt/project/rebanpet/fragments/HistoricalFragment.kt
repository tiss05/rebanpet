package pt.project.rebanpet.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import pt.project.rebanpet.databinding.FragmentHistoricalBinding
import pt.project.rebanpet.report.Report
import pt.project.rebanpet.report.ReportAdapter

class HistoricalFragment : Fragment() {

    lateinit var historicalBinding: FragmentHistoricalBinding

    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference : DatabaseReference = database.reference.child("MyReports")

    val reportList = ArrayList<Report>()
    lateinit var reportAdapter: ReportAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        historicalBinding = FragmentHistoricalBinding.inflate(inflater, container, false)
        val view = historicalBinding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

                myReference.child(id).removeValue()

                Toast.makeText(context,
                    "The user was deleted",
                    Toast.LENGTH_SHORT).show()

            }

        }).attachToRecyclerView(historicalBinding.recyclerView)

        retrieveDataFromDatabase()

    }

    fun retrieveDataFromDatabase(){

        myReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                reportList.clear()

                for(eachUser in snapshot.children){
                    val report = eachUser.getValue(Report::class.java)
                    if(report != null){
                        println("userId : ${report.reportId}")
                        println("userName : ${report.reportName}")
                        println("userAge : ${report.reportLocal}")
                        //println("userEmail : ${user.userEmail}")
                        println("**************************")

                        reportList.add(report)
                    }

                    reportAdapter = ReportAdapter(this@HistoricalFragment, reportList)
                    historicalBinding.recyclerView.layoutManager = LinearLayoutManager(context)
                    historicalBinding.recyclerView.adapter = reportAdapter

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}