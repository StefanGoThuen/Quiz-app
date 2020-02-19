package com.example.oblig1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oblig1.dialogs.OnSingleInput;
import com.example.oblig1.dialogs.OwnerDialog;
import com.example.oblig1.recyclerview.DatabaseItem;

import java.util.ArrayList;

/**
 * Startsiden for appen som inneholder kanpper til de forskjellige aktivitetene
 */

public class MainActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private static final String OWNER = "OWNER";
    public static final ArrayList<DatabaseItem> databaseItems = new ArrayList<>();
    public static boolean databaseDownloaded = false;
    public static boolean isDownloading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (databaseItems.size() == 0) {
            isDownloading = true;
            LoadDatabaseToMemory.LoadDatabaseAsync loadAsync = new LoadDatabaseToMemory.LoadDatabaseAsync(getApplicationContext(), databaseItems, new CallbackInterface() {
                @Override
                public void databaseDownloaded() {
                    loadUI();
                }
            });
            loadAsync.execute();
        }
        pref = getSharedPreferences(OWNER, MODE_PRIVATE);
        String owner = pref.getString(OWNER, "");
        if (owner.equals("")) {
            (new OwnerDialog(this, new OnSingleInput() {
                @Override
                public void onInput(String text) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString(OWNER, text);
                    editor.apply();
                }

                @Override
                public void onEmptyInput() {
                    Toast.makeText(getApplicationContext(), "Owner must be applied!", Toast.LENGTH_SHORT).show();
                }
            }, owner)).show();
        }
    }

    void loadUI() {
        findViewById(R.id.mainProgressBar).setVisibility(View.GONE);
        findViewById(R.id.databaseTextView).setVisibility(View.GONE);
        findViewById(R.id.databaseButton).setVisibility(View.VISIBLE);
        findViewById(R.id.addButton).setVisibility(View.VISIBLE);
        findViewById(R.id.quizButton).setVisibility(View.VISIBLE);
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
    public void addNames() {
        SharedPreferences.Editor editor = pref.edit();
        String[] names = getResources().getStringArray(R.array.navn);

        for (String s : names) {
            editor.putString(s, s);
        }
        editor.apply();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.owner_menu, menu);
        return true;
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String owner = pref.getString(OWNER, "");
        if (id == R.id.changeOwnerButton) {
            (new OwnerDialog(this, new OnSingleInput() {
                @Override
                public void onInput(String text) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString(OWNER, text);
                    editor.apply();
                }

                @Override
                public void onEmptyInput() {
                    Toast.makeText(getApplicationContext(), "Owner must be applied!", Toast.LENGTH_SHORT).show();
                }
            }, owner)).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}

