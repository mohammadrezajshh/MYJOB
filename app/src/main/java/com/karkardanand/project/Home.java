package com.karkardanand.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

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

public class Home extends AppCompatActivity {
    private HomeAdapter adapter;
    ArrayList<HomeMoudel> arrayList;
    Context context;
   String token;
   ImageView chat;
   ImageView notif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );
        chat = (ImageView)findViewById(R.id.chat) ;
        notif = (ImageView)findViewById(R.id.notification) ;
        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Notification.class);
                startActivity(intent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ListChat.class);
                startActivity(intent);
            }
        });
        BottomNavigationView bottomNavigationView = findViewById( R.id.bottom_nav );
        bottomNavigationView.setSelectedItemId( R.id.bn_Home );
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
         token = prefs.getString("MY_APP", "token");

    
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bn_Home:
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
                return;

            }
        });
        jsonparse();
    }
    public static Context context() {
        return context();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void jsonparse() {

//        for (int i = 0 ;i<notif.size();i++) {
//            notif.add( new NotifMoudel( title, money, photo, id ) );



        final StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://185.255.89.127:8081/jobapi/home/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        final ArrayList<HomeMoudel> homee = new ArrayList<>();
                        try {
                            JSONObject jo = new JSONObject( response );
                            JSONArray jsonArray = jo.getJSONArray( "postsList" );


                            try {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject( i );
                                    homee.add( new HomeMoudel(
                                            jsonObject.getString( "caption" ), jsonObject.getInt( "id" ),
                                            jsonObject.getString( "profilePic" ),jsonObject.getString( "photo" ) ) );
                                }
                                adapter = new HomeAdapter( context ,arrayList );
                                adapter.setHomeClickListener( (HomeAdapter.HomeClickListener) Home.this );
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
        } )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<String, String>();
                params.put("token","c3a502127e75596d517c9fb573579025");
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

//    public void onClick(int id) {
//        String url = "http://apk-trt.ir/client/api/v1/api.php?command=sendRegistrationToServer&token=" + "user_id" + "13" + "Like" + "&command";
//        StringRequest stringRequest = new StringRequest( Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        } );
//        //TODO notification item clicked
//    }


}