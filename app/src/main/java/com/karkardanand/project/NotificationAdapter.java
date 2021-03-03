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
        //todo use glide for show image on imageView
       // holder.photo.
        holder.cardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifiClickListener.onClick( position );
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
        TextView skils;
        TextView money;
        ImageView photo;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            cardView = itemView.findViewById( R.id.cardviewnotif );
            title= itemView.findViewById( R.id.textcard );
            skils= itemView.findViewById( R.id.skillscard );
            money= itemView.findViewById( R.id.money_card );
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
