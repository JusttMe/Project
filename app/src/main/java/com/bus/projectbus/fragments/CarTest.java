package com.bus.projectbus.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bus.projectbus.R;

/**
 * Created by End on 24-Dec-15.
 */
public class CarTest extends Fragment {
    private ToggleButton seat1, seat2,seat3,seat4,seat5,seat6,seat7,seat8,seat9,seat10,seat11,
            seat12,seat13,seat14,seat15,seat16;
    private ToggleButton [] tbArray = {seat1, seat2,seat3,seat4,seat5,seat6,seat7,seat8,seat9,seat10,seat11,
            seat12,seat13,seat14,seat15,seat16};
    private Integer [] tbIds = {R.id.seat1, R.id.seat2,R.id.seat3,R.id.seat4,R.id.seat5,R.id.seat6,
            R.id.seat7,R.id.seat8,R.id.seat9,R.id.seat10,R.id.seat11,
            R.id.seat12,R.id.seat13,R.id.seat14,R.id.seat15,R.id.seat16};

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.bus_test, container, false);
        View.OnClickListener myOCL = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "kek", Toast.LENGTH_SHORT).show();
            }
        };
        for (int i = 0; i < 16; i++) {
            tbArray[i] = (ToggleButton) v.findViewById(tbIds[i]);
            tbArray[i].setOnClickListener(myOCL);

        }

        return v;
    }


}
