package com.example.grouptaskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Set;

public class SettingsActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);
        getSupportActionBar().setTitle("Settings");
        EditText edit = (EditText) findViewById(R.id.editText);

        Button save = (Button) findViewById(R.id.button);
        save.setOnClickListener(createListener);

        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue != -1) {
            // set the selected value of the spinner
            spinner.setSelection(spinnerValue);
        }
        Switch mySwitch = (Switch) findViewById(R.id.switch1);
        boolean silent = sharedPref.getBoolean("switch", false);
        mySwitch.setChecked(silent);


        spinner.setOnItemSelectedListener(createListenerSpin);
        mySwitch.setOnCheckedChangeListener(createListenerSwitch);
    }


    private Switch.OnCheckedChangeListener createListenerSwitch = new Switch.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            SharedPreferences sharedPref = getSharedPreferences("FileName", 0);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("switch", b);
            editor.commit();
        }
    };

    private View.OnClickListener createListener = new View.OnClickListener() {
        public void onClick(View v) {
            EditText edit = (EditText) findViewById(R.id.editText);
            String username = edit.getText().toString();
            ((MyApplication) SettingsActivity2.this.getApplication()).setUsername(username);
            finish();

        }
    };

    private Spinner.OnItemSelectedListener createListenerSpin = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Spinner spinner = (Spinner) findViewById(R.id.spinner2);
            int userChoice = spinner.getSelectedItemPosition();
            SharedPreferences sharedPref = getSharedPreferences("FileName", 0);
            SharedPreferences.Editor prefEditor = sharedPref.edit();
            prefEditor.putInt("userChoiceSpinner", userChoice);
            prefEditor.commit();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}

