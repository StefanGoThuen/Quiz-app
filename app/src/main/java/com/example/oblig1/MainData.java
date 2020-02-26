package com.example.oblig1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
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

import static com.example.oblig1.MainActivity.databaseItems;

/**
 * Activity til Databasen
 */

public class MainData extends AppCompatActivity {
    ImageView dataImage;
    TextView textImage;
    private SharedPreferences pref;
    DatabaseRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        findViewById(R.id.progress_circular).setVisibility(View.GONE);
        findViewById(R.id.progressbarTextView).setVisibility(View.GONE);
        findViewById(R.id.dataLayout).setVisibility(View.VISIBLE);
        if (!MainActivity.databaseDownloaded && !MainActivity.isDownloading) {
            MainActivity.isDownloading = true;
            LoadDatabaseToMemory.LoadDatabaseAsync loadAsync = new LoadDatabaseToMemory.LoadDatabaseAsync(getApplicationContext(), databaseItems, new CallbackInterface() {
                @Override
                public void databaseDownloaded() {
                    updateUi();
                }
            });
            loadAsync.execute();
        } else{
            updateUi();
        }

    }

    void updateUi(){
        dataImage = findViewById(R.id.dataImage);
        textImage = findViewById(R.id.dataText);
        initializeRecyclerView();
    }


    /**
     * Henter ferdig definerte views fra DatabaseRecyclerViewAdapter
      */


    private void initializeRecyclerView() {
        adapter = new DatabaseRecyclerViewAdapter(MainActivity.databaseItems, this);
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
        editor.apply();
        rmPictures();
        MainActivity.databaseItems.clear();
        adapter.notifyDataSetChanged();
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
