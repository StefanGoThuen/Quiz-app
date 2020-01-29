package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

/**
 * Startsiden for appen som inneholder kanpper til de forskjellige aktivitetene
 */

public class MainActivity extends AppCompatActivity {
    private SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        startActivity(intent);
    }

    /**
     * fremtidig prosjekt. IKKE i bruk ennå
     */
    public void addNames(){
        SharedPreferences.Editor editor = pref.edit();
        String[] names = getResources().getStringArray(R.array.navn);

        for(String s : names){
            editor.putString(s, s);
        }
        editor.commit();
    }
}
