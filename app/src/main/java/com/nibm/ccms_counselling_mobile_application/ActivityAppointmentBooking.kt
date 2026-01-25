package com.nibm.ccms_counselling_mobile_application

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class ActivityAppointment : AppCompatActivity() {

    private lateinit var tvLogo: TextView
    private lateinit var etName: EditText
    private lateinit var tvDate: TextView
    private lateinit var tvTime: TextView
    private lateinit var etReason: EditText
    private lateinit var btnBook: Button

    private var selectedDate = ""
    private var selectedTime = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment)

        etName = findViewById(R.id.etName)
        tvDate = findViewById(R.id.tvDate)
        tvTime = findViewById(R.id.tvTime)
        etReason = findViewById(R.id.etReason)
        btnBook = findViewById(R.id.btnBook)

        tvDate.setOnClickListener { showDatePicker() }
        tvTime.setOnClickListener { showTimePicker() }

        btnBook.setOnClickListener {
            val name = etName.text.toString().trim()
            val reason = etReason.text.toString().trim()

            if (name.isEmpty()) {
                etName.error = "Name required"
                etName.requestFocus()
                return@setOnClickListener
            }
            if (selectedDate.isEmpty()) {
                Toast.makeText(this, "Select appointment date", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (selectedTime.isEmpty()) {
                Toast.makeText(this, "Select appointment time", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // TODO: save appointment to remote DB (Firestore) or your backend
            Toast.makeText(this, "Appointment booked for $selectedDate at $selectedTime", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun showDatePicker() {
        val c = Calendar.getInstance()
        DatePickerDialog(this, { _, year, month, dayOfMonth ->
            selectedDate = "%04d-%02d-%02d".format(year, month + 1, dayOfMonth)
            tvDate.text = selectedDate
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun showTimePicker() {
        val c = Calendar.getInstance()
        TimePickerDialog(this, { _, hourOfDay, minute ->
            selectedTime = "%02d:%02d".format(hourOfDay, minute)
            tvTime.text = selectedTime
        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show()
    }
}