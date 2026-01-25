package com.nibm.ccms_counselling_mobile_application

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ActivityPsychometric : AppCompatActivity() {

    private lateinit var btnSelect: Button
    private lateinit var tvFile: TextView
    private lateinit var btnUpload: Button

    private var fileUri: Uri? = null
    private lateinit var storage: FirebaseStorage

    private val REQUEST_PICK_FILE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_psychometric)

        btnSelect = findViewById(R.id.btnSelect)
        tvFile = findViewById(R.id.tvFile)
        btnUpload = findViewById(R.id.btnUpload)

        storage = Firebase.storage

        btnSelect.setOnClickListener {
            val i = Intent(Intent.ACTION_GET_CONTENT)
            i.type = "*/*"
            i.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(Intent.createChooser(i, "Select assessment file"), REQUEST_PICK_FILE)
        }

        btnUpload.setOnClickListener {
            val uri = fileUri
            if (uri == null) {
                Toast.makeText(this, "Select a file first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            uploadFile(uri)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_PICK_FILE && resultCode == Activity.RESULT_OK) {
            fileUri = data?.data
            tvFile.text = fileUri?.lastPathSegment ?: "selected file"
        }
    }

    private fun uploadFile(uri: Uri) {
        val df = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US)
        val name = df.format(Date())
        val filename = "psychometric_${name}"
        val ref = storage.reference.child("psychometric_uploads/$filename")

        btnUpload.isEnabled = false
        ref.putFile(uri)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener { downloadUrl ->
                    btnUpload.isEnabled = true
                    Toast.makeText(this, "Uploaded successfully", Toast.LENGTH_SHORT).show()
                    // You can save the downloadUrl.toString() to Firestore under user profile
                    finish()
                }
            }
            .addOnFailureListener { e ->
                btnUpload.isEnabled = true
                Toast.makeText(this, "Upload failed: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
    }
}