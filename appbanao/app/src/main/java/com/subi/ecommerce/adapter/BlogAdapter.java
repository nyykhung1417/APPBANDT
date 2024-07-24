package com.subi.ecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.method.CharacterPickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.subi.ecommerce.DetailActivity;
import com.subi.ecommerce.Detail_blogActivity;
import com.subi.ecommerce.R;
import com.subi.ecommerce.model.Blog;
import com.subi.ecommerce.model.Sanpham;

import java.text.NumberFormat;
import java.util.ArrayList;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Blog> list;
    NumberFormat format = NumberFormat.getCurrencyInstance();
    private UserCallback userCallback;

    public BlogAdapter(){

    }
    public BlogAdapter(Context context, ArrayList<Blog> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_blog, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Blog blog = list.get(position);
        //Set tiêu đề
        holder.name.setText(blog.getTieude());
        holder.mota.setText(blog.getMotangan());
        holder.ngaydang.setText(blog.getNgaydang());
        Picasso.get()
                .load(blog.getImage())
                .fit()
                .into(holder.imageView);

//        holder.itemView.setOnClickListener(view -> userCallback.onItemClick(blog.getTieude(),blog.getMotangan(),blog.getImage(),blog.getNgaydang(),blog.getMotachitiet()));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detail_blogActivity.class);
                //Truyền toàn bộ data sang
                intent.putExtra("blog", blog);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, mota,ngaydang;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Khai báo id theo itemView
            imageView = itemView.findViewById(R.id.image_blog);
            name = itemView.findViewById(R.id.one_name);
            ngaydang = itemView.findViewById(R.id.one_create);
            mota = itemView.findViewById(R.id.one_mota);
            view = itemView;
        }
    }
    public interface UserCallback{
        void onItemClick(String tieude, String motangan, String image, String ngaydang, String motachitiet);
    }
}
