package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> navn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addNames();
    }

    public void onclickData(View view) {
        Intent intent = new Intent(this, MainData.class);
        startActivity(intent);
    }

    public void onclickQuiz(View view) {
        Intent intent = new Intent(this, MainQuiz.class);
        startActivity(intent);
    }

    public void onclickAdd(View view) {
        Intent intent = new Intent(this, MainAdd.class);
        intent.putExtra("navn", navn);
        startActivity(intent);
    }
    public void addNames(){
        String[] names = getResources().getStringArray(R.array.navn);
        navn = new ArrayList<String>(names.length);
        for(int i = 0; i < names.length; i++){
            navn.add(names[i]);
        }
    }
}
