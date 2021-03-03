package com.karkardanand.project;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    Button btnjoinnow,btnForget,btnsignin,btnGSign;
    EditText edtEmail,edtPass;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 1;
    String email , pass, user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btnjoinnow=(Button)findViewById(R.id.btnjoinnow);
        btnForget=(Button)findViewById(R.id.btnForget);
        btnsignin=(Button)findViewById(R.id.btnsignin);
        btnGSign=(Button)findViewById(R.id.btnGSign);
        edtEmail=(EditText) findViewById(R.id.edtEmail);
        edtPass=(EditText) findViewById(R.id.edtPass);
        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
    }
    public void btnjoinnow(View view){
        startActivitys.set(SignInActivity.this,btnjoinnow,new Intent(SignInActivity.this,LoginActivity.class));
    }
    public void btnForget(View view){
        startActivitys.set(SignInActivity.this,btnForget,new Intent(SignInActivity.this,ForgotPasswordActivity.class));
    }
    public void btnsignin(View view){
        if (edtEmail.getText().toString().equals("")||edtPass.getText().toString().equals("")){
            Alert.shows(SignInActivity.this,"","Please enter your email address and password","OK","");
        }else {
            email = edtEmail.getText().toString();
            pass = edtPass.getText().toString();
            jsonparse();
            //send req with email and path
        }
    }

    private void jsonparse() {
        StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://apk-trt.ir/client/api/v1/api.php?", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonsms = null;
                try {
                    jsonsms = new JSONObject( response );
                    String status = jsonsms.getString( "status" );
                    switch (status){
                        case "ok":
                            user_id = jsonsms.getString( "user_id" );
                            if (jsonsms.getString( "act_state" ).equals( "1" )) {
                                Intent intent = new Intent(getApplicationContext(),Main_activity.class).putExtra( "user_id",user_id );
                                startActivity( intent );

                            }else {
                                Toast.makeText( SignInActivity.this, "Your email has not been activated", Toast.LENGTH_SHORT ).show();
                                Intent intent = new Intent( getApplicationContext(), Add_codeEmail.class );
                                startActivity( intent );
                            }
                            break;
                        default:
                            Alert.shows(SignInActivity.this,"","Please enter your email and password correctly","OK","");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText( SignInActivity.this, "Please check your conection", Toast.LENGTH_SHORT ).show();

            }
        } ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("command","Login");
                params.put( "email",email );
                params.put( "password",pass );
                return params;
            }
        };
    }

    public void btnGSign(View view){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            OptionalPendingResult<GoogleSignInResult> opr= Auth.GoogleSignInApi.silentSignIn(googleApiClient);
            if(opr.isDone()){
                GoogleSignInResult result=opr.get();
                handleSignInResult(result);
            }else{
                opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                    @Override
                    public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                        handleSignInResult(googleSignInResult);
                    }
                });
            }
        }
    }

    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account=result.getSignInAccount();
           //edtEmail.setText(account.getEmail());
            Toast.makeText(this, account.getEmail(), Toast.LENGTH_SHORT).show();
            //send it to server and get token
            startActivitys.set(SignInActivity.this,btnGSign,new Intent(SignInActivity.this,DashbordActivity.class));
        }else{
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

//    private void gotoProfile(){
//        Intent intent=new Intent(LoginActivity.this,JoinNowEmailActivity.class).putExtra("hi","hi");
//        startActivity(intent);
//    }
}