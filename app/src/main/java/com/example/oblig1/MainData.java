package com.example.oblig1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oblig1.recyclerview.DatabaseItem;
import com.example.oblig1.recyclerview.DatabaseRecyclerViewAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainData extends AppCompatActivity {
    ImageView dataImage;
    TextView textImage;
    private SharedPreferences pref;
    private ArrayList<DatabaseItem> dataItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        dataImage = findViewById(R.id.dataImage);
        textImage = findViewById(R.id.dataText);
        getDataItems();
        initializeRecyclerView();
    }


    private void initializeRecyclerView() {
        DatabaseRecyclerViewAdapter adapter = new DatabaseRecyclerViewAdapter(dataItems);
        RecyclerView recyclerView = findViewById(R.id.dataRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    //Adds dataItems to array
  //  private void getDataItems() {
   //     Drawable placeHolder = getResources().getDrawable(R.drawable.quizimagetest);
     //   String name = "test";
       // DatabaseItem item = new DatabaseItem(name, placeHolder);
        //for (int i = 0; i < 15; i++) {
          //  dataItems.add(item);

        //}
    //}
    private void getDataItems(){
        pref = getSharedPreferences("names", MODE_PRIVATE);
        Map allprefs = pref.getAll();
        Object[] strings = allprefs.values().toArray();
        HashMap<String, Bitmap> map = ImageHandler.retrieveImageWithName(this, getArrayList(strings));
        for(Object s: strings){
            if(s.toString().contains("_")){
                dataItems.add(new DatabaseItem(s.toString().split("_")[1], map.get(s.toString().split("_")[1])));
            }else{
                dataItems.add(new DatabaseItem(s.toString(), map.get(s.toString())));
            }

        }
    }
    private ArrayList<String> getArrayList(Object[] strings){
        ArrayList<String> list = new ArrayList<String>();
        for(Object o : strings){
            list.add(o.toString());
        }
        return list;
    }
    public void rmAll(View v){
        SharedPreferences pref = getSharedPreferences("names", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(this, MainData.class);
        startActivity(intent);
    }

}
