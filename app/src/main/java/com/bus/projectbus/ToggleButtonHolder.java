package com.bus.projectbus;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ToggleButtonHolder extends RecyclerView.ViewHolder {
    public ToggleButton toggleButton;
    public ToggleButtonHolder(View itemView) {
        super(itemView);
        toggleButton = (ToggleButton) itemView.findViewById(R.id.tgbtn);
    }
}