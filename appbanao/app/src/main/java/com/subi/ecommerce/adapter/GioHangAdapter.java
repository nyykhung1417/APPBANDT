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
import com.subi.ecommerce.DetailActivity;
import com.subi.ecommerce.GioHangActivity;
import com.subi.ecommerce.R;
import com.subi.ecommerce.api.ApiService;
import com.subi.ecommerce.model.GioHang;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    private Context context;
    private GioHang gioHang;
    private ArrayList<GioHang> list;
    NumberFormat format = NumberFormat.getCurrencyInstance();

    public GioHangAdapter() {
    }
    public void updateItem(int position, GioHang newData) {
        list.set(position, newData);
        notifyItemChanged(position);
    }

    public GioHangAdapter(Context context, ArrayList<GioHang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Set layout cho adapter để hiển thị lên list
        View view = LayoutInflater.from(context).inflate(R.layout.one_giohang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Trả về 1 item tại vị trí position(vị trí hiện tại theo list)
        GioHang gh = list.get(position);
        //Set tiêu đề
        holder.name.setText(gh.getTenSanPham());
        holder.tonggia.setText("Thành tiền: " +gh.getTonggia());
        holder.price.setText("Giá: " +gh.getGiaSanPham());
//        holder.imageView.setImageResource(gh.getImage());
        Picasso.get()
                .load(gh.getImage())
                .fit()
                .into(holder.imageView);
        holder.sl.setText("Số lượng: " + gh.getSoLuong());
        holder.del.setOnClickListener(view -> {
            Call<GioHang> giohang=ApiService.apiservice.deletegiohang(gh.getId());
         giohang.enqueue(new Callback<GioHang>() {
             @Override
             public void onResponse(Call<GioHang> call, Response<GioHang> response) {
                 Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(context, GioHangActivity.class);
                 context.startActivity(intent);
             }

             @Override
             public void onFailure(Call<GioHang> call, Throwable t) {
                 Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                 notifyDataSetChanged();
             }
         });
//            Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
//            GioHangDao gioHangDao = new GioHangDao(context);
//            if (gioHangDao.xoa(gh)) {
//                list.clear();
//                list.addAll(gioHangDao.getAll());
//                notifyDataSetChanged();
//                if (list.isEmpty()) {
//                    GioHangActivity.pay.setVisibility(View.GONE);
//                    GioHangActivity.emty.setVisibility(View.VISIBLE);
//                }
//                Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
//            }
//            else {
//                Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
//            }

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, del;
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
            del = itemView.findViewById(R.id.iv_del);
            view = itemView;
        }
    }

}
