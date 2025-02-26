package com.example.blooddonationactivity.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.blooddonationactivity.databinding.ActivityRequestBloodBinding
import com.example.blooddonationactivity.viewmodel.BloodDonationViewModel

class RequestBloodActivity : AppCompatActivity() {

    // Declare a reference to the binding object
    private lateinit var binding: ActivityRequestBloodBinding

    // Initialize ViewModel
    private val bloodDonationViewModel: BloodDonationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityRequestBloodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up click listener for the "Send Request" button
        binding.buttonRequestBlood.setOnClickListener {
            val requestMessage = binding.textView.text.toString()

            if (requestMessage.isNotEmpty()) {
                // Call ViewModel to send the blood donation request
                bloodDonationViewModel.sendBloodDonationRequest(requestMessage)

                // Observe the result from the ViewModel (success or error message)
                bloodDonationViewModel.requestResult.observe(this, { resultMessage ->
                    Toast.makeText(this, resultMessage, Toast.LENGTH_SHORT).show()

                    // Optionally, clear the input field after sending
                    binding.textView.text.clear()
                })
            } else {
                // Show a toast if the input is empty
                Toast.makeText(this, "Please enter a request", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
