package com.example.blooddonationactivity.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.blooddonationactivity.R
import com.example.blooddonationactivity.databinding.ActivityLoginBinding
import com.example.blooddonationactivity.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: UserViewModel by viewModels() // Use viewModels delegate to get the ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe login status
        viewModel.loginStatus.observe(this, Observer { resource ->
            when (resource) {
                is UserViewModel.Resource.Loading -> {
                    // Optionally show a loading indicator
                }
                is UserViewModel.Resource.Success -> {
                    Toast.makeText(this, resource.data, Toast.LENGTH_SHORT).show()
                    // Navigate to HomeActivity
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
                is UserViewModel.Resource.Error -> {
                    Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        // Handle login button click
        binding.buttonlogin.setOnClickListener {
            val email = binding.Email.text.toString().trim()
            val password = binding.passwordl.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.login(email, password) // Call the login method in ViewModel
            }
        }
    }
}