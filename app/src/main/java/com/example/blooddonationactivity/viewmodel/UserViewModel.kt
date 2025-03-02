package com.example.blooddonationactivity.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.blooddonationactivity.repository.UserRepository
import com.example.blooddonationactivity.repository.UserRepositoryImpl
import com.example.blooddonationactivity.model.UserModel
import com.google.firebase.auth.FirebaseUser

class UserViewModel(val userRepository: UserRepositoryImpl) {


    fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        userRepository.login(email, password, callback)
    }

    fun signup(
        email: String,
        password: String,
        username: String,
        bloodType: String,
        callback: (Boolean, String) -> Unit
    ) {
        userRepository.signup(email, password, username, bloodType, callback)
    }


    fun getCurrentUser(): FirebaseUser? {
        return userRepository.getCurrentUser()
    }

    fun logout(callback: (Boolean, String) -> Unit) {
        userRepository.logout(callback)
    }


}

