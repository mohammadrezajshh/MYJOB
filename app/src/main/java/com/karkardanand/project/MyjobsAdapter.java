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

import java.util.ArrayList;

public class MyjobsAdapter extends RecyclerView.Adapter<MyjobsAdapter.ViewHolder> {
        ArrayList<MyjobsMoudel> arrayList;
    private Context context;
        ArrayList<String> json=new ArrayList<String>();
        private MyjobsAdapter.myjobsClickListener myjobsClickListener;
        public MyjobsAdapter(Context context,ArrayList<MyjobsMoudel> arrayListy) {
            this.context=context;
            this.arrayList= arrayListy;
        }



    public MyjobsAdapter(Context context) {
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
        holder.skils.setText(myjobsmodel.getSkill());
        holder.title.setText( myjobsmodel.getTitle() );
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
        public interface myjobsClickListener{
            void onClick(int id);
        }

        public void setmyjobsClickListener(myjobsClickListener myjobsClickListener) {
            this.myjobsClickListener = myjobsClickListener;
        }
    }

