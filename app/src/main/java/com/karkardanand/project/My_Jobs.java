package com.karkardanand.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class My_Jobs extends AppCompatActivity {
    FloatingActionButton fb;
    RecyclerView recyclerView;
    MyjobsAdapter adapter;
    String user_id = "";
    String a = "1";
    String token = "";
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__jobs);
        fb= findViewById( R.id.fab );
        SharedPreferences shared = getSharedPreferences("MY_APP", MODE_PRIVATE);
        String channel = (shared.getString("token", token));
        Intent intent = getIntent();
        user_id=intent.getStringExtra( "user_id" );
        recyclerView = findViewById(R.id.recyclermyjob);
        fb.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreatProject.class).putExtra( "user_id",user_id );
                startActivity( intent );
            }
        } );
        BottomNavigationView bottomNavigationView = findViewById(R.id.bt_nav);
        bottomNavigationView.setSelectedItemId(R.id.bn_Jobs);
//        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
//        fab.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent b9 = new Intent( My_Jobs.this, Add_Job.class );
//                startActivity( b9 );
//
//            }
//        } );
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
                        startActivity(new Intent(getApplicationContext(),
                                Profile.class));
                        overridePendingTransition(0,0);
                        return ;
                    case R.id.bn_ADdpost:
                        startActivity(new Intent(getApplicationContext(),
                                Main_activity.class));
                        overridePendingTransition(0,0);
                        return ;
                    case R.id.bn_Jobs:

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

//        for (int i = 0 ;i<notif.size();i++) {
//            notif.add( new NotifMoudel( title, money, photo, id ) );



        final StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://185.255.89.127:8081/jobapi/myProject/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        final ArrayList<MyjobsMoudel> myjobsMoudels = new ArrayList<>();
                        try {
                            JSONObject jo = new JSONObject( response );
                            JSONArray jsonArray = jo.getJSONArray( "project" );


                            try {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject( i );
                                    myjobsMoudels.add( new MyjobsMoudel( jsonObject.getLong( "id" ), jsonObject.getString( "money" ),
                                            jsonObject.getString( "title" ), jsonObject.getString( "skill" ),
                                            jsonObject.getString( "isActive" ) ) );
                                }

                                adapter = new MyjobsAdapter(context);
                                adapter.setmyjobsClickListener( (MyjobsAdapter.myjobsClickListener) My_Jobs.this );
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
                params.put( "token",token );
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public void onClick(int id) {
        Intent intent = new Intent(getApplicationContext(),Viewproject.class);
        startActivity( intent );
        //TODO my job item clicked
    }

}