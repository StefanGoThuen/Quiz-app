package com.example.oblig1;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.google.common.truth.Truth;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RemoveImageTest {
    int numberOfPictures;
    @Rule
    public ActivityTestRule<MainData> quizRule = new ActivityTestRule<>(MainData.class);

    @Before
    public void onBefore(){
        Intents.init();
        numberOfPictures = quizRule.getActivity().adapter.getItemCount();

    }
    @After
    public void onAfter(){
        Intents.release();
    }

    @Test
    public void testQuiz(){
        onView(withId(R.id.dataRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));
        Truth.assertThat(quizRule.getActivity().adapter.getItemCount()).isEqualTo(numberOfPictures -1);
    }
}
