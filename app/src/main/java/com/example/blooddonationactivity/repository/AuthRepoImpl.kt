package com.example.blooddonationactivity.repository

import com.google.firebase.auth.FirebaseAuth

class AuthRepoImpl (var auth: FirebaseAuth):AuthRepo {

    override fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, "Login successful")
                } else {
                    callback(false, task.exception?.message ?: "Login failed")
                }
            }
    }
}
