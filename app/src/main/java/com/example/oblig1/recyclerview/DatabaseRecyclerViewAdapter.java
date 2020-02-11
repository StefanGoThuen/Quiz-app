package com.example.oblig1.recyclerview;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.oblig1.ImageHandler;
import com.example.oblig1.MainActivity;
import com.example.oblig1.R;
import com.example.oblig1.room.AppDatabase;
import com.example.oblig1.room.QuizItem;

import java.util.ArrayList;

public class DatabaseRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<QuizItem> items;
    private Context context;

    public DatabaseRecyclerViewAdapter(ArrayList<QuizItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    /**
     * RecycleView sin ViewHolder for å representere de itemsene de henter i DatabaseItem
     *
     * @param parent   View gruppen der items blir representert / lagt til.
     * @param viewType typen til den nye Viewen
     * @return Retunerer den nye view med itemsene (bilder og navn) i.
     */

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View cellForRow = layoutInflater.inflate(R.layout.data_item, parent, false);
        return new ViewHolder(cellForRow);
    }

    /**
     * RecycleView sin ViewHolder for å vise og gi items en posisjon
     * i den nye viewet
     *
     * @param holder   ViewHolder som viser dataen/items der i den posisjonen de har blitt tildelt
     * @param position Posisjonen til dataen/items
     *                 <p>
     *                 I tillegg er det en onClicklistener for å slette bilder i databasen
     *                 som ikke er default blider
     */

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final QuizItem item = items.get(position);
        ImageView imageView = holder.itemView.findViewById(R.id.dataImage);
        imageView.setImageBitmap(ImageHandler.byteToBitmap(item.getImage()));
        TextView textView = holder.itemView.findViewById(R.id.dataText);
        textView.setText(item.getName());
        holder.itemView.findViewById(R.id.dataItemLayout).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                items.remove(item);
                MainActivity.databaseItems.remove(item);
                notifyDataSetChanged();
                DeleteAsync delete = new DeleteAsync(item);
                delete.execute();
                return true;
            }
        });
        holder.itemView.findViewById(R.id.dataItemLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO give user ability to change name
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class DeleteAsync extends AsyncTask<Void, Void, Void> {

        QuizItem item;

        DeleteAsync(QuizItem item) {
            this.item = item;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase db = Room.databaseBuilder(context,
                    AppDatabase.class, "quiz").build();
            db.quizDao().delete(item);
            return null;
        }
    }
}
