package com.example.grouptaskapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class TaskList extends AppCompatActivity {


    public static final int EDIT_TASK = 0;
    public static final int NEW_TASK = 1;

    public static final int MENU_ITEM_EDITVIEW = Menu.FIRST;
    public static final int MENU_ITEM_DELETE = Menu.FIRST + 1;

    private ListView taskList;
    private TaskItemAdapter aa;

    // this is temporarily set up with package and static access so
    // that job detail can access items --
    // would be changed to private instance if supported by
    // permanent back-end database
    static ArrayList<TaskItem> taskItems;

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        taskList = (ListView) findViewById(R.id.tasklist);
        // create ArrayList of courses from database
        taskItems = new ArrayList<TaskItem>();
        // make array adapter to bind arraylist to listview with new custom item layout
        aa = new TaskItemAdapter(this, R.layout.task_item_layout, taskItems);
        taskList.setAdapter(aa);

        registerForContextMenu(taskList);

        aa.notifyDataSetChanged();  // to refresh items in the list

        // program a short click on the list item
        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(view, "Selected #" + id, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchNewTask();
            }
        });

    }

    private void launchNewTask() {
        Intent intentA = new Intent(TaskList.this, TaskDetail.class);
        intentA.putExtra("taskEDIT", false);
        startActivityForResult(intentA, NEW_TASK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && (requestCode == TaskList.EDIT_TASK
                || requestCode == TaskList.NEW_TASK)) {
            aa.notifyDataSetChanged();
        }
        // if resultCode == RESULT_CANCELED no need to update display
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // not implemented
        if (id == R.id.menu_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
