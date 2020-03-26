package com.example.grouptaskapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class allFragment extends Fragment {
    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_alltasks, container, false);
    }
}

