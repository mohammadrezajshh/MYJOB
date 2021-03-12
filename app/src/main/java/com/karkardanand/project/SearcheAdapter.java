package com.karkardanand.project;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

public class SearcheAdapter extends RecyclerView.Adapter<SearcheAdapter.ViewHolder>{
    ArrayList<SearchMoudel> arrayList;
    ArrayList<String> json=new ArrayList<String>();
    private Context context;
    private SearchListener searchListener;
    public SearcheAdapter(Context context ,ArrayList<SearchMoudel> seasrchh) {
        this.context= context;
        this.arrayList= seasrchh;
    }



    @NonNull
    @Override
    public SearcheAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.cardnotif  , parent , false);
        return new ViewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull SearcheAdapter.ViewHolder holder, final int position) {
        SearchMoudel SearchMoudel =arrayList.get( position );
        holder.money.setText(SearchMoudel.getMoney());
        holder.title.setText( SearchMoudel.getTitle() );
        Glide.with(context).load(SearchMoudel.getPhoto()).into(holder.photo);
        //todo use glide for show image on imageView
        // holder.photo.
        holder.cardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://185.255.89.127:8081/jobapi/projectDetails/", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
//                        params.put("projectid",jso)
                        return params;
                    }
                };
            }
        } );
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title;
        TextView money;
        ImageView photo;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            cardView = itemView.findViewById( R.id.cardviewnotif );
            title= itemView.findViewById( R.id.textcard );
            money= itemView.findViewById( R.id.money_card );
            photo= itemView.findViewById( R.id.photocard );
        }
    }
    public interface SearchListener{
        void onClick(int id);
    }

    public void setSearchListener(SearchListener searchListener) {
        this.searchListener = searchListener;
    }
}