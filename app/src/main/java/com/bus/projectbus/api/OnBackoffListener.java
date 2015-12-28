package com.bus.projectbus.api;

import com.android.volley.toolbox.JsonObjectRequest;

/**
 * Created by End on 28-Dec-15.
 */
public interface OnBackoffListener {
    void  onBackoff(boolean b, JsonObjectRequest request);

}