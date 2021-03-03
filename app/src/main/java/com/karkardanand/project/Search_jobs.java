package com.karkardanand.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

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

public class Search_jobs extends AppCompatActivity {
    AutoCompleteTextView search;

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

    private void jsonparse() {

        String url = "http://apk-trt.ir/client/api/v1/api.php?command=sendRegistrationToServer&token=" + "user_id" + "13" + "Search" + "&command" + searcher +"search_txt" ;
        final StringRequest stringRequest = new StringRequest( Request.Method.GET, url,
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
                                            jsonObject.getString( "title" ), jsonObject.getString( "skills" ),
                                            jsonObject.getInt( "photo" ) ) );
                                }
                                adapter = new SearcheAdapter( seasrchh );
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
        } );
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public void onClick(int id) {

        //TODO notification item clicked
    }

    }



