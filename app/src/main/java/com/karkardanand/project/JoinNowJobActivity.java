package com.karkardanand.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import org.json.JSONException;
import org.json.JSONObject;

public class  JoinNowJobActivity extends AppCompatActivity {
    AutoCompleteTextView edtrecentJob,edtcompaney,edtindustry,edtemploymenttype;
    Switch swchstudent;
    Button btnjoinnow;
    String[] employmentTypeArray={"Full-time","Part-time","Self-employed","Freelance","Contract","Internship","Apprenticeship","Duales Studium","Verbeamtet","Freiwilliges Soziales Jahr","Werkstudium"};
    private JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_now_job);
        edtrecentJob=(AutoCompleteTextView)findViewById(R.id.edtrecentJob);
        edtcompaney=(AutoCompleteTextView)findViewById(R.id.edtcompaney);
        edtindustry=(AutoCompleteTextView)findViewById(R.id.edtindustry);
        btnjoinnow=(Button) findViewById(R.id.btnjoinnow);
       // btnjoinnow=(Button) findViewById(R.id.btnjoinnow);
        edtemploymenttype=(AutoCompleteTextView)findViewById(R.id.edtemploymenttype);
        swchstudent=(Switch)findViewById(R.id.swchstudent);
        Intent intent=getIntent();
        try {

            json=new JSONObject(intent.getStringExtra("ret"));
        } catch (JSONException e) {
            Log.e("Ex32", e.toString());
            e.printStackTrace();
        }
        swchstudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    startActivity(new Intent(JoinNowJobActivity.this,JoinNoewJobStudentActivity.class).putExtra("ret",json.toString()));
                }
            }
        });

        AutoCompelet.setit(JoinNowJobActivity.this,edtrecentJob,getJobList.setjobs());
        AutoCompelet.setit(JoinNowJobActivity.this,edtemploymenttype,employmentTypeArray);
        AutoCompelet.setit(JoinNowJobActivity.this,edtindustry,getJobList.setindustry());



    }
    public void btnjoinnow(View view){
        if (edtrecentJob.getText().toString().equals("")||edtcompaney.getText().toString().equals("")||edtindustry.getText().toString().equals("")||edtemploymenttype.getText().toString().equals("")){
            Alert.shows(JoinNowJobActivity.this,"","Please complete the information","OK","");
        }else {
            try {
                json.put("student","False");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("job",edtrecentJob.getText().toString());
                jsonObject.put("company",edtcompaney.getText().toString());
                jsonObject.put("skill",edtindustry.getText().toString());

                startActivitys.set(JoinNowJobActivity.this,btnjoinnow,new Intent(JoinNowJobActivity.this,LocationAndPictureActivty.class).putExtra("ret",json.toString()).putExtra("info",jsonObject.toString()));
            } catch (JSONException e) {
            e.printStackTrace();
        }


        }
    }
}