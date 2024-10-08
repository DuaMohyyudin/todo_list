package com.example.todo_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<String> taskList;

    public TaskAdapter(ArrayList<String> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        String task = taskList.get(position);
        holder.textTask.setText(task);  // Set the task text in the TextView
    }

    @Override
    public int getItemCount() {
        return taskList.size();  // Return the size of the task list
    }

    // ViewHolder class to hold the task UI elements
    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView textTask;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox_task);
            textTask = itemView.findViewById(R.id.text_task);
        }
    }
}
