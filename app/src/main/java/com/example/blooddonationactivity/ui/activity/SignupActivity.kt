package com.example.blooddonationactivity.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blooddonationactivity.R
import com.example.blooddonationactivity.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize FirebaseAuth and Firestore
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

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
                // Log the email and password for debugging
                Log.d("SignupActivity", "Email: $email, Password: $password")

                // Create a new user with Firebase Authentication
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // User created successfully
                            val user = auth.currentUser

                            // Optionally store additional user info in Firestore
                            val userData = hashMapOf(
                                "username" to username,
                                "bloodType" to bloodType
                            )
                        }
                    }
            }
        }
    }
}

// Store user