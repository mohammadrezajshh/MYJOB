package com.karkardanand.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Eddit_profile extends AppCompatActivity {
String token;
    String oldpassword , txtoldpass ,txtnewpass , txtrepetpass;
    EditText oldpass,newpass,repetpass;
    TextInputLayout newpas,repetpas;
    TextView txtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_eddit_profile );
        oldpass = findViewById( R.id.edt_oldpass);
        newpass = findViewById( R.id.edt_newpass);
        repetpass = findViewById( R.id.edt_repnewpass);
        newpas = findViewById( R.id.inputTexttpass );
        repetpas = findViewById( R.id.inputTextPhoneReg );
        txtview = findViewById( R.id.txttf);
        SharedPreferences prefs = getSharedPreferences("MY_APP", MODE_PRIVATE);
         token = prefs.getString("TOKEN",token);
         txtoldpass = oldpass.getText().toString();
         if (txtoldpass == oldpassword){
             txtview.setVisibility( View.VISIBLE );
             newpas.setVisibility( View.VISIBLE );
             repetpas.setVisibility( View.VISIBLE );
             txtnewpass = newpass.getText().toString();
             txtrepetpass = repetpass.getText().toString();
             if (txtrepetpass == txtnewpass){
                 jsonparsee();
             }else {
                 Alert.shows( getApplicationContext(),"Error","please Check your repet password","OK","" );
             }



         }else {
             Alert.shows( getApplicationContext(),"Error","please Try again","OK","" );

         }
         jsonparse();

    }

    private void jsonparsee() {
        StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://185.255.89.127:8081/jobapi/changePassword/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject js = new JSONObject(response);
                    String status = js.getString( "status" );
                    switch (status){
                        case "ok":
                            Alert.shows( getApplicationContext(),"Successfully" ,"Your email changed successfully","Thanks","");
                            Intent intent = new Intent(getApplicationContext(),Main_activity.class);
                            startActivity( intent );
                            break;
                        default:
                            Alert.shows( getApplicationContext(),"Error","please check your conection","OK","" );
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Alert.shows( getApplicationContext(),"Error","please check your conection","OK","" );
            }
        } ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put( "token",token ) ;
                params.put( "newPass",txtnewpass );
                return params;
            }
        };
    }

    private void jsonparse() {
        StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://185.255.89.127:8081/jobapi/getPassword/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString( "status" );
                    switch (status){
                        case "ok":
                             oldpassword = jsonObject.getString( "password" );
                             break;
                        default:
                            Alert.shows( getApplicationContext(),"Error","please check your conection","OK","" );

                            break;

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Alert.shows( getApplicationContext(),"Error","please check your conection","OK","" );
            }
        } ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put( "token",token ) ;
                return params;
            }
        };
    }
}