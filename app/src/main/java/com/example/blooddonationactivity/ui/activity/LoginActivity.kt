package com.example.blooddonationactivity.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.blooddonationactivity.R
import com.example.blooddonationactivity.databinding.ActivityLoginBinding
import com.example.blooddonationactivity.repository.UserRepositoryImpl
import com.example.blooddonationactivity.viewmodel.UserViewModel
import com.google.firebase.FirebaseApp

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    lateinit var userViewModel: UserViewModel
    // Use viewModels delegate to get the ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        userViewModel = UserViewModel(UserRepositoryImpl())

        // Handle login button click
        binding.buttonlogin.setOnClickListener {
            val email = binding.Email.text.toString().trim()
            val password = binding.passwordl.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()


            } else {
                binding.displayLoginResult.text="login failed"
                binding.displayLoginResult.visibility= View.GONE
                userViewModel.login(email, password){
                    success,message->
                    if(success){
                        val intent = Intent(this@LoginActivity,HomeActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
                    }
                } // Call the login method in ViewModel
            }
        }
    }
}