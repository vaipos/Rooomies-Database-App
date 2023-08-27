package com.example.roomie_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<chore> array;

    public MyAdapter(Context context, ArrayList<chore> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.chore_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        chore chores = array.get(position);

        holder.choreName.setText(chores.Chore);

    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox choreName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            choreName = itemView.findViewById(R.id.choresBox);

        }
    }
}
