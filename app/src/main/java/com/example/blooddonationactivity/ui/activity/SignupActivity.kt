package com.example.blooddonationactivity.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.blooddonationactivity.R
import com.example.blooddonationactivity.databinding.ActivitySignupBinding
import com.example.blooddonationactivity.viewmodel.UserViewModel

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val viewModel: UserViewModel by viewModels() // Use viewModels delegate to get the ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe signup status
        viewModel.signupStatus.observe(this, Observer { resource ->
            when (resource) {
                is UserViewModel.Resource.Loading -> {
                    // Optionally show a loading indicator
                }
                is UserViewModel.Resource.Success -> {
                    Toast.makeText(this, resource.data, Toast.LENGTH_SHORT).show()
                    // Navigate to LoginActivity or HomeActivity
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                is UserViewModel.Resource.Error -> {
                    Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        // Set a click listener for the signup button
        binding.signupButton.setOnClickListener {
            val username = binding.signupUsername.text.toString().trim()
            val bloodType = binding.signupBloodType.text.toString().trim()
            val email = binding.signupEmail.text.toString().trim()
            val password = binding.signupPassword.text.toString().trim()

            // Validate the input fields
            if (username.isEmpty() || bloodType.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.signup(email, password, username, bloodType) // Call the signup method in ViewModel
            }
        }
    }
}