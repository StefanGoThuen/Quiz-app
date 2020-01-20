package com.example.oblig1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainAdd extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        String string = getIntent().getExtras().getString(MainActivity.MYSTRING);
        if (string != null) {
            textView.setText(string);
        }
    }
}
