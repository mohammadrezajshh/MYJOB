package com.karkardanand.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;

public class Add_password extends AppCompatActivity {
    TextInputLayout inputTexttpass;
String password = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_password );
        Intent intent = getIntent();
        password=intent.getStringExtra( "password" );
        EditText oldpass = findViewById( R.id.codpass );
        String oldes = oldpass.getText().toString();
        TextView text=findViewById( R.id.txttf );
        inputTexttpass = findViewById( R.id.inputTexttpass );
        EditText newpass = findViewById( R.id.newpass );
        String newpasss =newpass.getText().toString();
        if (password==oldes){
            text.setVisibility( View.VISIBLE );
            newpass.setVisibility( View.VISIBLE );
        }else {
            Alert.shows( getApplicationContext(),"Erorr","plese enter cod email","OK","" );
        }
        jsonparse();



    }

    private void jsonparse() {
        StringRequest stringRequest = new StringRequest( Request.Method.POST, "\"http://185.255.89.127:8081/jobapi/getPassword/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
    }
}