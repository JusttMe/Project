package com.bus.projectbus.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bus.projectbus.Generator;
import com.bus.projectbus.R;
import com.bus.projectbus.api.Api;
import com.bus.projectbus.api.OnGetRouteListener;
import com.bus.projectbus.entity.RouteEntity;

import java.util.ArrayList;


public class ShowFragment extends Fragment implements OnGetRouteListener {

    private Api mApi;
    private String sToken;
    private TextView mFrom, mTo;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.show_route_fragment,
                container, false);

        mFrom = (TextView) v.findViewById(R.id.fromPoint);
        mTo = (TextView) v.findViewById(R.id.toPoint);
        mApi = new Api(getActivity());
        mApi.setOnGetRouteListener(this);
        SharedPreferences settings = getActivity().getSharedPreferences("token", 0);
        sToken = settings.getString("token", null);
        getActivity().getFragmentManager().beginTransaction().add(R.id.schemeContainer, new Generator())
                .commit();
       // mApi.getRoute(sToken);
        return v;
    }



    @Override
    public void onGetRoute(RouteEntity routeEntity) {
        mFrom.setText(routeEntity.getFrom());
        mTo.setText(routeEntity.getTo());
        getFragmentManager().beginTransaction().add(R.id.schemeContainer, new CarTest())
                .commit();
    }

}
