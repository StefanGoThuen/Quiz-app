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
    val quizRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    private val idlingResource = DownloadAsyncIdlingResource()

    @Before
    fun onBefore() {
        Intents.init()
    }

    @After
    fun onAfter() {
        IdlingRegistry.getInstance().unregister(idlingResource)
        Intents.release()
    }

    @Test
    fun testQuiz() {


        IdlingRegistry.getInstance().register(idlingResource)

        try {
            onView(withText("QUIZ")).perform(click())
        } catch (e: NoMatchingViewException) {
            onView(withId(R.id.ownerDialogEditText)).perform(typeText("Petter"), closeSoftKeyboard())
            onView(withId(R.id.ownerDialogButton)).perform(click())
        }
        val quizActivity = getActivityInstance() as MainQuiz
        for (i in 0 until quizActivity.quizItems.size) {
            onView(withId(R.id.quizEditText)).perform(typeText("petter"), closeSoftKeyboard())
            onView(withId(R.id.quizButton)).perform(click())
        }
        Truth.assertThat(quizActivity.score).isEqualTo(1)
    }

    private fun getActivityInstance(): Activity {
        val currentActivity = ArrayList<Activity>()

        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            run {
                val resumedActivity = ActivityLifecycleMonitorRegistry.getInstance()
                        .getActivitiesInStage(Stage.RESUMED)
                val it = resumedActivity.iterator()
                currentActivity.add(it.next())
            }
        }

        return currentActivity[0]
    }
}