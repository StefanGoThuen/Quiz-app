package com.example.oblig1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainAdd extends AppCompatActivity {
    private ArrayList<String> navn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        navn = getIntent().getStringArrayListExtra("navn");
    }
}
