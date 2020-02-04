package com.example.oblig1.recyclerview;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oblig1.ImageHandler;
import com.example.oblig1.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class DatabaseRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<DatabaseItem> items;
    private Context context;

    public DatabaseRecyclerViewAdapter(ArrayList<DatabaseItem> items, Context context) {
        this.items = items;
        this.context = context;
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
        View cellForRow = layoutInflater.inflate(R.layout.data_item, parent, false);
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
     *      I tillegg er det en onClicklistener for å slette bilder i databasen
     *      som ikke er default blider
     */

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final DatabaseItem item = items.get(position);
        ImageView imageView = holder.itemView.findViewById(R.id.dataImage);
        imageView.setImageBitmap(item.getImage());
        TextView textView = holder.itemView.findViewById(R.id.dataText);
        textView.setText(item.getName());
        holder.itemView.findViewById(R.id.dataItemLayout).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SharedPreferences pref = context.getSharedPreferences("names", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                if (ImageHandler.removeImageFromStorage(context, item.getFileName())) {
                    editor.remove(item.getFileName());
                    editor.apply();
                    Toast.makeText(context, "The item was deleted", Toast.LENGTH_SHORT).show();
                    items.remove(item);
                    notifyItemRemoved(position);
                } else {
                    Toast.makeText(context, "Default items cannot be deleted", Toast.LENGTH_SHORT).show();
                }
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
}
