package com.karkardanand.project;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class viewprojectadapter extends RecyclerView.Adapter<viewprojectadapter.ViewoHolder> {
    @NonNull
    @Override
    public viewprojectadapter.ViewoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull viewprojectadapter.ViewoHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewoHolder extends RecyclerView.ViewHolder {
        public ViewoHolder(@NonNull View itemView) {
            super( itemView );
        }
    }
}
