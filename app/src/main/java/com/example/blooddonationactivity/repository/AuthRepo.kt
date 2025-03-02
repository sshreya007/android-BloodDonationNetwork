package com.example.blooddonationactivity.repository

interface AuthRepo {

    fun login(email: String, password: String, callback: (Boolean, String) -> Unit)
}