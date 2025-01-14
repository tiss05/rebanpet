package pt.project.rebanpet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import androidx.navigation.fragment.findNavController
import pt.project.rebanpet.R
import pt.project.rebanpet.databinding.FragmentHistoricalBinding
import pt.project.rebanpet.report.Report
import pt.project.rebanpet.report.ReportAdapter
import pt.project.rebanpet.databinding.FragmentEmptyBinding

class EmptyHistoricalFragment : Fragment()  {

    lateinit var emptyHistoricalFragment: FragmentEmptyBinding
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
        emptyHistoricalFragment = FragmentEmptyBinding.inflate(inflater, container, false)
        val view = emptyHistoricalFragment.root
        return view
    }


}