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

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Profileadapter extends RecyclerView.Adapter<Profileadapter.ViewHolder> {
    private ProfileClickListener profaileListener;
    ArrayList<ProfailMoudel> arrayList;
    private Context context;
    public Profileadapter(Context context , ArrayList<ProfailMoudel> prof) {
        this.context = context;
        this.arrayList = prof;
    }

//    public Profileadapter(ArrayList<HomeMoudel> arrayListy) {
//
//        this.arrayList = arrayListy;
//    }

    @NonNull
    @Override
    public Profileadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.cardviewprof, parent, false );
        return new Profileadapter.ViewHolder( v );

    }

    @Override
    public void onBindViewHolder(@NonNull Profileadapter.ViewHolder holder, final int position) {
        final ProfailMoudel profailMoudel = arrayList.get( position );
        holder.id.setText( profailMoudel.getID() );
        Glide.with(context).load(profailMoudel.getPhoto()).into(holder.photo);
//
//        holder.photo.setImageResource(ProfailMoudel.getPhoto());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView photo ;
        TextView id;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            cardView = itemView.findViewById( R.id.cardviewprof );
            photo = itemView.findViewById( R.id.photocard_post );
            id = itemView.findViewById( R.id.idcardview );
        }
    }
    public interface ProfileClickListener{
        void onClick(int id);
    }

    public void setProfailClickListener(ProfileClickListener profaileListener) {
        this.profaileListener = profaileListener;
    }
}
