package com.example.blooddonationactivity.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.blooddonationactivity.R
import com.example.blooddonationactivity.databinding.ActivityProfileBinding
import com.example.blooddonationactivity.repository.UserRepositoryImpl
import com.example.blooddonationactivity.viewmodel.UserViewModel

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        userViewModel = UserViewModel(UserRepositoryImpl())

        // Set click listener for logout button
        binding.btnlogout.setOnClickListener {
            logout()
        }

        // Set click listener for edit profile button
        binding.btneditprofile.setOnClickListener {
            editProfile()
        }
    }

    private fun logout() {
        userViewModel.logout{
            success,message-> if(success){
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
            // Navigate back to LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        } // Call the logout method in ViewModel

    }

    private fun editProfile() {
        // Navigate to EditProfileActivity (you need to create this activity)
        startActivity(Intent(this, EditProfileActivity::class.java))
    }
}