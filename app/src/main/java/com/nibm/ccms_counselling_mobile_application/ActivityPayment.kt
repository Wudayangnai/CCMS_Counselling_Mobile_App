package com.nibm.ccms_counselling_mobile_application

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActivityPayment : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etCardNumber: EditText
    private lateinit var etExpiry: EditText
    private lateinit var etCvv: EditText
    private lateinit var etAmount: EditText
    private lateinit var btnPay: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        etName = findViewById(R.id.etName)
        etCardNumber = findViewById(R.id.etCardNumber)
        etExpiry = findViewById(R.id.etExpiry)
        etCvv = findViewById(R.id.etCvv)
        etAmount = findViewById(R.id.etAmount)
        btnPay = findViewById(R.id.btnPay)

        btnPay.setOnClickListener {
            val name = etName.text.toString().trim()
            val card = etCardNumber.text.toString().trim()
            val exp = etExpiry.text.toString().trim()
            val cvv = etCvv.text.toString().trim()
            val amount = etAmount.text.toString().trim()

            if (name.isEmpty() || card.length < 12 || exp.isEmpty() || cvv.length < 3 || amount.isEmpty()) {
                Toast.makeText(this, "Please complete valid payment details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Note: This is a simulated payment form. Integrate a payment SDK (Stripe/PayPal/Razorpay) for real payments.
            Toast.makeText(this, "Payment of $amount processed (simulated)", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}