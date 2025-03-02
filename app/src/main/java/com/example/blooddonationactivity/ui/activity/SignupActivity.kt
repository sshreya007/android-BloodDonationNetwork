package com.example.blooddonationactivity.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.blooddonationactivity.R
import com.example.blooddonationactivity.databinding.ActivitySignupBinding
import com.example.blooddonationactivity.repository.UserRepositoryImpl
import com.example.blooddonationactivity.viewmodel.UserViewModel

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = UserViewModel(UserRepositoryImpl())


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
                userViewModel.signup(email, password, username, bloodType){
                    success,message->
                    if(success){
                        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
                        finish()

                    }else{
                        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
                    }
                } // Call the signup method in ViewModel
            }
        }
    }
}