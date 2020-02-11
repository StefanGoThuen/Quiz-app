package com.example.oblig1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTester {
    @Test
    public void scoreUpdateisCorrect() {
        MainQuiz quiz = new MainQuiz();
        quiz.initTesting();
        String answer = quiz.getCurrentAnswer();
        quiz.answerQuiz(answer);
        String wrongAnswer = "eple";
        quiz.answerQuiz(wrongAnswer);
        assertEquals(1, quiz.getScore());
    }
    public void nrPicturesUpdate(){

    }

}