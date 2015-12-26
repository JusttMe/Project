package com.bus.projectbus.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bus.projectbus.entity.CarsDescribe;
import com.bus.projectbus.entity.CarsEntity;
import com.bus.projectbus.entity.TownsEntity;
import com.bus.projectbus.entity.RouteEntity;
import com.bus.projectbus.entity.UserEntity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by End on 20-Dec-15.
 */
public class Api {
    public static final String SAVE_TOKEN = "token";
    public static final String URL ="http://bus-nikichxp.rhcloud.com/api/";
    public static final String TEST_GET_CARS ="{\"cars\":[{\"id\":\"1\",\"brand\":\"name\",\"seats\":50},{\"id\":\"2\",\"brand\":\"name\",\"seats\":50}]}";
    public static final String TEST_GET_TOWNS ="{\"towns\":[{\"id\":\"1\",\"name\":\"town1\"},{\"id\":\"2\",\"name\":\"town2\"}]}";

    RequestQueue mQueue;

    JsonObjectRequest jsonObjectRequest;
    private Context ctx;
    private OnRegisterListener mRegisterListener;
    private OnAuthListener mAuthListener;
    private OnStartRouteListener mStartRouteListener;
    private OnGetRouteListener mGetRouteListener;
    private OnAddClientListener mAddClientListener;
    private OnRemoveClientListener mRemoveClientListener;
    private OnGetCarListener mGetCarListener;
    private OnGetTownsListener mGetTownsListener;
    private Gson gson;
    public RouteEntity mRoute;
    public Api(Context context){

     mQueue = Volley.newRequestQueue(context);
        ctx = context;
    }
    public  void auth(String login, String pass){
        gson = new Gson();

       String URL_Request = URL+"user/auth?" +
               "login="+login+
               "&pass="+pass;
        Log.d("q auth", URL_Request);
       jsonObjectRequest =
               new JsonObjectRequest(Request.Method.GET, URL_Request, new Response.Listener<JSONObject>() {
                   @Override
                   public void onResponse(JSONObject response) {
                       try {
                           JSONObject js = response;
                           String s = js.getString("error");
                           Toast.makeText(ctx, "Error: "+s, Toast.LENGTH_SHORT).show();
                       } catch (JSONException e) {
                           if (e.getMessage().equals("No value for error")){
                               UserEntity user = gson.fromJson(response.toString(), UserEntity.class);

                               Log.d("TEST", user.getInfo());
                               mAuthListener.onAuth(user);
                           } else Toast.makeText(ctx, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                       }

                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       try {
                           Log.d("error", error.getMessage());
                       }catch (NullPointerException e){
                           Toast.makeText(ctx, "Server error", Toast.LENGTH_SHORT).show();
                       }
                   }
               });
       mQueue.add(jsonObjectRequest);
           }

    public  void register(UserEntity user){
        gson = new Gson();

        String URL_Request = URL+"user/reg?" +
                "login="+user.getLogin()+
                "&pass="+user.getPassword()+
                "&name="+user.getName()+
                "&surname="+user.getSurname();
        Log.d("q reg", URL_Request);
        jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, URL_Request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject js = response;
                            String s = js.getString("error");
                            Toast.makeText(ctx, "Error: "+s, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            if (e.getMessage().equals("No value for error")){
                                UserEntity ue = gson.fromJson(response.toString(), UserEntity.class);
                                String token = ue.getToken();
                                Log.d("TEST", ue.getInfo());
                                mRegisterListener.onRegister(token);
                            } else Toast.makeText(ctx, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            Log.d("error", error.getMessage());
                        }catch (NullPointerException e){
                            Toast.makeText(ctx, "Server error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        mQueue.add(jsonObjectRequest);
    }

    public  void getCars(){
        gson = new Gson();

        String URL_Request = URL+"data/getCar";
        Log.d("q getCar", URL_Request);
        jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, URL_Request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject js = response;
                            String s = js.getString("error");
                            Toast.makeText(ctx, "Error: "+s, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            if (e.getMessage().equals("No value for error")){
                                CarsEntity cars = gson.fromJson(response.toString(), CarsEntity.class);

                                mGetCarListener.onGetCar(cars);
                            } else Toast.makeText(ctx, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            Log.d("error", error.getMessage());
                        }catch (NullPointerException e){
                            Toast.makeText(ctx, "Server error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        mQueue.add(jsonObjectRequest);
    }

    public  void getTowns(){
        gson = new Gson();

        String URL_Request = URL+"data/getTowns";
        Log.d("q getTowns", URL_Request);
        jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, URL_Request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject js = response;
                            String s = js.getString("error");
                            Toast.makeText(ctx, "Error: "+s, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            if (e.getMessage().equals("No value for error")){
                                TownsEntity towns = gson.fromJson(response.toString(), TownsEntity.class);

                                mGetTownsListener.onGetTowns(towns);
                            } else Toast.makeText(ctx, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            Log.d("error", error.getMessage());
                        }catch (NullPointerException e){
                            Toast.makeText(ctx, "Server error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        mQueue.add(jsonObjectRequest);

    }

    public  void startRoute(RouteEntity route, String carId, String token){
        gson = new Gson();
        CarsDescribe cd = route.getCar().get(0);
        String URL_Request = URL+"route/start?"+
                "from=" + route.getFrom()+
                "&to=" +route.getTo()+
                "&carId=" +cd.getId()+
                "&tripNumber=" +route.getTripNumber()+
                "&token="+token;
        Log.d("q startRoute", URL_Request);
        jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, URL_Request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject js = response;
                            String s = js.getString("error");
                            Toast.makeText(ctx, "Error: "+s, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            if (e.getMessage().equals("No value for error")){
                                try {
                                    JSONObject js = response;
                                    String s = js.getString("success");
                                    mStartRouteListener.onStartRoute(true);
                                } catch (JSONException e1) {
                                    Toast.makeText(ctx, "Error: "+e1.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            } else Toast.makeText(ctx, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            Log.d("error", error.getMessage());
                        }catch (NullPointerException e){
                            Toast.makeText(ctx, "Server error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        mQueue.add(jsonObjectRequest);

    }

    public  void getRoute(String token){
        gson = new Gson();

        String URL_Request = URL+"rote/getRoute?token="+token;
        Log.d("q getRoute", URL_Request);
        jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, URL_Request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject js = response;
                            String s = js.getString("error");
                            Toast.makeText(ctx, "Error: "+s, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            if (e.getMessage().equals("No value for error")){
                                RouteEntity routeEntity = gson.fromJson(response.toString(), RouteEntity.class);

                                mGetRouteListener.onGetRoute(routeEntity);
                            } else Toast.makeText(ctx, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            Log.d("error", error.getMessage());
                        }catch (NullPointerException e){
                            Toast.makeText(ctx, "Server error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        mQueue.add(jsonObjectRequest);

    }

    public  void addClient(String token, final int seatNumber){
        gson = new Gson();
        String URL_Request = URL+"route/addClient?"+
                "token="+token+
                "&seatNumber="+seatNumber;
        Log.d("q addClient", URL_Request);
        jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, URL_Request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject js = response;
                            String s = js.getString("error");
                            Toast.makeText(ctx, "Error: "+s, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            if (e.getMessage().equals("No value for error")){
                                try {
                                    JSONObject js = response;
                                    String s = js.getString("success");
                                    mAddClientListener.onAddClient(true);
                                } catch (JSONException e1) {
                                    Toast.makeText(ctx, "Error: "+e1.getMessage(), Toast.LENGTH_SHORT).show();
                                    mAddClientListener.onAddClient(seatNumber);
                                }

                            } else {
                                mAddClientListener.onAddClient(seatNumber);
                                Toast.makeText(ctx, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            Log.d("error", error.getMessage());
                        }catch (NullPointerException e){
                            Toast.makeText(ctx, "Server error", Toast.LENGTH_SHORT).show();
                        } mAddClientListener.onAddClient(seatNumber);
                    }
                });
        mQueue.add(jsonObjectRequest);

    }

    public  void removeClient(String token, final int seatNumber){
        gson = new Gson();
        String URL_Request = URL+"route/removeClient?"+
                "token="+token+
                "&seatNumber="+seatNumber;
        Log.d("q removeClient", URL_Request);
        jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, URL_Request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject js = response;
                            String s = js.getString("error");
                            Toast.makeText(ctx, "Error: "+s, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            if (e.getMessage().equals("No value for error")){
                                try {
                                    JSONObject js = response;
                                    String s = js.getString("success");
                                    mRemoveClientListener.onRemoveClient(true);
                                } catch (JSONException e1) {
                                    Toast.makeText(ctx, "Error: "+e1.getMessage(), Toast.LENGTH_SHORT).show();
                                    mRemoveClientListener.onRemoveClient(seatNumber);
                                }

                            } else {
                                mRemoveClientListener.onRemoveClient(seatNumber);
                                Toast.makeText(ctx, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            Log.d("error", error.getMessage());
                        }catch (NullPointerException e){
                            Toast.makeText(ctx, "Server error", Toast.LENGTH_SHORT).show();
                        }mRemoveClientListener.onRemoveClient(seatNumber);
                    }
                });
        mQueue.add(jsonObjectRequest);

    }

    public void setOnAuthListener(OnAuthListener listener){
        mAuthListener = listener;
    }
    public void setOnRegistryListener(OnRegisterListener listener){
        mRegisterListener = listener;
    }
    public void setOnStartRouteListener(OnStartRouteListener listener) {
        mStartRouteListener = listener;
    }
    public void setOnGetRouteListener(OnGetRouteListener listener) {
        mGetRouteListener = listener;
    }
    public void setOnAddClientListener(OnAddClientListener listener) {
        mAddClientListener = listener;
    }
    public void setOnRemoveClientListener(OnRemoveClientListener listener) {
        mRemoveClientListener = listener;
    }
    public void setOnGetCarListener(OnGetCarListener listener) {
        mGetCarListener = listener;
    }
    public void setOnGetPointsListener(OnGetTownsListener listener) {
        mGetTownsListener = listener;
    }
}

