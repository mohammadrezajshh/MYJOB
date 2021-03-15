package com.karkardanand.project;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private HomeClickListener homeListener;
    private boolean waitDouble = true;
    private Context context;
    private static final int DOUBLE_CLICK_TIME = 350;
    ArrayList<HomeMoudel> arrayList;
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    String token = prefs.getString("Token", "");

    public HomeAdapter(Context context, ArrayList<HomeMoudel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.cardview_home, parent, false );
        return new HomeAdapter.ViewHolder( v );

    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, final int position) {
        final HomeMoudel homeMoudel = arrayList.get( position );
        holder.caption.setText( homeMoudel.getCaption() );
//        holder..setText(notifMoudel.getSkills());
//        holder.title.setText( notifMoudel.getTitle() );
        holder.id.setText( homeMoudel.getID() );
        Glide.with(context).load(homeMoudel.getPhoto()).into(holder.photo);
        Glide.with(context).load(homeMoudel.getProfilePic()).into(holder.photouser);
        holder.Like.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (waitDouble == true) {
                    waitDouble = false;
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                sleep( DOUBLE_CLICK_TIME );
                                if (waitDouble == false) {
                                    waitDouble = true;
                                    StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://185.255.89.127:8081/jobapi/setdisLike/", new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONObject jsonObject = new JSONObject( response );
                                                String status = jsonObject.getString( "status" );
                                                switch (status) {
                                                    case "ok":
                                                        Log.e( "Like", "LIKEe" );
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            //add like


                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    } ) {
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            Map<String, String> params = new HashMap<String, String>();
                                            params.put( "postid", String.valueOf( getId() ) );
                                            params.put( "token", token );
                                            return params;
                                        }


                                    };
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    thread.start();
                } else {
                    waitDouble = true;
                    StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://185.255.89.127:8081/jobapi/addLike/", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject( response );
                                String status = jsonObject.getString( "status" );
                                switch (status) {
                                    case "ok":
                                        Log.e( "Like", "LIKEe" );
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //add like


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    } ) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put( "postid", String.valueOf( homeMoudel.getID() ) );
                            params.put( "token", token );
                            return params;
                        }


                    };

                }
            }


        } );
    }






    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView caption,id;
        ImageView photo , Like , photouser;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );


            cardView = itemView.findViewById( R.id.cardviewhome );
            caption = itemView.findViewById( R.id.textcard_post );
            Like = itemView.findViewById( R.id.Like_post );
            photo = itemView.findViewById( R.id.photocard_post );
            photouser = itemView.findViewById( R.id.imageuser );
        }
    }
    public interface HomeClickListener{
        void onClick(int id);
    }

    public void setHomeClickListener(HomeClickListener homeListener) {
        this.homeListener = homeListener;
    }
}
