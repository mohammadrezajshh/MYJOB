package com.karkardanand.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreatProject extends AppCompatActivity {
    EditText title, info, money,time;
    AutoCompleteTextView skils;
    String title_msg,info_msg , money_msg,time_msg,skils_msg;
    String user_id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_creat_project );
        title = findViewById( R.id.title );
        info = findViewById( R.id.infoproject );
        money = findViewById( R.id.money );
        time = findViewById( R.id.time );
        skils = findViewById( R.id.skils );
        title_msg = title.getText().toString();
        info_msg = info.getText().toString();
        money_msg = money.getText().toString();
        time_msg = time.getText().toString();
        Intent intent = getIntent();
        user_id = intent.getStringExtra( "user_id" );
        jsonparse();
        AutoCompelet.setit(CreatProject.this,skils,getJobList.setindustry());
        skils_msg = skils.getText().toString();

    }

    private void jsonparse() {
        StringRequest stringRequest = new StringRequest( Request.Method.POST, "",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            String status = jsonObject.getString( "status" );
                            switch (status){
                                case "OK":
                                    Alert.shows( getApplicationContext(),"Successful","Your project has been successfully registered","Thanks","" );
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
                params.put( "command","creat_project" );
                params.put( "user_id",user_id );
                params.put( "title",title_msg );
                params.put( "info",info_msg );
                params.put( "money",money_msg );
                params.put( "time",time_msg );
                params.put( "skills",skils_msg );

                return params;
            }
        };
    }
}