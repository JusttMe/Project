package com.bus.projectbus.fragments;

        import android.app.Fragment;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.Toast;

        import com.bus.projectbus.MainActivity;
        import com.bus.projectbus.R;
        import com.bus.projectbus.api.Api;
        import com.bus.projectbus.api.OnGetCarListener;
        import com.bus.projectbus.api.OnGetTownsListener;
        import com.bus.projectbus.api.OnStartRouteListener;
        import com.bus.projectbus.entity.CarsDescribe;
        import com.bus.projectbus.entity.CarsEntity;
        import com.bus.projectbus.entity.RouteEntity;
        import com.bus.projectbus.entity.TownsDescribe;
        import com.bus.projectbus.entity.TownsEntity;

        import java.util.ArrayList;


public class StartFragment extends Fragment implements OnStartRouteListener, OnGetCarListener, OnGetTownsListener{
   private ArrayList<String> car, from, to;

    private Spinner fromPoint, toPoint, busSpinner;
    private ArrayAdapter fromPointAdapter, toPointAdapter, busSpinnerAdapter;
    String sToken;
    private Api mApi;
    private int iTripNumber;
    private String sFrom, sTo, sCar;
    private EditText tripNumberEditText;
    private CarsEntity mCarsEntity = null;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.create_route_fragment, container, false);
        mApi = new Api(getActivity());
        //mApi.getTowns();
        mApi.getCars();
        mApi.setOnStartRouteListener(this);
        mApi.setOnGetCarListener(this);
        mApi.setOnGetCarListener(this);

        car = new ArrayList<>();
        car.add("Марка автобуса");
        from = new ArrayList<>();
        from.add("Початок");
        to = new ArrayList<>();
        to.add("Кінець");

        fromPointAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, from);
        fromPointAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromPoint = (Spinner) v.findViewById(R.id.fromPoint);
        fromPoint.setAdapter(fromPointAdapter);

        toPointAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, to);
        toPointAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toPoint = (Spinner) v.findViewById(R.id.toPoint);
        toPoint.setAdapter(toPointAdapter);

        busSpinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, car);
        busSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        busSpinner = (Spinner) v.findViewById(R.id.busSpinner);
        busSpinner.setAdapter(busSpinnerAdapter);

        tripNumberEditText = (EditText) v.findViewById(R.id.tripNumberField);

        Button create = (Button) v.findViewById(R.id.createRouteBtn);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getData()){
                    Toast.makeText(getActivity(), "Перевірте данні", Toast.LENGTH_SHORT).show();
                    return;
                }
                checkFields();

                RouteEntity route = new RouteEntity(sFrom, sTo, iTripNumber);

          //      mApi.startRoute(route, sCar, sToken);
            }
        });




        return v;
    }
    private boolean checkFields() {
        if(TextUtils.isEmpty(Integer.toString(iTripNumber))) {
            tripNumberEditText.setError(getString(R.string.error_empty_field));
            return true;
        }

        return false;
    }
    public boolean getData() {

        SharedPreferences settings = getActivity().getSharedPreferences("token", 0);
        sToken = settings.getString("token", null);
        iTripNumber = Integer.parseInt(tripNumberEditText.getText().toString());

        sFrom = fromPoint.getSelectedItem().toString();
        sTo = toPoint.getSelectedItem().toString();
        String carS = busSpinner.getSelectedItem().toString();
        if (sFrom.equals("Початок")||sTo.equals("Кінець")|| carS.equals("Марка автобуса")){
            return true;
        }

        ArrayList<CarsDescribe> carsDescribe = mCarsEntity.getCars();
        sCar = Integer.toString((carsDescribe.get(busSpinner.getId()-1)).getId());
        return false;
    }

    @Override
    public void onStartRoute(boolean bool) {
        if (bool){
            MainActivity activity = (MainActivity) getActivity();
            activity.setShowFragment();
        }
    }

    @Override
    public void onGetCar(CarsEntity cars) {
        mCarsEntity = cars;
        ArrayList<String> carNew = new ArrayList<>();
        carNew.add("Марка автобуса");
        ArrayList<CarsDescribe> carsDescribe = cars.getCars();
        for (CarsDescribe cd: carsDescribe) {
            carNew.add(cd.getBrand()+" ("+cd.getSeats()+" місць)");
        }
        car = carNew;
        busSpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetTowns(TownsEntity towns) {
        from.clear();
        from.add("Початок");
        to.clear();
        to.add("Кінець");
        ArrayList<TownsDescribe> townsDescribe = towns.getTowns();
        for (TownsDescribe td: townsDescribe) {
            from.add(td.getName());
            to.add(td.getName());
        }
        fromPointAdapter.notifyDataSetChanged();
        toPointAdapter.notifyDataSetChanged();
    }
}
