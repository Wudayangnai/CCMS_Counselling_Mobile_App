package com.nibm.ccms_counselling_mobile_application

import android.content.Intent
import android.os.Bundle
import android.view.View
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

        // Keep edge-to-edge padding handling
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Start button opens the Login activity and makes this activity "invisible" by finishing it.
        val btnStart = findViewById<Button>(R.id.button)
        btnStart.setOnClickListener {
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)

            // Option 1 (recommended): finish this activity so it is removed and not visible
            finish()

            // Option 2 (alternative): if you truly want to keep the activity but hide its UI,
            // uncomment the next line instead of calling finish().
            // findViewById<View>(R.id.main).visibility = View.GONE
        }
    }
}