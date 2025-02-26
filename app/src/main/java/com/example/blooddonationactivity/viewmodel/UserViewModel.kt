package com.example.blooddonationactivity.viewmodel



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.blooddonationactivity.repository.UserRepository
import com.example.blooddonationactivity.repository.UserRepositoryImpl
import com.example.blooddonationactivity.model.UserModel
import com.google.firebase.auth.FirebaseUser

class UserViewModel : ViewModel() {

    private val userRepository: UserRepository = UserRepositoryImpl()

    val loginStatus = MutableLiveData<Resource<String>>()
    val signupStatus = MutableLiveData<Resource<String>>()
    val logoutStatus = MutableLiveData<Resource<String>>()

    fun login(email: String, password: String) {
        loginStatus.value = Resource.Loading()
        userRepository.login(email, password) { success, message ->
            if (success) {
                loginStatus.value = Resource.Success(message)
            } else {
                loginStatus.value = Resource.Error(message)
            }
        }
    }

    fun signup(email: String, password: String, username: String, bloodType: String) {
        signupStatus.value = Resource.Loading()
        userRepository.signup(email, password, username, bloodType) { success, message ->
            if (success) {
                signupStatus.value = Resource.Success(message)
            } else {
                signupStatus.value = Resource.Error(message)
            }
        }
    }

    fun logout() {
        logoutStatus.value = Resource.Loading()
        userRepository.logout { success, message ->
            if (success) {
                logoutStatus.value = Resource.Success(message)
            } else {
                logoutStatus.value = Resource.Error(message)
            }
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return userRepository.getCurrentUser()
    }

    // Helper class to handle different states (loading, success, error)
    sealed class Resource<T> {
        class Success<T>(val data: T) : Resource<T>()
        class Error<T>(val message: String) : Resource<T>()
        class Loading<T> : Resource<T>()
    }
}

