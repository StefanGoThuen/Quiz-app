package com.example.oblig1

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBackUnconditionally
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.oblig1.MainActivity.databaseItems
import com.google.common.truth.Truth
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeleteDataTest {

    @get:Rule
    val addRule: ActivityTestRule<SplashActivity> = ActivityTestRule(SplashActivity::class.java, false, true)

    private val downloadAsyncIdlingResource = DownloadAsyncIdlingResource()
    private val saveImageIdlingResource = SaveImagIdlingResource()
    @Before
    fun onBefore() {
        val pref = addRule.activity.getSharedPreferences("names", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("_jesus", "_jesus")
        editor.apply()
        val i1 = BitmapFactory.decodeResource(addRule.activity.resources,
                R.drawable.ole)
        ImageHandler.saveBitmapToFile(addRule.activity, "_jesus", i1)
        Intents.init()
    }

    @After
    fun onAfter() {
        IdlingRegistry.getInstance().unregister(downloadAsyncIdlingResource)
        IdlingRegistry.getInstance().unregister(saveImageIdlingResource)
        Intents.release()
    }

    //bad code but had to be done this way for this particular UI test
    @Test
    fun addDataThenDelete() {

        Thread.sleep(3000)
        addRule.activity.startActivity(Intent(addRule.activity, MainActivity::class.java))
        //Let stuff save/load
        onView(withText("DATABASE")).perform(click())
        val dataSize = databaseItems.size
        val recyclerView = onView(withId(R.id.dataRecyclerView))
        recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.longClick()))
        Truth.assertThat(databaseItems.size).isLessThan(dataSize)
    }


    /* private fun getActivityInstance(): Activity {
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
     }*/
}