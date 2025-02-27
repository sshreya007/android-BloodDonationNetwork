package com.example.blooddonationactivity.repository

import com.example.blooddonationactivity.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class UserRepositoryImpl : UserRepository { // Correct implementation of UserRepository interface
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

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

    override fun signup(email: String, password: String, username: String, bloodType: String, callback: (Boolean, String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: ""
                    // Create the user model with the necessary fields
                    val userModel = UserModel(email, username, bloodType, password)
                    addUserToDatabase(userId,userModel, callback)
                } else {
                    callback(false, task.exception?.message ?: "Sign up failed")
                }
            }
    }


    override fun forgetPassword(email: String, callback: (Boolean, String) -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, "Password reset email sent")
                } else {
                    callback(false, task.exception?.message ?: "Failed to send password reset email")
                }
            }
    }

    override fun addUserToDatabase(
        userId: String,
        userModel : UserModel,
        callback: (Boolean, String) -> Unit
    ) {db.collection("users").document(userId)
        .set(userModel)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback(true, "User added to database")
            } else {
                callback(false, task.exception?.message ?: "Failed to add user to database")
            }
        }

    }



    override fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    override fun getUserFromDatabase(
        userId: String,
        callback: (UserModel?, Boolean, String) -> Unit
    ) {
        db.collection("users").document(userId).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.toObject(UserModel::class.java)
                    if (user != null) {
                        callback(user, true, "User fetched successfully")
                    } else {
                        callback(null, false, "User not found")
                    }
                } else {
                    callback(null, false, task.exception?.message ?: "Failed to fetch user data")
                }
            }
    }



    override fun logout(callback: (Boolean, String) -> Unit) {
        auth.signOut()
        callback(true, "Logged out successfully")
    }

    override fun editProfile(userId: String, data: MutableMap<String, Any>, callback: (Boolean, String) -> Unit) {
        db.collection("users").document(userId)
            .update(data)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, "Profile updated successfully")
                } else {
                    callback(false, task.exception?.message ?: "Failed to update profile")
                }
            }
    }
}
