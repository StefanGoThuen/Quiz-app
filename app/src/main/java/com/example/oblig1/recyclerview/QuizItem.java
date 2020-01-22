package com.example.oblig1.recyclerview;

import android.graphics.drawable.Drawable;

public class QuizItem {
    private String correctAnswer;
    private Drawable image;

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public QuizItem(String correctAnswer, Drawable image){
        this.correctAnswer = correctAnswer;
        this.image = image;
    }
}
