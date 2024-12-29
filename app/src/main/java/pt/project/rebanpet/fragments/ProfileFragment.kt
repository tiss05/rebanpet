package pt.project.rebanpet.fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import pt.project.rebanpet.MainActivity
import pt.project.rebanpet.R
import pt.project.rebanpet.databinding.FragmentProfileBinding
import pt.project.rebanpet.databinding.FragmentReportBinding

class ProfileFragment : Fragment() {

    lateinit var profileBinding : FragmentProfileBinding
    lateinit var auth : FirebaseAuth
    lateinit var databaseReference: DatabaseReference
    //lateinit var storageReference: StorageReference
    lateinit var dialog : Dialog
    //lateinit var user : User
    lateinit var uid : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        profileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = profileBinding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        //uid = auth.currentUser?.uid.toString()

        val user = auth.currentUser

        if(user != null){

            val uid = user.uid
            val name = user.displayName
            val email = user.email

            profileBinding.contentNameProfile.text = name
            profileBinding.contentEmailProfile.text = email
            /*profileBinding.contentContactProfile.text=email
            profileBinding.contentLocalProfile.text=uid*/

        }
    }
}