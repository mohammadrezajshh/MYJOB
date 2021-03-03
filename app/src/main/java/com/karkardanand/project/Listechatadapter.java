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

public class Listechatadapter extends RecyclerView.Adapter<Listechatadapter.ViewHolder> {
    private Listechatadapter.ListchatClickListener listchatListener;
    ArrayList<HomeMoudel> arrayList;

    public Listechatadapter(ArrayList<HomeMoudel> arrayListy) {

        this.arrayList = arrayListy;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.cardview_home, parent, false );
        return new Listechatadapter.ViewHolder( v );

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        HomeMoudel homeMoudel = arrayList.get( position );
        holder.caption.setText(homeMoudel.getCaption());
//        holder..setText(notifMoudel.getSkills());
//        holder.title.setText( notifMoudel.getTitle() );
        holder.id.setText(homeMoudel.getPost_id());
        holder.Like.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listchatListener.onClick( position );
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
    public interface ListchatClickListener{
        void onClick(int id);
    }

    public void setListchatClickListener(ListchatClickListener listchatListener) {
        this.listchatListener = listchatListener;
    }
}
