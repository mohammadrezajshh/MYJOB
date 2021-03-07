package com.karkardanand.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        final TextView tv1 = (TextView) findViewById( R.id.tv1 );
        String str = android.os.Build.MODEL;
        tv1.setText( str );
        ImageView img = (ImageView) findViewById( R.id.img );
        final Bitmap largeIcon = BitmapFactory.decodeResource( getResources(), R.drawable.logo );
        ImageHelper imageHelper = new ImageHelper();
        img.setImageBitmap( imageHelper.getRoundedCornerBitmap( largeIcon, 50 ) );
        Animation a = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.animzoomin );
        a.reset();
        img.clearAnimation();
        img.startAnimation( a );
        Handler handler = new Handler();
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent( MainActivity.this, LoginActivity.class );
                startActivity( intent );

            }


//                StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://apk-trt.ir/client/api/v1/api.php?", new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e( "response",response );
//                        Token_id = response;
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                } ){
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String , String> params = new HashMap<String,String>();
//                        Log.e( "eeee",token );
//                        params.put( "token", String.valueOf( token));
//                        params.put( "command", "sendRegistrationToServer");
//                        params.put( "data", String.valueOf( tv1 ) );
//                        params.put( "time",getDate( getTimeoutMs() ) );
//                        return params;
//                    }
//                };


        }, 5000 );
    }
}

//        class sendregister {
//            public void sendit(Context context, String token) {
//                String url = "http://apk-trt.ir/client/api/v1/api.php?command=sendRegistrationToServer&token=" + token + "&device_name=" + getDeviceName() + "&time_stmp=" + String.valueOf( System.currentTimeMillis() );
//                StringRequest stringRequest = new StringRequest( Request.Method.GET, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                try {
//                                    String token_id = new JSONObject( response ).getString( "token_id" );
//                                    Intent intent = new Intent( MainActivity.this, LoginActivity.class ).putExtra( "token_id", token_id );
//                                    startActivity( intent );
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//
//                } );
//                RequestQueue requestQueue = Volley.newRequestQueue( context );
//                requestQueue.add( stringRequest );
//            }
//
//            public String getDeviceName() {
//                String manufacturer = Build.MANUFACTURER;
//                String model = Build.MODEL;
//                if (model.startsWith( manufacturer )) {
//                    return capitalize( model );
//                } else {
//                    return capitalize( manufacturer ) + " " + model;
//                }
//            }
//
//
//            private String capitalize(String s) {
//                if (s == null || s.length() == 0) {
//                    return "";
//                }
//                char first = s.charAt( 0 );
//                if (Character.isUpperCase( first )) {
//                    return s;
//                } else {
//                    return Character.toUpperCase( first ) + s.substring( 1 );
//                }
//
//            }
//        }
//    }
//
//    public String encode (String key, String data) throws
//    NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
//        Mac sha256_HMAC = Mac.getInstance( "HmacSHA256" );
//        SecretKeySpec secret_key = new SecretKeySpec( key.getBytes( "UTF-8" ), "HmacSHA256" );
//        sha256_HMAC.init( secret_key );
//
//        final byte[] mac_data = sha256_HMAC.doFinal( data.getBytes( "UTF-8" ) );
//        Log.e( "mac_sec", String.valueOf( mac_data ) );
//
//        return getHex( mac_data );
//
//
//    }
//
////    regIds=new encode("7823054c1ca1aa8822scfb248g4f9554a4ss9b8edd6ce2aed471852f4bc60ae9f",deviceName+new Date().getTime());
//    private String getHex(byte[] mac_data) {
//        if (mac_data == null) {
//            return null;
//        }
//        final StringBuilder hex = new StringBuilder(2 * mac_data.length);
//        for (final byte b : mac_data) {
//            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
//        }
//        Log.e("hex_sec", String.valueOf(hex));
//        token = hex.toString();
//        return hex.toString();
//    }
//    private String getDate(long time) {
//        Calendar cal = Calendar.getInstance( Locale.ENGLISH);
//        cal.setTimeInMillis(time * 1000);
//        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
//        return date;
//    }
//}


