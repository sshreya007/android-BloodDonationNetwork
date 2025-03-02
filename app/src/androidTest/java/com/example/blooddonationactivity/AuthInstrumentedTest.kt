package com.example.blooddonationactivity

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.blooddonationactivity.ui.activity.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(AndroidJUnit4::class)
class AuthInstrumentedTest {
    @get:Rule
    val testRule = ActivityScenarioRule(LoginActivity::class.java)
    @Test
    fun checklogin() {
        onView(withId(R.id.Email)).perform(
            typeText("shreya@gmail.com")
        )

        onView(withId(R.id.passwordl)).perform(
            typeText("password")
        )

        closeSoftKeyboard()

        Thread.sleep(1500)

        onView(withId(R.id.buttonlogin)).perform(
            click()
        )

        Thread.sleep(1500)
        onView(withId(R.id.displayLoginResult)).check(matches(withText("loginfailed")))
    }

}