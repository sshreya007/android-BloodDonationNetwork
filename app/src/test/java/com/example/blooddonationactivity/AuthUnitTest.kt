package com.example.blooddonationactivity


import com.example.blooddonationactivity.repository.AuthRepo
import com.example.blooddonationactivity.repository.AuthRepoImpl
import com.example.blooddonationactivity.repository.UserRepositoryImpl
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.any
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class AuthUnitTest {
    @Mock
    private lateinit var mockAuth: FirebaseAuth

    @Mock
    private lateinit var mockTask: Task<AuthResult>

    private lateinit var AuthRepo: AuthRepoImpl

    @Captor
    private lateinit var captor: ArgumentCaptor<OnCompleteListener<AuthResult>>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        AuthRepo = AuthRepoImpl(mockAuth)
    }
    @Test
    fun testLogin_Successful() {
        val email = "test@example.com"
        val password = "testPassword"
        var expectedResult = "Initial Value" // Define the initial value

        // Mocking task to simulate successful registration
        `when`(mockTask.isSuccessful).thenReturn(true)
        `when`(mockAuth.signInWithEmailAndPassword(any(), any()))
            .thenReturn(mockTask)

        // Define a callback that updates the expectedResult
        val callback = { success: Boolean, message: String? ->
            expectedResult = message ?: "Callback message is null"
        }

        // Call the function under test
        AuthRepo.login(email, password, callback)

        verify(mockTask).addOnCompleteListener(captor.capture())
        captor.value.onComplete(mockTask)

        // Assert the result
        assertEquals("Login successful", expectedResult)
    }
}