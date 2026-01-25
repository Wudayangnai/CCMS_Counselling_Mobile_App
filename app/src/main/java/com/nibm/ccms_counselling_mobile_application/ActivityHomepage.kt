package com.nibm.ccms_counselling_mobile_application

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class ActivityHomepage : AppCompatActivity() {

    private lateinit var tvWelcome: TextView
    private lateinit var tvUserEmail: TextView
    private lateinit var btnRegister: Button
    private lateinit var btnAppointment: Button
    private lateinit var btnPayment: Button
    private lateinit var btnResources: Button
    private lateinit var btnPsychometric: Button
    private lateinit var btnLogout: Button

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        // Bind views
        tvWelcome = findViewById(R.id.tvWelcome)
        tvUserEmail = findViewById(R.id.tvUserEmail)
        btnRegister = findViewById(R.id.btnRegister)
        btnAppointment = findViewById(R.id.btnAppointment)
        btnPayment = findViewById(R.id.btnPayment)
        btnResources = findViewById(R.id.btnResources)
        btnPsychometric = findViewById(R.id.btnPsychometric)
        btnLogout = findViewById(R.id.btnLogout)

        // Show signed-in user's email if available
        val user = auth.currentUser
        tvUserEmail.text = user?.email ?: "Guest"

        // Navigation handlers
        btnRegister.setOnClickListener {
            startActivity(Intent(this, ActivityRegister::class.java))
        }

        btnAppointment.setOnClickListener {
            startActivity(Intent(this, ActivityAppointment::class.java))
        }

        btnPayment.setOnClickListener {
            startActivity(Intent(this, ActivityPayment::class.java))
        }

        btnResources.setOnClickListener {
            startActivity(Intent(this, ActivityResources::class.java))
        }

        btnPsychometric.setOnClickListener {
            startActivity(Intent(this, ActivityPsychometric::class.java))
        }

        // Sign out and go back to login screen
        btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, ActivityLogin::class.java)
            // Clear activity stack so user can't press Back to return here
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}