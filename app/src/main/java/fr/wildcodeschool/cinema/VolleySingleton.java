package fr.wildcodeschool.cinema;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.Consumer;
import android.support.v7.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import fr.wildcodeschool.cinema.model.User;

public class VolleySingleton {

    private static final String API_URL = "http://10.0.2.2:8080/";
    private static final String SIGN_IN_URL = API_URL + "users/signIn";
    private static final String SIGN_UP_URL = API_URL + "users/signUp";

    private static VolleySingleton instance;
    private static Context context;
    private RequestQueue requestQueue;

    private VolleySingleton(Context context) {
        VolleySingleton.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public void signUp(User user, final Consumer<User> listener) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();

        final String requestBody = gson.toJson(user);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                SIGN_UP_URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                User user = gson.fromJson(response.toString(), User.class);
                listener.accept(user);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                new AlertDialog.Builder(context)
                        .setTitle("Error")
                        .setMessage("Something went wrong !")
                        .show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            requestBody, "utf-8");
                    return null;
                }
            }
        };

        addToRequestQueue(jsonObjectRequest);
    }

    public void signIn(User user, final Consumer<User> listener) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();
        final String requestBody = gson.toJson(user);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                SIGN_IN_URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                User user = gson.fromJson(response.toString(), User.class);
                listener.accept(user);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                new AlertDialog.Builder(context)
                        .setTitle("Error")
                        .setMessage("Your email or password are invalid !")
                        .show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            requestBody, "utf-8");
                    return null;
                }
            }
        };

        addToRequestQueue(jsonObjectRequest);
    }
}
