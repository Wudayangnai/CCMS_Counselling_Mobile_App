package com.nibm.ccms_counselling_mobile_application

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ActivityResources : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resources)

        // Example resource buttons
        findViewById<Button>(R.id.btnResource1).setOnClickListener {
            openUrl("https://www.example.com/resource1.pdf")
        }
        findViewById<Button>(R.id.btnResource2).setOnClickListener {
            openUrl("https://www.example.com/resource2.pdf")
        }
    }

    private fun openUrl(url: String) {
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(i)
    }
}