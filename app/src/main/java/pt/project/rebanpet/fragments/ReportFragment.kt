package pt.project.rebanpet.fragments

import android.content.Intent
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth
import pt.project.rebanpet.R
import pt.project.rebanpet.databinding.FragmentReportBinding
import pt.project.rebanpet.report.Report
import pt.project.rebanpet.databinding.CustomAlertDialogBinding
import java.text.SimpleDateFormat
import java.util.Date
import android.Manifest
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.Priority
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.Locale
import android.os.Build
import android.provider.MediaStore


class ReportFragment : Fragment() {

    lateinit var reportBinding : FragmentReportBinding
    val auth : FirebaseAuth = FirebaseAuth.getInstance()
    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myReference : DatabaseReference = database.reference.child("reports")
    lateinit var mRef : DatabaseReference
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val storageRef = FirebaseStorage.getInstance().reference

    private lateinit var storageRefe : StorageReference
    private lateinit var firebaseRef : DatabaseReference
    private var photoUri: Uri? = null

    companion object {
        private val PERMISSION_CODE = 1001;
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        reportBinding = FragmentReportBinding.inflate(inflater, container, false)
        val view = reportBinding.root

        val currentUser = auth.currentUser
        if (currentUser != null) {
            firebaseRef = FirebaseDatabase.getInstance().getReference("reports/${currentUser.uid}")
            storageRefe = FirebaseStorage.getInstance().getReference("images/${currentUser.uid}")
        }



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        reportBinding.buttonGetLocation.setOnClickListener {
            getLocation()
        }

        reportBinding.buttonPhotoAnimal.setOnClickListener {
            requestGalleryPermission()
        }

        reportBinding.buttonAddUser.setOnClickListener {
            addReportToDatabase()
        }
    }

    // LOCATION
    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            return
        }

        val locationTask: Task<Location> = fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
        locationTask.addOnSuccessListener { location ->
            if (location != null) {
                getAddressFromLocation(location)
            } else {
                reportBinding.contentLocalAnimal.text =
                    "Tente novamente! Verifique se a localização está ativada."
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "Falha na localização", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getAddressFromLocation(location: Location) {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        try {
            val addresses: List<Address>? = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            if (addresses != null && addresses.isNotEmpty()) {
                val address = addresses[0]
                reportBinding.contentLocalAnimal.text = address.getAddressLine(0)
            } else {
                reportBinding.contentLocalAnimal.text = "Localização não encontrada"
            }
        } catch (e: IOException) {
            e.printStackTrace()
            reportBinding.contentLocalAnimal.text = "Erro na procura de localização"
        }
    }


    // PHOTO GALLERY
    private fun requestGalleryPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // For Android 13+
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_MEDIA_IMAGES
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                openGallery()
            } else {
                val permissions = arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
                requestPermissions(permissions, PERMISSION_CODE)
            }
        } else {
            // For Android 12 and below
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                openGallery()
            } else {
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permissions, PERMISSION_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openGallery()
                }else{
                    Toast.makeText(context,"Permissão negada", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val openGalleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        reportBinding.photoAnimal.setImageURI(it)
        if (it != null){
            photoUri = it
        } else {
            Toast.makeText(context,"Nenhuma imagem selecionada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openGallery() {
        openGalleryLauncher.launch("image/*")
    }


    // ADD REPORT
    private fun addReportToDatabase(){
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm")

        val descriptionAnimal : String = reportBinding.contentDescriptionAnimal.text.toString()
        val localAnimal : String = reportBinding.contentLocalAnimal.text.toString()
        val currentDateTime = sdf.format(Date())

        //val id : String = myReference.push().key.toString()
        val reportId = firebaseRef.push().key!!
        var report : Report


        if (descriptionAnimal.isNotEmpty() && localAnimal.isNotEmpty() && photoUri != null) {
            photoUri?.let{
                storageRefe.child(reportId).putFile(it)
                    .addOnSuccessListener { task ->
                        task.metadata!!.reference!!.downloadUrl
                            .addOnSuccessListener { url ->
                                val imgUrl = url.toString()

                                report = Report(
                                    reportId,
                                    descriptionAnimal,
                                    localAnimal,
                                    currentDateTime,
                                    imgUrl
                                )

                                firebaseRef.child(reportId).setValue(report)
                                    .addOnCompleteListener { task ->

                                        if (task.isSuccessful) {
                                            clearInputs()
                                            showDialogMessage()
                                        }
                                    }
                                    .addOnFailureListener { error ->
                                        Toast.makeText(
                                            context,
                                            "erro: ${error.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                            }
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
        reportBinding.contentDescriptionAnimal.text.clear()
        reportBinding.contentLocalAnimal.text=""
        reportBinding.photoAnimal.visibility = View.VISIBLE
    }

    private fun showDialogMessage() {

        val dialogBinding = CustomAlertDialogBinding.inflate(LayoutInflater.from(requireContext()))

        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .setCancelable(false)
            .create()

        dialogBinding.dialogTitle.text = "Denúncia concluída!"
        dialogBinding.dialogMessage.text = "Adicionada no histórico de denúncias"

        dialogBinding.btnDone.setOnClickListener { //dialog, _ ->
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentReport, HistoricalFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            alertDialog.dismiss()
        }

        /*alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel") { dialog, _ ->
            dialog.dismiss()
        }*/

        alertDialog.show()
    }
}