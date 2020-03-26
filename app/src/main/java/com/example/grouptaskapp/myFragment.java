package com.example.grouptaskapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class myFragment extends Fragment {
    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_mytasks, container, false);
    }
}
