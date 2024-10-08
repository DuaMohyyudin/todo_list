package com.example.todo_list;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayoutTasks;
    private FloatingActionButton fabAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayoutTasks = findViewById(R.id.linearLayoutTasks);
        fabAddTask = findViewById(R.id.fabAddTask);

        fabAddTask.setOnClickListener(v -> {
            // Show the fragment to add a new task
            AddTaskFragment addTaskFragment = new AddTaskFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, addTaskFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    // Method to add a task
    public void addTask(String taskText) {
        // Create a new LinearLayout for the task
        LinearLayout taskLayout = new LinearLayout(this);
        taskLayout.setOrientation(LinearLayout.HORIZONTAL);
        taskLayout.setPadding(16, 16, 16, 16); // Padding for better spacing

        // Create a CheckBox for the task
        CheckBox taskCheckBox = new CheckBox(this);

        // Create a TextView for the task description
        TextView taskTextView = new TextView(this);
        taskTextView.setText(taskText);
        taskTextView.setTextSize(18);
        taskTextView.setTextColor(getResources().getColor(android.R.color.white));

        // Add CheckBox and TextView to the task layout
        taskLayout.addView(taskCheckBox);
        taskLayout.addView(taskTextView);

        // Add the task layout to the main LinearLayout
        linearLayoutTasks.addView(taskLayout);
    }
}
