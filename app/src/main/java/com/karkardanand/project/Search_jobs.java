package com.karkardanand.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Search_jobs extends AppCompatActivity {
    AutoCompleteTextView search;
    Context context;
    ImageView chat;
    ImageView notif;
    String searcher;
    private SearcheAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_jobs);
        notif = findViewById( R.id.notification );
        chat = findViewById( R.id.chat );

         search = findViewById( R.id.eddittextserch );
        AutoCompelet.setit(Search_jobs.this,search,getJobList.setindustry());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bt_nav);
        bottomNavigationView.setSelectedItemId(R.id.bn_Search_job);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.bn_Home:
                            startActivity(new Intent(getApplicationContext()
                                    ,Home.class));
                            overridePendingTransition(0,0);
                            return;
                        case R.id.bn_Jobs:
                            startActivity(new Intent(getApplicationContext()
                                    ,My_Jobs.class));
                            overridePendingTransition(0,0);
                            return ;
                        case R.id.bn_ADdpost:
                            startActivity(new Intent(getApplicationContext()
                                    ,Main_activity.class));
                            overridePendingTransition(0,0);
                            return;
                        case R.id.bn_Search_job:
                        case R.id.bn_Profail:
                            startActivity(new Intent(getApplicationContext()
                                    ,Profile.class));
                            overridePendingTransition(0,0);



                    }
                    return;
                }
            });
        chat.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(),ChatActivity.class) );
            }
        } );
        notif.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Notification.class);
                startActivity( intent );
            }
        } );
        AutoCompelet.setit(Search_jobs.this,search,getJobList.setindustry());
        searcher=search.toString();

        jsonparse();

    }
    public static Context context() {
        return context();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void jsonparse() {

        final StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://185.255.89.127:8081/jobapi/searchJob/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        final ArrayList<SearchMoudel> seasrchh = new ArrayList<>();
                        try {
                            JSONObject jo = new JSONObject( response );
                            JSONArray jsonArray = jo.getJSONArray( "projects" );


                            try {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject( i );
                                    seasrchh.add( new SearchMoudel( jsonObject.getString( "id" ), jsonObject.getString( "Money" ),
                                            jsonObject.getString( "title" ),
                                            jsonObject.getString( "photo" ) ) );
                                }
                                adapter = new SearcheAdapter( context,seasrchh );
                                adapter.setSearchListener( (SearcheAdapter.SearchListener) Search_jobs.this );
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
        } ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("skill", searcher);

                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public void onClick(int id) {

        //TODO notification item clicked
    }

    }



