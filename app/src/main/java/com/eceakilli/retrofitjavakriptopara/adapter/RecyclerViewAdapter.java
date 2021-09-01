package com.eceakilli.retrofitjavakriptopara.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eceakilli.retrofitjavakriptopara.R;
import com.eceakilli.retrofitjavakriptopara.databinding.ActivityMainBinding;
import com.eceakilli.retrofitjavakriptopara.databinding.RowLayoutBinding;
import com.eceakilli.retrofitjavakriptopara.model.CryptoModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {

    public RecyclerViewAdapter(ArrayList<CryptoModel> cryptoList) {
        this.cryptoList = cryptoList;
    }

    private ArrayList<CryptoModel> cryptoList;
    private String[] colors = {"#a3ff00","#ff00aa","#b4a7d6","#a4c2f4","#8ee5ee","#cd950c","#f5f5f5","#f47932"};

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowLayoutBinding bind = RowLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new RowHolder(bind);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position % 8]));
        holder.binding.textName.setText(cryptoList.get(position).currency);
        holder.binding.textPrice.setText(cryptoList.get(position).price);

    }

    @Override
    public int getItemCount() {
        return cryptoList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {

        RowLayoutBinding binding;

        public RowHolder(RowLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

    }
}
