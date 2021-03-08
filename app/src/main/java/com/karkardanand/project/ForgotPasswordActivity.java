package com.karkardanand.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class ForgotPasswordActivity extends AppCompatActivity {
    TextInputLayout inptcode;
    Button btnforgetemail,btnresend;
    EditText edtCode,edtEmail;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        btnforgetemail=(Button)findViewById(R.id.btnforgetemail);
        btnresend=(Button)findViewById(R.id.btnresend);

        edtEmail=(EditText) findViewById(R.id.edtEmail);

        email=edtEmail.getText().toString();

    }
    public void btnforgetemail(View view){
        jsonparse();
        //send req for code
        Alert.shows(ForgotPasswordActivity.this,"","We will send a reset link to your email","OK","");
//        inptcode.setVisibility(View.VISIBLE);
//        btnforgetemail.setText("Submit");


    }

    private void jsonparse() {
        StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://185.255.89.127:8081/jobapi/forgetPassword/", new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString( "status" );
                            switch (status){
                                case "OK":
                                    inptcode.setVisibility(View.VISIBLE);
                                    btnforgetemail.setText("Submit");
                                    Intent intent = new Intent(getApplicationContext(),Add_password.class);
                                    startActivity( intent );
                                    break;
                                default:
                                    Toast.makeText( ForgotPasswordActivity.this, "Erorr", Toast.LENGTH_SHORT ).show();
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
                Map<String, String> params = new HashMap<String, String>();
                params.put( "email",email );
                return params;
            }
        };
    }

    public void btnresend(View view){
        //send req for code
    }
}