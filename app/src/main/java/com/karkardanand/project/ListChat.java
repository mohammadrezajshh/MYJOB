package com.karkardanand.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class ListChat extends AppCompatActivity {
RecyclerView recyclerView;
private Listechatadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_list_chat );
        recyclerView = findViewById( R.id.recyclerchat );
        jsonparse();
    }

    private void jsonparse() {
        StringRequest stringRequest = new StringRequest( Request.Method.GET, "", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );

    }
}