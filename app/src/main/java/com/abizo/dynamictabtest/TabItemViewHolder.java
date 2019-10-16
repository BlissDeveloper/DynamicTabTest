package com.abizo.dynamictabtest;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TabItemViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewTabName;

    public TabItemViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewTabName = itemView.findViewById(R.id.textViewTabItemName);
    }

    public void setName(String name) {
        textViewTabName.setText(name);
    }
}
