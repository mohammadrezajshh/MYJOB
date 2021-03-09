package com.karkardanand.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EdditProfail extends AppCompatActivity {
    EditText name,famili,email,country,skils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_eddit_profail );
        TextView changepass = findViewById( R.id.changepass );
        name= findViewById( R.id.trt_name_eddit );
        famili= findViewById( R.id.trt_family_ );
        email= findViewById( R.id.trt_email_eddit );
        skils= findViewById( R.id.skil_eddit );
        country= findViewById( R.id.changeCountry );
        changepass.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Eddit_profile.class);
                startActivity( intent );
            }
        } );

    }
}