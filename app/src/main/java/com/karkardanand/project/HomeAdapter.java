package com.karkardanand.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private HomeClickListener homeListener;
    ArrayList<HomeMoudel> arrayList;

    public HomeAdapter(ArrayList<HomeMoudel> arrayListy) {

        this.arrayList = arrayListy;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.cardview_home, parent, false );
        return new HomeAdapter.ViewHolder( v );

    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, final int position) {
        HomeMoudel homeMoudel = arrayList.get( position );
        holder.caption.setText(homeMoudel.getCaption());
//        holder..setText(notifMoudel.getSkills());
//        holder.title.setText( notifMoudel.getTitle() );
        holder.id.setText(homeMoudel.getPost_id());
        holder.Like.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeListener.onClick( position );
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
        ImageView photo , Like;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            cardView = itemView.findViewById( R.id.cardviewhome );
            caption = itemView.findViewById( R.id.textcard_post );
            Like = itemView.findViewById( R.id.Like_post );
            photo = itemView.findViewById( R.id.photocard_post );
        }
    }
    public interface HomeClickListener{
        void onClick(int id);
    }

    public void setHomeClickListener(HomeClickListener homeListener) {
        this.homeListener = homeListener;
    }
}
