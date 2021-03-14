package com.karkardanand.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.load.data.InputStreamRewinder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreatRequest extends AppCompatActivity {
EditText description , time  , money;
Button send;
String project_id = "",sttim,stmon,des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_request);
        description = findViewById(R.id.et_description);
        time = findViewById(R.id.et_time);

        SharedPreferences mPrefs = getSharedPreferences("MY_APP", MODE_PRIVATE); //add key
        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        final String token = mPrefs.getString("token", null);
        money = findViewById(R.id.et_money);
        send = findViewById(R.id.send_req0);
        stmon= money.getText().toString();
        sttim= time.getText().toString();
        des= description.getText().toString();
        Intent intent = getIntent();
        project_id = intent.getStringExtra("projectId");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://185.255.89.127:8081/jobapi/creatRequest/", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");
                            switch (status){
                                case "ok":
                                    Toast.makeText(CreatRequest.this, "Your request has been successfully submitted", Toast.LENGTH_SHORT).show();
                                    startActivitys.set(getApplicationContext(),send,new Intent(getApplicationContext(),Viewproject.class));
                                    break;
                                default:
                                    Toast.makeText(CreatRequest.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put( "token",token );
                        params.put( "projectId",project_id );
                        params.put( "description",des );
                        params.put( "money",stmon );
                        params.put( "duration",sttim );
                        return params;
                    }
                };
            }
        });

    }
}