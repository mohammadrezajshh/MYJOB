package com.karkardanand.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity {
    private Profileadapter profileadapter;
String naemepeo , emailpro,skilspro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bt_nav);
        bottomNavigationView.setSelectedItemId(R.id.bn_Profail);
        TextView textView = findViewById( R.id.text_eddit );
        TextView name = findViewById( R.id.trtname );
        TextView email = findViewById( R.id.trtemail );
        TextView skils = findViewById( R.id.skil_prof );
        ImageView profile= findViewById( R.id.imageprof );
        textView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EdditProfail.class);
                startActivity( intent );
            }
        } );
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bn_Home:
                        startActivity(new Intent(getApplicationContext(),
                                Home.class));
                        overridePendingTransition(0,0);
                        return;
                    case R.id.bn_Profail:

                        case R.id.bn_ADdpost:
                        startActivity(new Intent(getApplicationContext(),
                                Main_activity.class));
                        overridePendingTransition(0,0);
                        return ;
                    case R.id.bn_Jobs:
                        startActivity(new Intent(getApplicationContext(),
                                My_Jobs.class));
                        overridePendingTransition(0,0);

                        return ;
                    case R.id.bn_Search_job:
                        startActivity(new Intent(getApplicationContext(),
                                Search_jobs.class));
                        overridePendingTransition(0,0);
                        return ;

                }

            }
        });
        jsonparse();
    }

    private void jsonparse() {
        StringRequest stringRequest = new StringRequest( Request.Method.GET, "", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jo = null;
                try {
                    jo = new JSONObject( response );
                    JSONArray jsonArray = jo.getJSONArray( "posts" );

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