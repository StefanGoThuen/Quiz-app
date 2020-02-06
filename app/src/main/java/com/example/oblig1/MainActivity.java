package com.example.oblig1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

/**
 * Startsiden for appen som inneholder kanpper til de forskjellige aktivitetene
 */

public class MainActivity extends AppCompatActivity {
    private SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkForOwner();
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
    private void checkForOwner(){
        pref = getSharedPreferences("owner", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if(pref.getString("owner", "default").equals("default")){
            System.out.println("NO OWNER SET!!!");
            openDialog();


        }else{
            System.out.println("OWNER SET!!!");
            return;
        }
    }
    public void openDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Who are you?");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("Enter your name...");
        builder.setView(input);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String owner = input.getText().toString();
                if(owner.equals("")){
                    return;
                }else{
                    System.out.println("OK");
                    setOwner(owner);
                }
            }
        });
        builder.show();
    }
    private void setOwner(String owner){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("owner", owner);
        editor.commit();
    }
    public void btnNewOwner(View v){
        openDialog();
    }
}
