package com.karkardanand.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Add_codeEmail extends AppCompatActivity {
    private TextInputLayout textInputLayout;
    EditText pass, edtpassword;
    Button btnEmailJoin;
    private Intent intent;
    String passs ,passcod;
    String token= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_code_email );
        textInputLayout = (TextInputLayout) findViewById( R.id.inputTextPhoneRe );
        pass = (EditText) findViewById( R.id.edtPass );
        btnEmailJoin = (Button) findViewById( R.id.btnEmailJoin );
        intent = getIntent();
        passs = pass.getText().toString();
        token=intent.getStringExtra( "token");
        jsonparsee();
        if (passs == passcod){
            jsonparse();
        }else {
            Alert.shows( getApplicationContext(),"Try Again","Please enter the opacity correctly ","OK","" );
        }

    }

    private void jsonparsee() {
        StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://185.255.89.127:8081/jobapi/getPassword/", new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            try {

                JSONObject json = new JSONObject( response );
                String status = json.getString( "status" );
                switch (status){
                    case "ok":
                        passcod = json.getString( "password" );
                        Alert.shows( Add_codeEmail.this,"Successful","Your registration has been completed successfully","OK","" );
                        break;
                    default:
                        Alert.shows( Add_codeEmail.this,"Wrong","Email is wrong","ok","" );

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
            params.put( "token",token );
            return params;
        }
    };
    }


    public void btnEmailJoin(View view) throws JSONException {
        Intent intent = new Intent(getApplicationContext(),Main_activity.class);
        startActivity( intent );
        if (textInputLayout.getVisibility() == View.GONE) {
            jsonparse();
            } else {
                Alert.shows( Add_codeEmail.this, "", "Email is wrong", "OK", "" );
            }



        }

    private void jsonparse() {


        StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://185.255.89.127:8081/jobapi/checkCode/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonsms = new JSONObject( response );
                    String status = jsonsms.getString( "status" );
                    switch (status){
                        case "ok":
                            Alert.shows( Add_codeEmail.this,"Successful","Your registration has been completed successfully","OK","" );
                            Intent intent = new Intent(getApplicationContext(),Main_activity.class );
                            startActivity( intent );
                            break;
                        default:
                            Alert.shows( Add_codeEmail.this,"Wrong","Email is wrong","ok","" );

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
                params.put( "token",token );
                params.put( "code",passs );
                return params;
            }
        };
    }


    private boolean isEmailValidd(String toString) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile( expression, Pattern.CASE_INSENSITIVE );
        Matcher matcher = pattern.matcher( toString );
        return matcher.matches();
    }
}