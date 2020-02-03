package com.example.oblig1

import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.withId
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
class AddDataTest {

    @get:Rule
    var mRuntimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

    @get:Rule
    val addRule: ActivityTestRule<MainAdd> = ActivityTestRule(MainAdd::class.java)


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
    fun cameraIntent() {
        // Check that the ImageView doesn't have a drawable applied.
        Truth.assertThat(addRule.activity.imageView.drawable).isEqualTo(null)
        // Click on the button that will trigger the stubbed intent.
        onView(withId(R.id.picBtn)).perform(click())
        // With no user interaction, the ImageView will have a drawable.
        Truth.assertThat(addRule.activity.imageView.drawable).isNotEqualTo(null)
    }



    //bad code but had to be done this way for this particular UI test
    @Test
    fun addDataThenDelete() {
        val pref = addRule.activity.getSharedPreferences("names", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("_jesus", "_jesus")
        editor.apply()

        val i1 = BitmapFactory.decodeResource(addRule.activity.resources,
                R.drawable.ole)
        ImageHandler.saveBitmapToFile(addRule.activity, "_jesus", i1)
        onView(withId(R.id.editText)).perform(typeText("jesus"), closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        addRule.activity.startActivity(Intent(addRule.activity, MainData::class.java))
        //Never use Thread.sleep, use an IdlingResource instead. This is just me being lazy
        Thread.sleep(2000)
        val activity = getActivityInstance() as MainData
        val dataSize = activity.dataItems.size
        val recyclerView = onView(withId(R.id.dataRecyclerView))
        recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, longClick()))
        Truth.assertThat(activity.dataItems.size).isLessThan(dataSize)
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

    private fun createImageCaptureActivityResultStub(): Instrumentation.ActivityResult {
        // Put the drawable in a bundle.
        val bundle = Bundle()
        bundle.putParcelable("test", BitmapFactory.decodeResource(
                addRule.activity.resources, R.drawable.jostein));

        // Create the Intent that will include the bundle.
        val resultData = Intent()
        resultData.putExtras(bundle)

        // Create the ActivityResult with the Intent.
        return Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
    }
}