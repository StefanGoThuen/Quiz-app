package com.example.oblig1

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.google.common.truth.Truth
import kotlinx.android.synthetic.main.activity_add.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

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
    fun takePicture() {
        // Check that the ImageView doesn't have a drawable applied.
        Truth.assertThat(addRule.activity.imageView.drawable).isEqualTo(null)

        // Click on the button that will trigger the stubbed intent.
        onView(withId(R.id.picBtn)).perform(click());

        // With no user interaction, the ImageView will have a drawable.
        Truth.assertThat(addRule.activity.imageView.drawable).isNotEqualTo(null)
        onView(withId(R.id.editText)).perform(typeText("smile"), closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
    }




    private fun createImageCaptureActivityResultStub() : Instrumentation.ActivityResult {
    // Put the drawable in a bundle.
    val bundle =  Bundle()
    bundle.putParcelable("test", BitmapFactory.decodeResource(
            addRule.activity.resources, R.drawable.jostein));

    // Create the Intent that will include the bundle.
    val resultData =  Intent()
    resultData.putExtras(bundle)

    // Create the ActivityResult with the Intent.
    return Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
}
}