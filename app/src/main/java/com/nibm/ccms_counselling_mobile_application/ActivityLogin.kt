package com.nibm.ccms_counselling_mobile_application

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActivityLogin : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnGoRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Bind views
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnGoRegister = findViewById(R.id.btnGoRegister)

        // Login button click
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty()) {
                etUsername.error = "Username required"
                etUsername.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                etPassword.error = "Password required"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            // Temporary login check (replace with Firebase / API later)
            if (username == "admin" && password == "1234") {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

                // Example: go to dashboard
                // val intent = Intent(this, DashboardActivity::class.java)
                // startActivity(intent)
                // finish()

            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }

        // Go to Register button click
        btnGoRegister.setOnClickListener {
            // Example navigation
            // val intent = Intent(this, ActivityRegister::class.java)
            // startActivity(intent)

            Toast.makeText(this, "Go to Register Screen", Toast.LENGTH_SHORT).show()
        }
    }
}
