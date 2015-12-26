package com.bus.projectbus.fragments;

import android.app.Activity;
import android.os.Bundle;

import com.bus.projectbus.Generator;
import com.bus.projectbus.R;

/**
 * Created by End on 25-Dec-15.
 */
public class TestAct extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        getFragmentManager().beginTransaction().add(R.id.cccccc, new Generator())
                .commit();
    }
}
