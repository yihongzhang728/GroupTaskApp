package com.example.grouptaskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class TaskDetail extends AppCompatActivity {

    Intent launcher;
    private boolean editMode;
    private EditText et_name;
    private EditText et_deadline;
    private EditText et_assignee;

    private int taskIndex;

    private String servText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        et_name = (EditText) findViewById(R.id.name_text);
        et_deadline = (EditText) findViewById(R.id.deadline_text);
        et_assignee = (EditText) findViewById(R.id.assignee_text);

        launcher = getIntent();
        editMode = launcher.getBooleanExtra("taskEDIT", false);
        taskIndex = launcher.getIntExtra("taskNUM", -1);

        if (editMode) {
            // get job data to edit from the launcher
            et_name.setText(TaskList.taskItems.get(taskIndex).getName());
            et_deadline.setText(TaskList.taskItems.get(taskIndex).getDeadline());
            et_assignee.setText(TaskList.taskItems.get(taskIndex).getAssignee());
        }


        Button cancelBtn = (Button) findViewById(R.id.btn_cancel);
        cancelBtn.setOnClickListener(cancelListener);

        Button createBtn = (Button) findViewById(R.id.btn_create);
        createBtn.setOnClickListener(createListener);

    }


    private View.OnClickListener createListener = new View.OnClickListener() {
        public void onClick(View v) {

            if (!editMode) {   // new course being added
                TaskItem newTask = new TaskItem(et_name.getText().toString(),
                        et_deadline.getText().toString(),
                        et_assignee.getText().toString());
                TaskList.taskItems.add(newTask);
                setResult(RESULT_OK);
                finish();
            }
            else { // editing existing task!!
                TaskList.taskItems.get(taskIndex).setAll(et_name.getText().toString(),
                        et_deadline.getText().toString(),
                        et_assignee.getText().toString());
                setResult(RESULT_OK);
                finish();
            }
        }
    };


    private View.OnClickListener cancelListener = new View.OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };
}
