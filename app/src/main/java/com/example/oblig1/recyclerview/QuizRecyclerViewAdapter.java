package com.example.oblig1.recyclerview;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oblig1.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Denne klassen viser spørsmålene og sjekker svarene i Quiz
 */

public class QuizRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<DatabaseItem> items;
    private HashMap<DatabaseItem, String> userAnswers;

    public QuizRecyclerViewAdapter(ArrayList<DatabaseItem> items, HashMap<DatabaseItem, String> userAnswers) {
        this.items = items;
        this.userAnswers = userAnswers;
    }
    /**
     * RecycleView sin ViewHolder for å representere de itemsene de henter i DatabaseItem
     *
     * @param parent
     * View gruppen der items blir representert / lagt til.
     *
     * @param viewType
     * typen til den nye Viewen
     *
     * @return
     * Retunerer den nye view med itemsene (bilder og navn) i.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View cellForRow = layoutInflater.inflate(R.layout.quiz_item, parent, false);
        return new ViewHolder(cellForRow);
    }

    /**
     * RecycleView sin ViewHolder for å vise og gi items en posisjon
     * i den nye viewet
     *
     * @param holder
     *   ViewHolder som viser dataen/items der i den posisjonen de har blitt tildelt
     *
     * @param position
     *      Posisjonen til dataen/items
     *
     *      Metoden sjekker også om brukeren sitt svar er riktig eller feil
     *      og setter fargen i følge svaret
     */

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DatabaseItem item = items.get(position);
        TextView userAnswerTextView = holder.itemView.findViewById(R.id.userAnswerTextView);
        TextView correctAnswerTextView = holder.itemView.findViewById(R.id.correctAnswerTextView);
        ImageView image = holder.itemView.findViewById(R.id.imageView);
        if (userAnswers.get(item) != null) {
            String userAnswer = userAnswers.get(item).toUpperCase();
            String correctAnswer = item.getName().toUpperCase();
            if (!userAnswer.equals(correctAnswer)) {
                userAnswerTextView.setTextColor(Color.RED);
            } else {
                userAnswerTextView.setTextColor(Color.GREEN);
            }
            correctAnswerTextView.setText(correctAnswer);
            userAnswerTextView.setText(userAnswer);
        }

        image.setImageBitmap(item.getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
