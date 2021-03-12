package com.karkardanand.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{
    ArrayList<NotifMoudel> arrayList;
    ArrayList<String> json=new ArrayList<String>();
    private NotifiClickListener notifiClickListener;
    public NotificationAdapter(ArrayList<NotifMoudel> arrayListy) {
       ;
        this.arrayList= arrayListy;
    }
    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.cardnotif  , parent , false);
        return new ViewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, final int position) {
        NotifMoudel notifMoudel =arrayList.get( position );
        holder.money.setText(notifMoudel.getMoney());
        holder.skils.setText(notifMoudel.getSkills());
        holder.title.setText( notifMoudel.getTitle() );
        holder.id.setText( notifMoudel.getId() );
        //todo use glide for show image on imageView
       // holder.photo.
//        Glide.with(Notification.this).load().apply(options).into(photo);
//        Glide.with(  )
        holder.cardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title,id;
        TextView skils;
        TextView money;
        ImageView photo;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            cardView = itemView.findViewById( R.id.cardviewnotif );
            title= itemView.findViewById( R.id.textcard );
            skils= itemView.findViewById( R.id.skillscard );
            money= itemView.findViewById( R.id.money_card );
            id= itemView.findViewById( R.id.id_notif );
            photo= itemView.findViewById( R.id.photocard );
        }
    }
    public interface NotifiClickListener{
        void onClick(int id);
    }

    public void setNotifiClickListener(NotifiClickListener notifiClickListener) {
        this.notifiClickListener = notifiClickListener;
    }
}
