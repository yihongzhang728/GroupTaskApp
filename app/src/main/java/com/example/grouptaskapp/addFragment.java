package com.example.grouptaskapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


//THIS NEVER GETS CALLED CAUSE I JUST USE THE CREATE NEW TASK FUNCTION INSTEAD OF A FRAGMENT
//ONLY HERE IN CASE IT'S NEEDED IN THE FUTURE


public class addFragment extends Fragment {
    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_addtasks, container, false);
    }
}
