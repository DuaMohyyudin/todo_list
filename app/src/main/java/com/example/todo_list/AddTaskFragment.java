package com.example.todo_list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddTaskFragment extends Fragment {

    private EditText editTextTask;
    private Button buttonAddTask;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);

        // Initialize UI components
        editTextTask = view.findViewById(R.id.editTextTask);
        buttonAddTask = view.findViewById(R.id.buttonAddTask);

        // Log fragment lifecycle event
        Log.d("AddTaskFragment", "onCreateView");

        // Set button click listener to add task
        buttonAddTask.setOnClickListener(v -> {
            String taskText = editTextTask.getText().toString().trim();
            if (!taskText.isEmpty()) {
                addTaskToMainActivity(taskText);  // Add task to MainActivity
            } else {
                Toast.makeText(getActivity(), "Please enter a task", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("AddTaskFragment", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("AddTaskFragment", "onResume");
    }

    // Method to send the task back to MainActivity
    private void addTaskToMainActivity(String taskText) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addTask(taskText);  // Call MainActivity method to add task
            getActivity().getSupportFragmentManager().popBackStack();  // Close fragment after task is added
        }
    }
}
