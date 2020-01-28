package com.example.oblig1;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.oblig1.recyclerview.DatabaseItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class DatabaseHandler {


    static void getQuizItems(Context context, ArrayList<DatabaseItem> quizItems){
        BitmapFactory.decodeResource(context.getResources(), R.drawable.ole);
        SharedPreferences pref = context.getSharedPreferences("names", MODE_PRIVATE);
        Map allprefs = pref.getAll();
        Object[] strings = allprefs.values().toArray();
        HashMap<String, Bitmap> map = ImageHandler.retrieveImageWithName(context, getArrayList(strings));
        for(Object s: strings){
            if(s.toString().contains("_")){
                quizItems.add(new DatabaseItem(s.toString().split("_")[1], map.get(s.toString().split("_")[1])));
            }else{
                quizItems.add(new DatabaseItem(s.toString(), map.get(s.toString())));
            }
        }
        addDefaultImages(quizItems, context);
    }
    private static ArrayList<String> getArrayList(Object[] strings){
        ArrayList<String> list = new ArrayList<>();
        for(Object o : strings){
            list.add(o.toString());
        }
        return list;
    }

    private static void addDefaultImages(ArrayList<DatabaseItem> quizItems, Context context){
        Bitmap i1 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ole);
        Bitmap i2 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.jostein);
        Bitmap i3 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.petter);
        Bitmap i4 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.simen);
        Bitmap i5 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.stefan);
        DatabaseItem db1 = new DatabaseItem("ole", i1);
        DatabaseItem db2 = new DatabaseItem("jostein", i2);
        DatabaseItem db3 = new DatabaseItem("petter", i3);
        DatabaseItem db4 = new DatabaseItem("simen", i4);
        DatabaseItem db5 = new DatabaseItem("stefan", i5);
        quizItems.add(db1);
        quizItems.add(db2);
        quizItems.add(db3);
        quizItems.add(db4);
        quizItems.add(db5);
    }



}
