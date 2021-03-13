package com.karkardanand.project;


import android.content.Context;
import android.content.Intent;
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


    public SearcheAdapter(Context context,ArrayList<SearchMoudel> seasrchh) {
        this.context= context;
        this.arrayList= seasrchh;
    }

    public SearcheAdapter(ArrayList<SearchMoudel> seasrchh) {
        this.arrayList= seasrchh;
    }


    @NonNull
    @Override
    public SearcheAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.cardnotif  , parent , false);
        return new ViewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull final SearcheAdapter.ViewHolder holder, final int position) {
        final SearchMoudel SearchMoudel =arrayList.get( position );
        holder.money.setText(SearchMoudel.getMoney());
        holder.title.setText( SearchMoudel.getTitle() );
        Glide.with(context).load(SearchMoudel.getPhoto()).into(holder.photo);
        //todo use glide for show image on imageView
        // holder.photo.
        holder.cardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivitys.set(context,holder.cardView,new Intent(context,Viewproject.class).putExtra("projectid", SearchMoudel.getId()));
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