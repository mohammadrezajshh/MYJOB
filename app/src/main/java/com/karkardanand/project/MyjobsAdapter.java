package com.karkardanand.project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyjobsAdapter extends RecyclerView.Adapter<MyjobsAdapter.ViewHolder> {
        ArrayList<MyjobsMoudel> arrayList;
        int string;
    private Context context;

        private MyjobsAdapter.myjobsClickListener myjobsClickListener;


    public MyjobsAdapter(Context context,ArrayList<MyjobsMoudel> arrayListy) {
        this.context=context;
        this.arrayList= arrayListy;
    }

    public MyjobsAdapter(Context context) {
        this.context = context;
    }


    @NonNull
        @Override
        public MyjobsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.cardnotif  , parent , false);
            return new MyjobsAdapter.ViewHolder( v );
        }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final MyjobsMoudel myjobsmodel =arrayList.get( position );
        holder.money.setText(myjobsmodel.getMoney());
        holder.id.setText(myjobsmodel.getID());
        holder.title.setText( myjobsmodel.getTitle() );
        holder.Skill.setText( myjobsmodel.getSkill() );
        string=myjobsmodel.getId();
        holder.inactive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked = true) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://185.255.89.127:8081/jobapi/inactiveProject/", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.getString("status");
                                switch (status){
                                    case "ok":
                                        holder.inactive.setTextColor( Color.BLACK );
                                        break;
                                    default:
                                        Toast.makeText(context, "Erorr", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "Erorr", Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put( "projectid",String.valueOf(string ));
                            return params;
                        }
                    };

                    jsonparse();
                } else {
                    holder.inactive.setTextColor( Color.BLUE );

                }
            }
        });
        //todo use glide for show image on imageView
        // holder.photo.
        holder.cardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myjobsClickListener.onClick( position );
                startActivitys.set(context,holder.cardView,new Intent(context,Viewproject.class).putExtra("projectid", myjobsmodel.getId()));
            }
        } );
    }

    private void jsonparse() {

    }


    @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            CardView cardView;
            TextView title;
            TextView id;
            TextView money;
            TextView Skill;
            CheckBox inactive;

            public ViewHolder(@NonNull View itemView) {
                super( itemView );
                cardView = itemView.findViewById( R.id.cardview_myjob );
                title= itemView.findViewById( R.id.texttitle_mujob );
                money= itemView.findViewById( R.id.money_myjob );
                id= itemView.findViewById(R.id.id_cardmyjob);
                Skill = itemView.findViewById(R.id.skill_projectmyjob);
                inactive = itemView.findViewById(R.id.inactiveproject);
            }
        }
        public interface myjobsClickListener{
            void onClick(int id);
        }

        public void setmyjobsClickListener(myjobsClickListener myjobsClickListener) {
            this.myjobsClickListener = myjobsClickListener;
        }
    }

