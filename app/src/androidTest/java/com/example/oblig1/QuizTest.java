package com.example.oblig1;



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
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class QuizTest {

    @Rule
    public ActivityTestRule<MainQuiz> quizRule = new ActivityTestRule<>(MainQuiz.class);

    @Before
    public void onBefore(){
        Intents.init();
    }
    @After
    public void onAfter(){
        Intents.release();
    }

    @Test
    public void testQuiz(){
        for(int i=0; i <quizRule.getActivity().quizItems.size(); i++){
            onView(withId(R.id.quizEditText)).perform(typeText("Petter"));
            onView(withId(R.id.quizButton)).perform(click());
        }
        Truth.assertThat(quizRule.getActivity().score).isEqualTo(0);
    }

}
