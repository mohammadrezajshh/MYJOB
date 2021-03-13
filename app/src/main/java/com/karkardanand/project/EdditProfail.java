package com.karkardanand.project;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class EdditProfail extends AppCompatActivity {
    EditText name, famili, email;
    private static final int IMAGE_PICK_CODE = 10;
    TextView changephoto;
    static String pathimage;
    ImageView imageView;
    AutoCompleteTextView job, skill, country;
    Context context;
    private JSONObject json, jsonn;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eddit_profail);
        TextView changepass = findViewById(R.id.changepass);
        name = findViewById(R.id.trt_name_eddit);
        famili = findViewById(R.id.trt_family_);
        job = findViewById(R.id.changejob);
        email = findViewById(R.id.trt_email_eddit);
        skill = findViewById(R.id.skil_eddit);
        country = findViewById(R.id.changeCountry);
        imageView = findViewById(R.id.image_prof_eddit);
        changephoto = findViewById(R.id.changephoto);
        AutoCompelet.setit(EdditProfail.this, job, getJobList.setjobs());
        AutoCompelet.setit(EdditProfail.this, country, GetCountry.getc());
        AutoCompelet.setit(EdditProfail.this, skill, getJobList.setindustry());

        changepass.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Eddit_profile.class);
                startActivity( intent );
            }
        } );

        changephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, IMAGE_PICK_CODE);

            }
        });
        try {
            JSONObject jsonn = new JSONObject();
            jsonn.put("country", country.getText().toString());
            jsonn.put("job", job.getText().toString());
            jsonn.put("skill", skill.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    ;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {

            if (requestCode == IMAGE_PICK_CODE) {
                Uri selectedImageUri = data.getData();


                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);

                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImageUri,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                pathimage = cursor.getString(columnIndex);

                Log.e("stuf", pathimage.toString());
                cursor.close();

                imageView.setImageBitmap(bitmap);
            }

        } catch (Exception e) {
            Log.e("stuf", e.getMessage());

        }
    }

    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://185.255.89.127:8081/jobapi/editInfo/");

                Handler handler = new Handler(Looper.getMainLooper());

                try {
                    MultipartEntity entity = new MultipartEntity();
                    entity.addPart("email", new StringBody(email.getText().toString(), Charset.forName("UTF8")));
                    entity.addPart("lastName", new StringBody(name.getText().toString(), Charset.forName("UTF8")));
                    entity.addPart("firstName", new StringBody(famili.getText().toString(), Charset.forName("UTF8")));
                    entity.addPart("infoData", new StringBody(jsonn.toString(), Charset.forName("UTF8")));


//                    } catch (UnsupportedEncodingException e) {
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                Log.e("stuf", "1" );
////                                Toast.makeText(Loginguse.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
                    File file = new File(pathimage.toString());

                    FileBody fileBody = new FileBody(file);
                    entity.addPart("picture", fileBody);
                    httpPost.setEntity(entity);


                    HttpResponse response = httpclient.execute(httpPost);
                    HttpEntity httpEntity = response.getEntity();
                    String _response = EntityUtils.toString(httpEntity); // content will be consume only once
                    try {

                        final JSONObject jObject = new JSONObject(_response);
                        String status = jObject.getString("status");
                        switch (status) {
                            case "ok":
                                Alert.shows(context, "Thank You", "your profile updated", "OK", "");
                                break;
                            default:
                                Toast.makeText(context, "Please try again", Toast.LENGTH_SHORT).show();
                                break;
                        }


                    } catch (JSONException e) {
                        // dialog.dismiss();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("stuf", "2");
                                // Toast.makeText(Loginguse.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }


                } catch (ClientProtocolException e) {
                    // dialog.dismiss();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("stuf", "3");
//                            Toast.makeText(Loginguse.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (IOException e) {
                    //dialog.dismiss();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("stuf", "4");
                            // Toast.makeText(Loginguse.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        }).start();


    }



}
