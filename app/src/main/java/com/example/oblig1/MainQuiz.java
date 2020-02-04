package com.example.oblig1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oblig1.recyclerview.DatabaseItem;
import com.example.oblig1.recyclerview.QuizRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Aktiviteten til QUIZ
 */


public class MainQuiz extends AppCompatActivity {
    int questionNumber = 0;
    int score = 0;
    ImageView quizImage;
    Button answerQuiz;
    EditText userAnswer;
    TextView questionNumberTextView;
    ArrayList<DatabaseItem> quizItems = new ArrayList<>();
    HashMap<DatabaseItem, String> result = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        quizImage = findViewById(R.id.quizImage);
        answerQuiz = findViewById(R.id.quizButton);
        userAnswer = findViewById(R.id.quizEditText);
        questionNumberTextView = findViewById(R.id.questionNumberTextView);

        shuffleAndLimitQuizItems();
        setQuestionNumberTextView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateUI(View.GONE);
        while (!MainActivity.databaseDownloaded) {
            Log.i("gg", ":)");
        }
        if (MainActivity.databaseItems.size() == 0) {
            Toast.makeText(this, "Database is empty, add items to start a quiz! (DUH)", Toast.LENGTH_SHORT).show();
            finish();
        } else {

            findViewById(R.id.progressbarTextView).setVisibility(View.GONE);
            findViewById(R.id.progress_circular).setVisibility(View.GONE);
            updateUI(View.VISIBLE);
            quizImage.setImageBitmap(MainActivity.databaseItems.get(questionNumber).getImage());
        }

    }

    /**
     * Metodene nedenfor er for sjekke svar, hente neste sprøsmål, vise framgang, osv..
     */

    private void nextImage() {
        questionNumber++;
        if (questionNumber == MainActivity.databaseItems.size()) {
            endQuiz();
        } else {
            quizImage.setImageBitmap(MainActivity.databaseItems.get(questionNumber).getImage());
        }
    }

    private void endQuiz() {
        updateUI(View.GONE);
        QuizRecyclerViewAdapter adapter = new QuizRecyclerViewAdapter(MainActivity.databaseItems, result);
        RecyclerView recyclerView = findViewById(R.id.quizRecyclerView);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        //recyclerView.setAdapter();
        questionNumberTextView.setText(getString(R.string.score, String.valueOf(score), String.valueOf(MainActivity.databaseItems.size())));
    }

    private void updateUI(int visibility) {
        userAnswer.setVisibility(visibility);
        quizImage.setVisibility(visibility);
        answerQuiz.setVisibility(visibility);
    }

    public void answerQuiz(View view) {
        String answer = userAnswer.getText().toString();
        if (answer.equals("")) {
            Toast.makeText(this, "Answer Cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (answer.toUpperCase().equals(MainActivity.databaseItems.get(questionNumber).getName().toUpperCase())) {
            score++;
        }
        result.put(MainActivity.databaseItems.get(questionNumber), answer);
        userAnswer.setText("");
        setQuestionNumberTextView();
        nextImage();
    }

    private void setQuestionNumberTextView() {
        String qN = String.valueOf(questionNumber + 1);
        questionNumberTextView.setText(getString(R.string.quizQuestion, qN, String.valueOf(MainActivity.databaseItems.size())));
    }

    //Adds databaseItems to array

    private void shuffleAndLimitQuizItems() {
        Collections.shuffle(MainActivity.databaseItems);
        if (MainActivity.databaseItems.size() > 10) {
            for (int i = 0; i < 10; i++) {
                quizItems.add(MainActivity.databaseItems.get(i));
            }
        } else {
            quizItems.addAll(MainActivity.databaseItems);
        }
    }

}

