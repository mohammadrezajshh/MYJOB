package com.karkardanand.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Viewproject extends AppCompatActivity {
TextView title;
TextView info;
TextView money;
TextView skils;
Button creatreq;
    RequestAdapter adapter;
String project_id = "";
String titlee , infoo , moneyy,skilss , id;
Context context;
private viewprojectadapter viewprojectadapter;
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_viewproject );
        Intent intent = getIntent();
        creatreq= findViewById(R.id.creatRequest);
        project_id = intent.getStringExtra( "projectid" );
        title = findViewById( R.id.title_view );
        info = findViewById( R.id.info_view );
        money = findViewById(R.id.money_view);
        skils = findViewById(R.id.skils_view);
        recyclerView = findViewById( R.id.recyclerview_view );
        jsonparse();
        title.setText( titlee );
        info.setText( infoo );
        money.setText( moneyy );
        skils.setText( skilss );
        creatreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreatRequest.class).putExtra("projectId",project_id);
                startActivity(intent);
            }
        });
        jsonparsee();
    }
//todo list of request
    private void jsonparse() {
    ;
        StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://185.255.89.127:8081/jobapi/prohectDetails/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jo = null;
                try {
                    jo = new JSONObject( response );
                    String status= jo.getString( "status" );
                    JSONObject jsonObject = jo.getJSONObject( "projectDeatail" );
                    switch(status) {
                        case "OK":
                            titlee = jsonObject.getString( "title" );
                            infoo = jsonObject.getString( "description" );
                            moneyy = jsonObject.getString( "money" );
                            skilss = jsonObject.getString( "skill" );
                            id = jsonObject.getString( "id" );
                            break;
                        default:
                            Toast.makeText( Viewproject.this, "Erorr", Toast.LENGTH_SHORT ).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put( "projectId",project_id );
                return params;
            }
        };
    }
    public static Context context() {
        return context();
    }

    public void setContext(Context context) {
        this.context = context;
    }

//todo request of recycler
    private void jsonparsee() {

        StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://185.255.89.127:8081/jobapi/getRequest/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                final ArrayList<AllRequest> allRequests = new ArrayList<>();

                    try {
                        JSONObject jo = new JSONObject( response );
                        JSONArray jsonArray = jo.getJSONArray( "allRequests" );


                        try {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject( i );
                                allRequests.add( new AllRequest( jsonObject.getLong( "id" ), jsonObject.getString( "description" ),
                                        jsonObject.getLong( "idProject" ), jsonObject.getString( "token" ),
                                        jsonObject.getString( "money" ),jsonObject.getString("duration") ) );
                            }


                            adapter = new RequestAdapter(context);

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
        } ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put( "projectId",project_id );
                return params;
            }
        };
    }
}