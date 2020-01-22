package com.example.oblig1;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oblig1.recyclerview.QuizItem;
import com.example.oblig1.recyclerview.QuizRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class MainQuiz extends AppCompatActivity {
    int questionNumber = 0;
    int score = 0;
    ImageView quizImage;
    Button answerQuiz;
    EditText userAnswer;
    ArrayList<QuizItem> quizItems = new ArrayList<>();
    TextView questionNumberTextView;
    HashMap<QuizItem, String> result = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        questionNumberTextView = findViewById(R.id.questionNumberTextView);
        quizImage = findViewById(R.id.quizImage);
        answerQuiz = findViewById(R.id.quizButton);
        userAnswer = findViewById(R.id.quizEditText);
        getQuizItems();
        setQuestionNumberTextView();
        quizImage.setImageDrawable(quizItems.get(questionNumber).getImage());
    }
    private void nextImage() {
        if (questionNumber == quizItems.size()) {
            endQuiz();
        } else {
            quizImage.setImageDrawable(quizItems.get(questionNumber).getImage());
        }
    }

    private void endQuiz() {
        userAnswer.setVisibility(View.GONE);
        quizImage.setVisibility(View.GONE);
        answerQuiz.setVisibility(View.GONE);
        QuizRecyclerViewAdapter adapter = new QuizRecyclerViewAdapter(quizItems, result);
        RecyclerView recyclerView = findViewById(R.id.quizRecyclerView);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        //recyclerView.setAdapter();
        questionNumberTextView.setText(getString(R.string.score, String.valueOf(score), String.valueOf(quizItems.size())));
    }

    public void answerQuiz(View view) {
        String answer = userAnswer.getText().toString();
        if (answer.equals("")) {
            Toast.makeText(this, "Answer Cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(answer.toUpperCase().equals(quizItems.get(questionNumber).getCorrectAnswer().toUpperCase())){
            score++;
        }
        result.put(quizItems.get(questionNumber), answer);
        userAnswer.setText("");
        questionNumber++;
        nextImage();
    }

    private void setQuestionNumberTextView(){
        String qN = String.valueOf(questionNumber+1);
        questionNumberTextView.setText(getString(R.string.quizQuestion, qN, String.valueOf(quizItems.size())));
    }

    //Adds quizItems to array
    private void getQuizItems(){
        Drawable placeHolder = getResources().getDrawable(R.drawable.quizimagetest);
        String name = "test";
        QuizItem item = new QuizItem(name, placeHolder);
        quizItems.add(item);
    }
}
