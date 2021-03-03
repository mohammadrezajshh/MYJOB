package com.karkardanand.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Notification extends AppCompatActivity implements NotificationAdapter.NotifiClickListener {
    View cardView;
    String user_id = "";
    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    private NotificationAdapter exampleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_notification );
        cardView = findViewById( R.id.cardviewnotif );

        Intent intent = getIntent();
        user_id = intent.getStringExtra( "user_id" );
        jsonparse();

    }

    private void jsonparse() {

//        for (int i = 0 ;i<notif.size();i++) {
//            notif.add( new NotifMoudel( title, money, photo, id ) );


        String url = "http://apk-trt.ir/client/api/v1/api.php?command=sendRegistrationToServer&token=" + "user_id" + "13" + "notification" + "&command";
        final StringRequest stringRequest = new StringRequest( Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        final ArrayList<NotifMoudel> notif = new ArrayList<>();
                        try {
                            JSONObject jo = new JSONObject( response );
                            JSONArray jsonArray = jo.getJSONArray( "project" );


                            try {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject( i );
                                    notif.add( new NotifMoudel( jsonObject.getString( "id" ), jsonObject.getString( "Money" ),
                                            jsonObject.getString( "title" ), jsonObject.getString( "skils" ),
                                            jsonObject.getInt( "photo" ) ) );
                                }
                                adapter = new NotificationAdapter( notif );
                                adapter.setNotifiClickListener( Notification.this );
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    @Override
    public void onClick(int id) {
        //TODO notification item clicked
    }
}
