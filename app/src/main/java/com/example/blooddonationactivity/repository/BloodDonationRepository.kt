package com.example.blooddonationactivity.repository



open class BloodDonationRepository {

    // Simulate sending request (replace with actual backend logic)
    fun sendRequest(requestMessage: String, callback: (Boolean, String) -> Unit) {
        // Simulate success or failure after "sending" the request
        if (requestMessage.isNotEmpty()) {
            callback(true, "Request sent successfully")
        } else {
            callback(false, "Request message is empty")
        }
    }
}
