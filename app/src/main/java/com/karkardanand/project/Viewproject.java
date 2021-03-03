package com.karkardanand.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Viewproject extends AppCompatActivity {
TextView title;
TextView info;
TextView money;
TextView skils;
String titlee , infoo , moneyy,skilss , id;
private viewprojectadapter viewprojectadapter;
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_viewproject );
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
        jsonparsee();
    }
//todo list of request
    private void jsonparsee() {
        String url ="http://apk-trt.ir/client/api/v1/api.php?command=sendRegistrationToServer&token=" + "user_id" + "13" + "view_project" + "&command";
        StringRequest stringRequest = new StringRequest( Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jo = null;
                try {
                    jo = new JSONObject( response );
                    String status= jo.getString( "status" );
                    switch(status) {
                        case "OK":
                            titlee = jo.getString( "title" );
                            infoo = jo.getString( "info" );
                            moneyy = jo.getString( "money" );
                            skilss = jo.getString( "skills" );
                            id = jo.getString( "id" );
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
        } );
    }


    private void jsonparse() {
        String url ="http://apk-trt.ir/client/api/v1/api.php?command=sendRegistrationToServer&token=" + "user_id" + "13" + "view_project" + "&command";
        StringRequest stringRequest = new StringRequest( Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jo = null;
                try {
                    jo = new JSONObject( response );
                    String status= jo.getString( "status" );
                    switch(status) {
                        case "OK":



                            titlee = jo.getString( "title" );
                            infoo = jo.getString( "info" );
                            moneyy = jo.getString( "money" );
                            skilss = jo.getString( "skills" );
                            id = jo.getString( "id" );
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
        } );
    }
}