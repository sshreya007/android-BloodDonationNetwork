package com.example.blooddonationactivity.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.blooddonationactivity.R
import com.example.blooddonationactivity.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Request Blood Button Click
        binding.buttonRequest.setOnClickListener {
            // Navigate to Request Blood Activity
            val intent = Intent(this, RequestBloodActivity::class.java)
            startActivity(intent)
        }

        // Events Button Click
        binding.buttonEvent.setOnClickListener {
            // Navigate to Events Activity
            val intent = Intent(this, EventsActivity::class.java)
            startActivity(intent)
        }

        // Blood Stock Button Click
        binding.buttonBloodStock.setOnClickListener {
            // Navigate to Blood Stock Activity
            val intent = Intent(this, BloodStockActivity::class.java)
            startActivity(intent)
        }

        // Profile Button Click
        binding.buttonProfile.setOnClickListener {
            // Navigate to Profile Activity
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
