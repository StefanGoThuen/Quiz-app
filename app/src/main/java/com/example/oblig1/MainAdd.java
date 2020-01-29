package com.example.oblig1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class MainAdd extends AppCompatActivity {
    private TextView view;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int RESULT_LOAD_IMG = 2;
    private Bitmap imageBitmap;
    private ImageView imageView;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        view = findViewById(R.id.textView);
        pref = getSharedPreferences("names", MODE_PRIVATE);
        imageView = findViewById(R.id.imageView);
        showNames();

    }

    public void addNew(View v) {
        if (v.getId() == R.id.button) {

            EditText txt = findViewById(R.id.editText);
            String nametoAdd = txt.getText().toString();
            if (nametoAdd.equals("") || imageBitmap == null) {
                return;
            }
            append(nametoAdd);
            nametoAdd = ImageHandler.randomString() + nametoAdd;
            txt.setText("");
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(nametoAdd, nametoAdd);
            editor.apply();

            //Saves the image

            ImageHandler.saveBitmapToFile(this, nametoAdd, imageBitmap);
            imageView.setImageBitmap(null);
        } else if(v.getId()==R.id.galleryButton){
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
        }
    }

    private void append(String s) {
        view.append(s + "\n");
    }

    private void showNames() {
        Map names = pref.getAll();
        Object[] s = names.values().toArray();
        for (Object o : s) {
            if (o.toString().contains("_")) {
                view.append(o.toString().split("_")[1] + "\n");
            } else {
                view.append(o.toString() + "\n");
            }

        }
    }

    public void startPicture(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");

            imageView.setImageBitmap(imageBitmap);
        } else if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageBitmap = ImageHandler.scaledImage(selectedImage);
                imageView.setImageBitmap(imageBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }
    }
}
