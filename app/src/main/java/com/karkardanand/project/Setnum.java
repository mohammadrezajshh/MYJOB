package com.karkardanand.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.effect.Effect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Setnum extends AppCompatActivity {
    EditText editText;
    TextView recent;
    Button bt_send;
    String code;
    String email = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_setnum );
        editText = findViewById( R.id.code_email );
        recent = findViewById( R.id.Recent_code );
        bt_send = findViewById( R.id.btncodeJoin );
        code = editText.getText().toString();
        Intent intent = getIntent();
        email = intent.getStringExtra("email" );
        recent.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send again cod
                Toast.makeText( Setnum.this, "send Again", Toast.LENGTH_SHORT ).show();
                StringRequest stringRequest = new StringRequest( Request.Method.POST, "", new Response.Listener<String>() {
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
                        Map<String, String> params = new HashMap<String, String>();
                        params.put( "email",email );
                        params.put( "Cod",code );
                        return params;
                    }
                };
            }
        } );



    }

}