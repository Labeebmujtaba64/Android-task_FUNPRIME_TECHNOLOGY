package com.labeeb.funprimetechnology;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Home_Adapter extends RecyclerView.Adapter<Home_Adapter.ViewHolder> {

    Context context;

    ArrayList image;


    public Home_Adapter(Context context, ArrayList arrayList){

        this.context = context;
        this.image = arrayList;
    }


    @NonNull
    @Override
    public Home_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_recycler_view,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.recyclerImage1.setImageDrawable(context.getResources().getDrawable(R.drawable.funpime_logo));




    }

    @Override
    public int getItemCount() {
        return image.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recyclerImage1;
        FrameLayout id_native_ads;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerImage1 = (ImageView) itemView.findViewById(R.id.recyclerimage1);
            id_native_ads = (FrameLayout) itemView.findViewById(R.id.id_native_ads);
        }
    }
}
