package com.subi.ecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.subi.ecommerce.GioHangActivity;
import com.subi.ecommerce.R;
import com.subi.ecommerce.api.ApiService;
import com.subi.ecommerce.model.Donhang;
import com.subi.ecommerce.model.GioHang;

import java.text.NumberFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonhangAdapter extends RecyclerView.Adapter<DonhangAdapter.ViewHolder> {
    private Context context;
    private Donhang donhang;
    private ArrayList<Donhang> list;
    NumberFormat format = NumberFormat.getCurrencyInstance();

    public DonhangAdapter() {
    }
    public void updateItem(int position, Donhang newData) {
        list.set(position, newData);
        notifyItemChanged(position);
    }

    public DonhangAdapter(Context context, ArrayList<Donhang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Set layout cho adapter để hiển thị lên list
        View view = LayoutInflater.from(context).inflate(R.layout.one_donhang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Trả về 1 item tại vị trí position(vị trí hiện tại theo list)
        Donhang dh = list.get(position);
        //Set tiêu đề
        holder.name.setText(dh.getTenSanPham());
        holder.tonggia.setText("Thành tiền: " +dh.getTonggia());
        holder.price.setText("Giá: " +dh.getGiaSanPham());
//        holder.imageView.setImageResource(gh.getImage());
        Picasso.get()
                .load(dh.getImage())
                .fit()
                .into(holder.imageView);
        holder.sl.setText("Số lượng: " + dh.getSoLuong());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, price, sl,tonggia;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Khai báo id theo itemView
            imageView = itemView.findViewById(R.id.ivNewsList);
            name = itemView.findViewById(R.id.one_name);
            price = itemView.findViewById(R.id.one_price);
            tonggia = itemView.findViewById(R.id.tonggia);
            sl = itemView.findViewById(R.id.one_sl);

            view = itemView;
        }
    }

}
