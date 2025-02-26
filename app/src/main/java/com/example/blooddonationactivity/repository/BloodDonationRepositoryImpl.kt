package com.example.blooddonationactivity.repository


import com.example.blooddonationactivity.model.BloodDonationRequest
import com.google.firebase.firestore.FirebaseFirestore

class BloodDonationRepositoryImpl : BloodDonationRepository() {

    private val db = FirebaseFirestore.getInstance()

    // Add new blood donation request to Firestore
    fun createBloodDonationRequest(
        request: BloodDonationRequest,
        callback: (Boolean, String) -> Unit
    ) {
        val requestRef = db.collection("blood_donation_requests").document()

        // Saving the request in Firestore
        requestRef.set(request)
            .addOnSuccessListener {
                callback(true, "Request sent successfully!")
            }
            .addOnFailureListener { e ->
                callback(false, "Error sending request: ${e.message}")
            }
    }
}
