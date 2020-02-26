package com.example.oblig1

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.google.common.truth.Truth
import kotlinx.android.synthetic.main.activity_add.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Author: Petter Knudsen
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    @get:Rule
    var mRuntimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

    @get:Rule
    val addRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)


    @Before
    fun onBefore() {
        Intents.init()

        val result = createImageCaptureActivityResultStub()
        intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(result)
    }

    @After
    fun onAfter() {
        Intents.release()
    }

    @Test
    fun addImageThenDelete() {
        try {
            onView(withText("ADD")).perform(click())
        } catch (e: NoMatchingViewException) {
            onView(withId(R.id.ownerDialogEditText)).perform(typeText("Petter"), closeSoftKeyboard())
            onView(withId(R.id.ownerDialogButton)).perform(click())
            onView(withText("ADD")).perform(click())
        }

        val addActivity = getActivityInstance() as MainAdd

        // Check that the ImageView doesn't have a drawable applied.
        Truth.assertThat(addActivity.imageView.drawable).isEqualTo(null)
        // Click on the button that will trigger the stubbed intent.
        onView(withId(R.id.picBtn)).perform(click())
        // With no user interaction, the ImageView will have a drawable.
        Truth.assertThat(addActivity.imageView.drawable).isNotEqualTo(null)
        onView(withId(R.id.editText)).perform(typeText("jesus"), closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        Espresso.pressBackUnconditionally()
        onView(withText("DATABASE")).perform(click())
        val dataSize = MainActivity.databaseItems.size
        val recyclerView = onView(withId(R.id.dataRecyclerView))
        recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, longClick()))
        Truth.assertThat(MainActivity.databaseItems.size).isLessThan(dataSize)
    }

    private fun createImageCaptureActivityResultStub(): Instrumentation.ActivityResult {
        // Put the drawable in a bundle.
        val bundle = Bundle()
        bundle.putParcelable("data", ImageHandler.scaledImage(BitmapFactory.decodeResource(
                addRule.activity.resources, R.drawable.jostein)))

        // Create the Intent that will include the bundle.
        val resultData = Intent()
        resultData.putExtras(bundle)

        // Create the ActivityResult with the Intent.
        return Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
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