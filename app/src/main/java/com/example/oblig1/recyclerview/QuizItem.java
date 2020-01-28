package com.example.oblig1.recyclerview;

import android.graphics.Bitmap;

public class QuizItem {
    private String correctAnswer;
    private Bitmap image;

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public QuizItem(String correctAnswer, Bitmap image){
        this.correctAnswer = correctAnswer;
        this.image = image;
    }
}
