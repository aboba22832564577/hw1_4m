package com.geektech.hw1_4m.ui.home.Adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.geektech.hw1_4m.App;
import com.geektech.hw1_4m.databinding.ItemWorkBinding;
import com.geektech.hw1_4m.ui.home.Model.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<TaskModel> models = new ArrayList<>();
    private IHomeClickListener iHomeClickListener;
    private LongClick longClick;


    @SuppressLint("NotifyDataSetChanged")
    public void addItem(List<TaskModel> list){
        this.models.clear();
        this.models.addAll(list);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setLongClick (LongClick longClick) {
        this.longClick = longClick;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setIHomeClickListener (IHomeClickListener iHomeClickListener) {
        this.iHomeClickListener = iHomeClickListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWorkBinding binding = ItemWorkBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(models.get(position).getTitle(), models.get(position).getCreated());
        holder.binding.itemBackground.setVerticalScrollbarPosition(position);
        if (position % 2 == 1) {
            holder.itemView.setBackgroundColor(Color.GRAY);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }


        holder.itemView.setOnLongClickListener(v -> {
            longClick.OmLongClick(models.get(position), position);
            return true;
        });



        holder.itemView.setOnClickListener(v -> iHomeClickListener.OnHomeClick(models.get(position),
                position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void sortByAlphabet() {
        models = App.dataBase.caseDao().getAllTasksByAlphabet();
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setByDate() {
        models = App.dataBase.caseDao().getAllTasksByDate();
        notifyDataSetChanged();
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

    public interface IHomeClickListener{
        void OnHomeClick(TaskModel model, int position);
    }

    public interface  LongClick{
        void OmLongClick(TaskModel model, int position);
    }

}
