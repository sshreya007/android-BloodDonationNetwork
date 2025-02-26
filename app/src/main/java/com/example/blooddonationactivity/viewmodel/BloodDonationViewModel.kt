package com.example.blooddonationactivity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blooddonationactivity.repository.BloodDonationRepository

class BloodDonationViewModel : ViewModel() {

    private val _requestResult = MutableLiveData<String>()
    val requestResult: LiveData<String> get() = _requestResult

    private val bloodDonationRepository = BloodDonationRepository()

    // Function to send a blood donation request
    fun sendBloodDonationRequest(requestMessage: String) {
        // Call the repository function to send the request
        bloodDonationRepository.sendRequest(requestMessage) { success, message ->
            if (success) {
                _requestResult.value = "Request sent successfully"
            } else {
                _requestResult.value = "Failed to send request: $message"
            }
        }
    }
}
