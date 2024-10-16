package com.skappsstore.text.repeater.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.skappsstore.text.repeater.Model.HomeServiceModel;
import com.skappsstore.text.repeater.R;
import com.skappsstore.text.repeater.Helper;

import java.util.ArrayList;

public class HomeServiceAdapter extends RecyclerView.Adapter<HomeServiceAdapter.ViewHolder> {

    Context context;
    ArrayList<HomeServiceModel> shortCateModels;

    public HomeServiceAdapter(Context context, ArrayList<HomeServiceModel> shortCateModels) {
        this.context = context;
        this.shortCateModels = shortCateModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.home_service_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeServiceAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

//        holder.category_Title.setBackgroundColor(helper.getColorCode(context));
        holder.category_Title.setText(shortCateModels.get(position).getServiceTitle());

        holder.smsCategoryRootID.setOnClickListener(v -> {
            Helper.openService(context,position);
        });

        holder.shotCateImg.setImageResource(shortCateModels.get(position).getServiceImg());

    }

    @Override
    public int getItemCount() {
        return shortCateModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView category_Title;
        private MaterialCardView smsCategoryRootID;
        private ImageView shotCateImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category_Title = itemView.findViewById(R.id.category_Title);
            smsCategoryRootID = itemView.findViewById(R.id.smsCategoryRootID);
            shotCateImg = itemView.findViewById(R.id.shotCateImg);
        }
    }

}
