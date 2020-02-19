package com.example.oblig1

import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.google.common.truth.Truth
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Author: Petter Knudsen
 */
@RunWith(AndroidJUnit4::class)
class QuizTest {

    @get:Rule
    val quizRule: ActivityTestRule<MainQuiz> = ActivityTestRule(MainQuiz::class.java)


    @Before
    fun onBefore() {
        Intents.init()
    }

    @After
    fun onAfter() {
        Intents.release()
    }

    @Test
    fun testQuiz() {


        for (i in 0 until quizRule.activity.quizItems.size) {
            onView(withId(R.id.quizEditText)).perform(typeText("petter"), closeSoftKeyboard())
            onView(withId(R.id.quizButton)).perform(click())
        }
        Truth.assertThat(quizRule.activity.score).isEqualTo(1)
    }


}