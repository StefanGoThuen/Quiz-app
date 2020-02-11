package com.example.oblig1;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.Room;

import com.example.oblig1.room.AppDatabase;
import com.example.oblig1.room.QuizItem;

import java.util.ArrayList;

/**
 * Klasse som henter bilder og navn allerede lagret i databasen
 */

class DatabaseHandler {

    private static final String DHB = "DHB";

    static ArrayList<QuizItem> getQuizItems(Context context) {
        SharedPreferences pref = context.getSharedPreferences(DHB, Context.MODE_PRIVATE);
        if (!pref.getBoolean(DHB, false)) {
            handleDefaultImages(context);
        }
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "quiz").build();

        return (ArrayList<QuizItem>) db.quizDao().getAll();
    }


    /**
     * Metoden som legger til
     * bildene som allerede er laget
     */

    private static void handleDefaultImages(Context context) {
        ArrayList<QuizItem> list = new ArrayList<>();
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
        i1 = ImageHandler.scaledImage(i1);
        i2 = ImageHandler.scaledImage(i2);
        i3 = ImageHandler.scaledImage(i3);
        i4 = ImageHandler.scaledImage(i4);
        i5 = ImageHandler.scaledImage(i5);
        QuizItem item1 = null;
        if (i1 != null) {
            item1 = new QuizItem(0, "ole", ImageHandler.bitmapToBytes(i1));
        }
        QuizItem item2 = null;
        if (i2 != null) {
            item2 = new QuizItem(0, "jostein", ImageHandler.bitmapToBytes(i2));
        }
        QuizItem item3 = null;
        if (i3 != null) {
            item3 = new QuizItem(0, "petter", ImageHandler.bitmapToBytes(i3));
        }
        QuizItem item4 = null;
        if (i4 != null) {
            item4 = new QuizItem(0, "simen", ImageHandler.bitmapToBytes(i4));
        }
        QuizItem item5 = null;
        if (i5 != null) {
            item5 = new QuizItem(0, "stefan", ImageHandler.bitmapToBytes(i5));
        }
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);

        saveImagesToDatabase(context, list);
    }

    private static void saveImagesToDatabase(Context context, ArrayList<QuizItem> list) {
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "quiz").build();
        db.quizDao().insertAll(list);
        SharedPreferences pref = context.getSharedPreferences(DHB, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putBoolean(DHB, true);
        edit.apply();
    }


}
