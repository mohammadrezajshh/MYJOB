package com.karkardanand.project;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.provider.ContactsContract;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.prefs.NodeChangeListener;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{
    ArrayList<NotifMoudel> arrayList;
    ArrayList<String> json=new ArrayList<String>();
    private Context context;
    private NotifiClickListener notifiClickListener;
    public NotificationAdapter(Context context,ArrayList<NotifMoudel> notif) {
       this.context=context;
        this.arrayList= notif;
    }

    public NotificationAdapter(ArrayList<NotifMoudel> notif) {
        this.arrayList= notif;
    }


    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.cardnotif  , parent , false);
        return new ViewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull final NotificationAdapter.ViewHolder holder, final int position) {
        final NotifMoudel notifMoudel =arrayList.get( position );
        holder.money.setText(notifMoudel.getMoney());
        holder.skils.setText(notifMoudel.getSkills());
        holder.title.setText( notifMoudel.getTitle() );
        holder.id.setText( notifMoudel.getId() );
        Glide.with(context).load(notifMoudel.getPhoto()).into(holder.photo);
        //todo use glide for show image on imageView
       // holder.photo.
//        Glide.with(Notification.this).load().apply(options).into(photo);
//        Glide.with(  )
        holder.cardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivitys.set(context,holder.cardView,new Intent(context,Viewproject.class).putExtra("projectid", notifMoudel.getId()));
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
