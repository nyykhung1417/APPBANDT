package com.subi.ecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.subi.ecommerce.Detail_blogActivity;
import com.subi.ecommerce.ProductActivity;
import com.subi.ecommerce.R;

import com.subi.ecommerce.model.Danhmuc;

import java.text.NumberFormat;
import java.util.ArrayList;

public class DanhmucAdapter extends RecyclerView.Adapter<DanhmucAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Danhmuc> list;
    NumberFormat format = NumberFormat.getCurrencyInstance();
    private UserCallback userCallback;

    public DanhmucAdapter(){

    }
    public DanhmucAdapter(Context context, ArrayList<Danhmuc> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_danhmuc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Danhmuc danhmuc = list.get(position);
        //Set tiêu đề
        holder.tendanhmuc.setText(danhmuc.getTendanhmuc());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductActivity.class);
                //Truyền toàn bộ data sang
                intent.putExtra("danhmuc", danhmuc);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tendanhmuc;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Khai báo id theo itemView
            tendanhmuc = itemView.findViewById(R.id.tendanhmuc);

            view = itemView;
        }
    }
    public interface UserCallback{
        void onItemClick(int id,String tendanhmuc);
    }
}
