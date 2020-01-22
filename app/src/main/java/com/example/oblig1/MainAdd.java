package com.example.oblig1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainAdd extends AppCompatActivity {
    private ArrayList<String> navn;
    private TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        navn = getIntent().getStringArrayListExtra("navn");
        view = findViewById(R.id.textView);
        for(String s : navn){
            append(s);

        }
    }
    public void addNew(View v){
        EditText txt = findViewById(R.id.editText);
        append(txt.getText().toString());
        txt.setText("");
    }
    private void append(String s){
        view.append(s + "\n");
    }
}
