package com.karkardanand.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main_activity extends AppCompatActivity {
    private static final int IMAGE_PICK_CODE = 10;
    static String pathimage;
    EditText et_caption;
    ImageView photo;
    TextView Post;
    String caption;
    String file_name;
    String user_Id = "";
    private ArrayList<String> listPermissionsNeeded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_post );
        Toast.makeText( this, "jjjjj", Toast.LENGTH_SHORT ).show();
        Post = findViewById( R.id.post );
        Intent intent = getIntent();
        user_Id = intent.getStringExtra( "user_id" );
        et_caption = findViewById( R.id.edditextpost );
        photo = (ImageView) findViewById( R.id.photogalery );
        BottomNavigationView bottomNavigationView = findViewById( R.id.bt_nav );
        bottomNavigationView.setSelectedItemId( R.id.bn_ADdpost );
        caption = et_caption.getText().toString();
        Post.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread( new Runnable() {
                    @Override
                    public void run() {
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httpPost = new HttpPost( "http://185.255.89.127:8081/jobapi/addPost/" );

                        Handler handler = new Handler( Looper.getMainLooper() );


                        MultipartEntity entity = new MultipartEntity();
                        try {
                            entity.addPart("caption",new StringBody(et_caption.getText().toString(), Charset.forName("UTF8")));
                            entity.addPart("caption",new StringBody(user_Id.toString(), Charset.forName("UTF8")));
//                        entity.addPart( "password", new StringBody( json.getString( "password" ).toString(), Charset.forName( "UTF8" ) ) );
//                        entity.addPart( "lastName", new StringBody( json.getString( "lastName" ).toString(), Charset.forName( "UTF8" ) ) );
//                        entity.addPart( "firstName", new StringBody( json.getString( "firstName" ).toString(), Charset.forName( "UTF8" ) ) );
//                        entity.addPart( "country", new StringBody( json.getString( "country" ).toString(), Charset.forName( "UTF8" ) ) );
//                        entity.addPart( "student", new StringBody( json.getString( "student" ).toString(), Charset.forName( "UTF8" ) ) );
//                        entity.addPart( "infoData", new StringBody( jsonn.toString(), Charset.forName( "UTF8" ) ) );


//                    } catch (UnsupportedEncodingException e) {
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                Log.e("stuf", "1" );
////                                Toast.makeText(Loginguse.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });

                            File file = new File( pathimage.toString() );

                            FileBody fileBody = new FileBody( file );
                            entity.addPart( "picture", fileBody );
                            httpPost.setEntity( entity );


                            HttpResponse response = httpclient.execute( httpPost );
                            HttpEntity httpEntity = response.getEntity();
                            String _response = EntityUtils.toString( httpEntity ); // content will be consume only once
                            try {

                                final JSONObject jObject = new JSONObject( _response );
                                String status = jObject.getString( "status" );
                                switch (status) {
                                    case "ok":
                                        String token = jObject.getString( "token" );
                                        Intent intent = new Intent( getApplicationContext(), Add_codeEmail.class ).putExtra( "token", token );
                                        startActivity( intent );
                                        break;
                                    default:
                                        Toast.makeText( getApplicationContext(), "Please try again", Toast.LENGTH_SHORT ).show();
                                }


                            } catch (JSONException e) {
                                // dialog.dismiss();
                                handler.post( new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.e( "stuf", "2" );
                                        // Toast.makeText(Loginguse.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                } );

                            }


                        } catch (ClientProtocolException e) {
                            // dialog.dismiss();
                            handler.post( new Runnable() {
                                @Override
                                public void run() {
                                    Log.e( "stuf", "3" );
//                            Toast.makeText(Loginguse.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            } );
                        } catch (IOException e) {
                            //dialog.dismiss();
                            handler.post( new Runnable() {
                                @Override
                                public void run() {
                                    Log.e( "stuf", "4" );
                                    // Toast.makeText(Loginguse.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            } );
                        }

                    }
                } ).start();



            }
        } );
        photo.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertClose = new AlertDialog.Builder( Main_activity.this );
                alertClose.setTitle( "Profile picture" ).
                        setMessage( "" ).
                        setPositiveButton( "Take a photo", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listPermissionsNeeded = new ArrayList<String>();
                                listPermissionsNeeded.add( Manifest.permission.CAMERA );
                                listPermissionsNeeded.add( Manifest.permission.WRITE_EXTERNAL_STORAGE );
                                listPermissionsNeeded.add( Manifest.permission.READ_EXTERNAL_STORAGE );
                                ActivityCompat.requestPermissions( Main_activity.this, listPermissionsNeeded.toArray
                                        ( new String[listPermissionsNeeded.size()] ), 1001 );


                            }
                        } ).setNegativeButton( "Choose a photo from the gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                } ).show();

            }
        } );

        bottomNavigationView.setOnNavigationItemReselectedListener( new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bn_Home:
                        startActivity( new Intent( getApplicationContext(),
                                Home.class ).putExtra( "user_id", user_Id ) );
                        overridePendingTransition( 0, 0 );
                        return;

                    case R.id.bn_Profail:
                        startActivity( new Intent( getApplicationContext(),
                                Profile.class ).putExtra( "user_id", user_Id ) );
                        overridePendingTransition( 0, 0 );
                        return;

                    case R.id.bn_ADdpost:

                    case R.id.bn_Jobs:
                        startActivity( new Intent( getApplicationContext(),
                                My_Jobs.class ).putExtra( "user_id", user_Id ) );
                        overridePendingTransition( 0, 0 );
                        return;
                    case R.id.bn_Search_job:
                        startActivity( new Intent( getApplicationContext(),
                                Search_jobs.class ).putExtra( "user_id", user_Id ) );
                        overridePendingTransition( 0, 0 );
                        return;


                }
                return;

            }
        } );

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1001) {
            if (checkSelfPermission( Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission( Manifest.permission.READ_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission( Manifest.permission.WRITE_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED) {

                Alert.shows( Main_activity.this, "", "Sorry,We dont have permissions", "OK", "" );
            } else {
                Intent cameraIntent = new Intent( android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
                startActivityForResult( cameraIntent, 2001 );
            }
        }
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
    }




//    {
//        new Thread( new Runnable() {
//            @Override
//            public void run() {
//                HttpClient httpclient = new DefaultHttpClient();
//                HttpPost httpPost = new HttpPost( "http://185.255.89.127:8081/jobapi/saveInfo/" );
//
//                Handler handler = new Handler( Looper.getMainLooper() );
//
//
//                    MultipartEntity entity = new MultipartEntity();
//                    try {
//                        entity.addPart("caption",new StringBody(et_caption.getText().toString(), Charset.forName("UTF8")));
//                        entity.addPart("caption",new StringBody(user_Id.toString(), Charset.forName("UTF8")));
////                        entity.addPart( "password", new StringBody( json.getString( "password" ).toString(), Charset.forName( "UTF8" ) ) );
////                        entity.addPart( "lastName", new StringBody( json.getString( "lastName" ).toString(), Charset.forName( "UTF8" ) ) );
////                        entity.addPart( "firstName", new StringBody( json.getString( "firstName" ).toString(), Charset.forName( "UTF8" ) ) );
////                        entity.addPart( "country", new StringBody( json.getString( "country" ).toString(), Charset.forName( "UTF8" ) ) );
////                        entity.addPart( "student", new StringBody( json.getString( "student" ).toString(), Charset.forName( "UTF8" ) ) );
////                        entity.addPart( "infoData", new StringBody( jsonn.toString(), Charset.forName( "UTF8" ) ) );
//
//
////                    } catch (UnsupportedEncodingException e) {
////                        handler.post(new Runnable() {
////                            @Override
////                            public void run() {
////                                Log.e("stuf", "1" );
//////                                Toast.makeText(Loginguse.this, e.getMessage(), Toast.LENGTH_SHORT).show();
////                            }
////                        });
//
//                    File file = new File( pathimage.toString() );
//
//                    FileBody fileBody = new FileBody( file );
//                    entity.addPart( "picture", fileBody );
//                    httpPost.setEntity( entity );
//
//
//                    HttpResponse response = httpclient.execute( httpPost );
//                    HttpEntity httpEntity = response.getEntity();
//                    String _response = EntityUtils.toString( httpEntity ); // content will be consume only once
//                    try {
//
//                        final JSONObject jObject = new JSONObject( _response );
//                        String status = jObject.getString( "status" );
//                        switch (status) {
//                            case "ok":
//                                String token = jObject.getString( "token" );
//                                Intent intent = new Intent( getApplicationContext(), Add_codeEmail.class ).putExtra( "token", token );
//                                startActivity( intent );
//                                break;
//                            default:
//                                Toast.makeText( getApplicationContext(), "Please try again", Toast.LENGTH_SHORT ).show();
//                        }
//
//
//                    } catch (JSONException e) {
//                        // dialog.dismiss();
//                        handler.post( new Runnable() {
//                            @Override
//                            public void run() {
//                                Log.e( "stuf", "2" );
//                                // Toast.makeText(Loginguse.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        } );
//
//                    }
//
//
//                } catch (ClientProtocolException e) {
//                    // dialog.dismiss();
//                    handler.post( new Runnable() {
//                        @Override
//                        public void run() {
//                            Log.e( "stuf", "3" );
////                            Toast.makeText(Loginguse.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    } );
//                } catch (IOException e) {
//                    //dialog.dismiss();
//                    handler.post( new Runnable() {
//                        @Override
//                        public void run() {
//                            Log.e( "stuf", "4" );
//                            // Toast.makeText(Loginguse.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    } );
//                }
//
//            }
//        } ).start();
//
//
//    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 2001) {
            Bitmap photo = (Bitmap) data.getExtras().get( "data" );
            ImageHelper imageHelper = new ImageHelper();
            imageHelper.getRoundedCornerBitmap( photo, 75 );
        }
        super.onActivityResult( requestCode, resultCode, data );
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getPath3(Context context, Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri( context, uri )) {
            // ExternalStorageProvider
            if (isExternalStorageDocument( uri )) {
                final String docId = DocumentsContract.getDocumentId( uri );
                final String[] split = docId.split( ":" );
                final String type = split[0];

                if ("primary".equalsIgnoreCase( type )) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument( uri )) {
                final String id = DocumentsContract.getDocumentId( uri );
                final Uri contentUri = ContentUris.withAppendedId( Uri.parse( "content://downloads/public_downloads" ), Long.valueOf( id ) );
                return getDataColumn( context, contentUri, null, null );
            }
            // MediaProvider
            else if (isMediaDocument( uri )) {
                final String docId = DocumentsContract.getDocumentId( uri );
                final String[] split = docId.split( ":" );
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals( type )) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals( type )) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals( type )) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn( context, contentUri, selection, selectionArgs );
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase( uri.getScheme() )) {
            // Return the remote address
            if (isGooglePhotosUri( uri ))
                return uri.getLastPathSegment();
            return getDataColumn( context, uri, null, null );
        }
        // File
        else if ("file".equalsIgnoreCase( uri.getScheme() )) {
            return uri.getPath();
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query( uri, projection, selection, selectionArgs, null );
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow( column );
                return cursor.getString( index );
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals( uri.getAuthority() );
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals( uri.getAuthority() );
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals( uri.getAuthority() );
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals( uri.getAuthority() );
    }

    private File savebitmap(Bitmap bmp) {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;
        // String temp = null;
        File file = new File( Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile.png" );
        if (file.exists()) {
            file.delete();
            file = new File( Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile.png" );

        }

        try {
            outStream = new FileOutputStream( file );
            bmp.compress( Bitmap.CompressFormat.PNG, 100, outStream );
            outStream.flush();
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

}
