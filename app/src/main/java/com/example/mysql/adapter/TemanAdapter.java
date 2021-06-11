package com.example.mysql.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysql.R;
import com.example.mysql.database.Teman;
import com.example.mysql.MainActivity;
import com.example.mysql.R;
import com.example.mysql.database.Teman;
import com.google.android.material.transition.Hold;

import java.util.ArrayList;
import java.util.HashMap;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<Teman> listdata;

    public TemanAdapter(ArrayList<Teman> listdata) {
        this.listdata = listdata;
    }

    @Override
    public TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View view = layoutInf.inflate(R.layout.rowdatateman,parent,false);
        return new TemanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TemanViewHolder holder, int position) {
        String nm,tlp;

        nm = listdata.get(position).getNama();
        tlp = listdata.get(position).getTelpon();

        holder.namaTxt.setTextColor(Color.BLUE);
        holder.namaTxt.setTextSize(20);
        holder.namaTxt.setText(nm);
        holder.telponTxt.setText(tlp);
        holder.cardku.setOnClickListener(new View.OnClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }

    @Override
    public int getItemCount() { return (listdata != null)?listdata.size() : 0;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView cardku;
        private TextView namaTxt,telponTxt;
        public TemanViewHolder(View view){
            super(view);
            cardku = (CardView) view.findViewById(R.id.kartuku);
            namaTxt = (TextView) view.findViewById(R.id.textNama);
            telponTxt = (TextView) view.findViewById(R.id.textTelpon);
        }
    }
}
