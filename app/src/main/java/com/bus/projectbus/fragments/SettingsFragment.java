package com.bus.projectbus.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bus.projectbus.R;


public class SettingsFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.settings_fragment,
                container, false);



        return v;
    }



}
