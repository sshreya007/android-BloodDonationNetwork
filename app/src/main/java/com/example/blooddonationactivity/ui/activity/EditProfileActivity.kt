package com.example.blooddonationactivity.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.blooddonationactivity.R
import com.example.blooddonationactivity.databinding.ActivityEditProfileBinding
import com.example.blooddonationactivity.repository.UserRepositoryImpl
import com.example.blooddonationactivity.viewmodel.UserViewModel

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        userViewModel = UserViewModel(UserRepositoryImpl())
        // Set click listener for save changes button
        binding.btnSaveChanges.setOnClickListener {
            val newPassword = binding.editTextNewPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else if (newPassword != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                // Call the method to update the password
                updatePassword(newPassword)
            }
        }
    }

    private fun updatePassword(newPassword: String) {
        userViewModel.getCurrentUser ()?.let { user ->
            user.updatePassword(newPassword).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show()
                    finish() // Close the activity
                } else {
                    Toast.makeText(this, "Failed to update password: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}