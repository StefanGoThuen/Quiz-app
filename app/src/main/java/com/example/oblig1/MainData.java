package com.example.oblig1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oblig1.recyclerview.DatabaseItem;
import com.example.oblig1.recyclerview.DatabaseRecyclerViewAdapter;

import java.io.File;
import java.util.ArrayList;

/**
 * Activity til Databasen
 */

public class MainData extends AppCompatActivity {
    ImageView dataImage;
    TextView textImage;
    private SharedPreferences pref;
    ArrayList<DatabaseItem> dataItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        dataImage = findViewById(R.id.dataImage);
        textImage = findViewById(R.id.dataText);
        DatabaseHandler.getQuizItems(this , dataItems);
        initializeRecyclerView();
    }

    /**
     * Henter ferdig definerte views fra DatabaseRecyclerViewAdapter
      */

    private void initializeRecyclerView() {
        DatabaseRecyclerViewAdapter adapter = new DatabaseRecyclerViewAdapter(dataItems, this);
        RecyclerView recyclerView = findViewById(R.id.dataRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }



    /**
     * Metoden for å oppdatere viewet
     */

    public void rmAll(View v){
        SharedPreferences pref = getSharedPreferences("names", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
        rmPictures();
        Intent intent = new Intent(this, MainData.class);
        startActivity(intent);

    }

    /**
     * Slett alle bildene knapp
     */

    private void rmPictures(){
        File picDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File[] allPics = picDir.listFiles();
        for(File pic : allPics){
            pic.delete();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            startActivity(new Intent(this, MainAdd.class));
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }

}
