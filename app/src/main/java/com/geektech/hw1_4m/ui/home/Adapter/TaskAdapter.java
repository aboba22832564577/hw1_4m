package com.geektech.hw1_4m.ui.home.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.geektech.hw1_4m.databinding.ItemWorkBinding;
import com.geektech.hw1_4m.ui.home.Model.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private final List<TaskModel> models = new ArrayList<>();


    public void addNewNote(TaskModel model) {
        this.models.add(model);
        notifyDataSetChanged();
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWorkBinding binding = ItemWorkBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent,
                        false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(models.get(position).getTitle(), models.get(position).getCreated());
        holder.binding.itemBackground.setVerticalScrollbarPosition(position);
        if (position % 2 == 1) {
            holder.itemView.setBackgroundColor(Color.GREEN);
        } else {
            holder.itemView.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemWorkBinding binding;

        public ViewHolder(@NonNull ItemWorkBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(String title, String created) {
            binding.txtResult.setText(title);
            binding.txtCreated.setText(created);
        }
    }
}
