package com.example.grouptaskapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class TaskList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    public static final int EDIT_TASK = 0;
    public static final int NEW_TASK = 1;
    public static final int VIEW_TASK = 1;

    public static String username;

    public static final int MENU_ITEM_EDITVIEW = Menu.FIRST;
    public static final int MENU_ITEM_DELETE = Menu.FIRST + 1;

    private ListView taskList;
    private TaskItemAdapter aa;

    private DrawerLayout drawer;

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

        //getSupportActionBar().setTitle("All Tasks");

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
                launchViewTask(id);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchNewTask();
            }
        });



////////////////////////////////////////////////////////////////////////////////////////////////////
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Tasks");


        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new allFragment()).commit();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
         if(savedInstanceState == null) {
             ((MyApplication) this.getApplication()).setUsername("User (Set name in settings)");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new allFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_allTasks);
        }

         NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
         View headerView = navView.getHeaderView(0);
        TextView name = (TextView) headerView.findViewById(R.id.welcome);
        name.setText("Welcome " + ((MyApplication) this.getApplication()).getUsername());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_allTasks:
                Intent intentC = new Intent(TaskList.this, TaskList.class);
                startActivity(intentC);
                getSupportActionBar().setTitle("All Tasks");
                break;
            case R.id.nav_myTasks:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new myFragment()).commit();
                getSupportActionBar().setTitle("My Tasks");
                break;
            case R.id.nav_addTask:
                launchNewTask();
                getSupportActionBar().setTitle("Add Task");
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navView.getHeaderView(0);
        TextView name = (TextView) headerView.findViewById(R.id.welcome);
        name.setText("Welcome " + ((MyApplication) this.getApplication()).getUsername());
        return true;
    }

    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
            View headerView = navView.getHeaderView(0);
            TextView name = (TextView) headerView.findViewById(R.id.welcome);
            name.setText("Welcome " + ((MyApplication) this.getApplication()).getUsername());
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////

    private void launchNewTask() {
        Intent intentA = new Intent(TaskList.this, TaskDetail.class);
        intentA.putExtra("taskEDIT", false);
        startActivityForResult(intentA, NEW_TASK);
    }

    private void launchViewTask (long id) {
        Intent intentB = new Intent(TaskList.this, ViewTask.class);
        intentB.putExtra("ID", id);
        startActivityForResult(intentB, VIEW_TASK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && (requestCode == TaskList.EDIT_TASK
                || requestCode == TaskList.NEW_TASK || requestCode == TaskList.VIEW_TASK)) {
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
            startActivity(new Intent(this, SettingsActivity2.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
