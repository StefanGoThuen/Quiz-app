package com.example.oblig1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Map;

public class MainAdd extends AppCompatActivity {
    private TextView view;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        view = findViewById(R.id.textView);
        pref = getSharedPreferences("names", MODE_PRIVATE);
        showNames(view);

    }
    public void addNew(View v){
        EditText txt = findViewById(R.id.editText);
        String nametoAdd = txt.getText().toString();
        append(nametoAdd);
        txt.setText("");
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(nametoAdd, nametoAdd);
        editor.commit();
    }
    private void append(String s){
        view.append(s + "\n");
    }
    private void showNames(View v){
        Map names = pref.getAll();
        Object[] s = names.values().toArray();
        for(Object o : s){
            view.append(o.toString() + "\n");
        }
    }
}
