package com.nibm.ccms_counselling_mobile_application

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Apply edge-to-edge padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Start button opens the Login activity
        val btnStart = findViewById<Button>(R.id.button)
        btnStart.setOnClickListener {
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)

            // Remove MainActivity so it becomes invisible / not on back stack.
            // If you want to return to MainActivity with Back pressed, remove this line.
            finish()
        }
    }
}