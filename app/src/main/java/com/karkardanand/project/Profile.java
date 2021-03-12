package com.karkardanand.project;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class Profile extends AppCompatActivity {
    private Profileadapter profileadapter;
    private static Context mContext;
String naemepeo , emailpro,skilspro;
    private Profileadapter adapter;
    TextView email , name , family;
    TextView skils;
    ImageView profile;
    String namejson,emailjson,skiljson,familyjson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bt_nav);
        bottomNavigationView.setSelectedItemId(R.id.bn_Profail);
        TextView edditprof = findViewById( R.id.text_eddit );
         name = findViewById( R.id.trtname );
         email = findViewById( R.id.trtemail );
         skils = findViewById( R.id.skil_prof );
         family = findViewById( R.id.trtfamily );
         namejson = name.toString();
         familyjson = family.toString();
         emailjson = name.toString();
         skiljson = name.toString();

        profile= findViewById( R.id.imageprof );
        edditprof.setOnClickListener( new View.OnClickListener() {
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
    public static Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    private void jsonparse() {
        StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://185.255.89.127:8081/jobapi/getPrpfailAndPosts/", new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(String response) {
                JSONObject jo = null;
                JSONArray json = null;
                final ArrayList<ProfailMoudel> prof = new ArrayList<>();
                try {
                    jo = new JSONObject( response );
                    JSONObject jsonObject = null;
                    JSONObject jsonObject1 = null;
                    json = (jo.getJSONArray("postsList"));
                    jsonObject = jo.getJSONObject( "profailDict" );
                    jsonObject1 = jsonObject.getJSONObject( "infoData" );
                    JSONArray jsonArray = jo.getJSONArray( "postList" );
                    String status = jo.getString( "status" );
                    switch (status){
                        case "ok":
                            namejson =jsonObject.getString("firstname");
                            emailjson =jsonObject.getString("email");
                            skiljson =jsonObject1.getString("skill");
                            familyjson =jsonObject.getString("lastName");
                            Glide.with(mContext).load(jsonObject.getString("picture")).into(profile);
                            try {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObjectt = jsonArray.getJSONObject( i );
                                    prof.add( new ProfailMoudel(
                                           jsonObjectt.getString( "id" ),jsonObjectt.getString( "photo" )));
                                }
                                adapter = new Profileadapter( prof );
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

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