package com.example.grouptaskapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewTask extends AppCompatActivity {

    Intent launcher;
    private int id;

    private TextView name;
    private TextView deadline;
    private TextView assignee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        getSupportActionBar().setTitle("View Task");

        launcher = getIntent();
        id = (int) launcher.getLongExtra("ID", -1);
        name = (TextView) findViewById(R.id.name_view);
        deadline = (TextView) findViewById(R.id.deadline_view);
        assignee = (TextView) findViewById(R.id.assignee_view);

        name.setText(TaskList.taskItems.get(id).getName());
        deadline.setText(TaskList.taskItems.get(id).getDeadline());
        assignee.setText(TaskList.taskItems.get(id).getAssignee());

        Button completeBtn = (Button) findViewById(R.id.btn_complete);
        completeBtn.setOnClickListener(completeListener);

    }

    private View.OnClickListener completeListener = new View.OnClickListener() {
        public void onClick(View v) {
            TaskList.taskItems.remove(id);
            setResult(RESULT_OK);
            finish();
        }
    };

}
