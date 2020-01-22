package com.example.oblig1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Map;

public class MainAdd extends AppCompatActivity {
    private TextView view;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        view = findViewById(R.id.textView);
        pref = getSharedPreferences("names", MODE_PRIVATE);
        imageView = findViewById(R.id.imageView);
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
    public void startPicture(View v){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            imageView.setImageBitmap(imageBitmap);
        }
    }
}
