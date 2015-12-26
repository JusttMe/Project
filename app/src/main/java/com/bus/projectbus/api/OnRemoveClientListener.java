package com.bus.projectbus.api;

import com.bus.projectbus.entity.RouteEntity;

/**
 * Created by End on 21-Dec-15.
 */
public interface OnRemoveClientListener {
    void onRemoveClient(boolean b);
    void onRemoveClient(int pos);

}
