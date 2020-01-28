package com.example.oblig1.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oblig1.R;

import java.util.ArrayList;

public class DatabaseRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<DatabaseItem> items;

    public DatabaseRecyclerViewAdapter(ArrayList<DatabaseItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View cellForRow = layoutInflater.inflate(R.layout.data_item, parent, false);
        return new ViewHolder(cellForRow);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DatabaseItem item = items.get(position);
        ImageView imageView = holder.itemView.findViewById(R.id.dataImage);
        imageView.setImageBitmap(item.getImage());
        TextView textView = holder.itemView.findViewById(R.id.dataText);
        textView.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
