package com.karkardanand.project;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder>{
    ArrayList<AllRequest> arrayList;
    private Context context;
    private TextView title,message;
    private Button chat,email;
    ImageView close;
    Dialog dialog;
    String token;

    public RequestAdapter(Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.cardnotif  , parent , false);
        return new RequestAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestAdapter.ViewHolder holder, int position) {
        dialog = new Dialog( context );
        final AllRequest allRequest =arrayList.get( position );
        holder.token.setText(allRequest.getToken());
        holder.id.setText(allRequest.getID());
        holder.description.setText(allRequest.getDescription());
        holder.money.setText(allRequest.getMoney());
        holder.time.setText(allRequest.getToken());
        holder.idproject.setText(allRequest.getIDProject());
        token = allRequest.getToken();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAlert();
            }
        });

            }

    private void ShowAlert() {
        dialog.setContentView( R.layout.alertshow );
        title = (TextView) dialog.findViewById( R.id.title_go );

        chat = (Button) dialog.findViewById( R.id.btnchat );
        close = (ImageView) dialog.findViewById( R.id.close_accept );
        message = (TextView) dialog.findViewById( R.id.messaage_acceot );
        title.setText("Are you sure about your choice?" );
        message.setText("If you are sure, press the chat button" );

        chat.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivitys.set(context,chat,new Intent(context,ChatActivity.class).putExtra("token",token));
            }
        } );



        dialog.getWindow().setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) );
        dialog.show();
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView description;
        TextView id;
        TextView money;
        TextView time;
        TextView token;
        TextView idproject;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            cardView = itemView.findViewById( R.id.cardrequest );
            description= itemView.findViewById( R.id.textcard );
            money= itemView.findViewById( R.id.money_card );
            id= itemView.findViewById(R.id.idreq);
            time = itemView.findViewById(R.id.timecard);
            token = itemView.findViewById(R.id.tokenreq);
            idproject = itemView.findViewById(R.id.idprojectreq);
        }
    }
}
