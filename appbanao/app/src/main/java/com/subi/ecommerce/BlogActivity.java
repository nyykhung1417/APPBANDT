package com.subi.ecommerce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.subi.ecommerce.adapter.BlogAdapter;
import com.subi.ecommerce.adapter.ShowDialog;
import com.subi.ecommerce.api.ApiService;
import com.subi.ecommerce.model.Blog;
import com.subi.ecommerce.model.Sanpham;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogActivity extends AppCompatActivity {

    private ArrayList<Blog> list = new ArrayList<>();

    private ShowDialog showDialog;
    BlogAdapter blogAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        init();
        setTitle("Blog");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Intent mainIntent = new Intent(BlogActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        break;

                    case R.id.action_account:
                        Intent dangkyIntent = new Intent(BlogActivity.this, DangnhapActivity.class);
                        startActivity(dangkyIntent);
                        break;

                    case R.id.action_cart:
                        Intent cartIntent = new Intent(BlogActivity.this, GioHangActivity.class);
                        startActivity(cartIntent);
                        break;
                    case R.id.action_blog:
                        Intent blogIntent = new Intent(BlogActivity.this, BlogActivity.class);
                        startActivity(blogIntent);
                        break;
                    case R.id.action_product:
                        Intent productIntent = new Intent(BlogActivity.this, ProductActivity.class);
                        startActivity(productIntent);
                        break;
                }
                return true;
            }
        });

    }
    private void init() {
        showDialog = new ShowDialog(this);
        recyclerView = findViewById(R.id.rv_blogs);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //Set list vào adapter
//        blogAdapter = new BlogAdapter(this, list);
//        recyclerView.setAdapter(blogAdapter);
        callApiGetPost();

    }
    private void callApiGetPost(){
        ApiService.apiservice.getlistpost().enqueue(new Callback<List<Blog>>() {
            @Override
            public void onResponse(Call<List<Blog>> call, Response<List<Blog>> response) {
                ArrayList<Blog>list_post= (ArrayList<Blog>) response.body();
                blogAdapter = new BlogAdapter(BlogActivity.this, list_post);
                recyclerView.setAdapter(blogAdapter);
            }

            @Override
            public void onFailure(Call<List<Blog>> call, Throwable t) {
                Toast.makeText(BlogActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_giohang, menu);
        getMenuInflater().inflate(R.menu.menu_danhmuc, menu);
        getMenuInflater().inflate(R.menu.menudangnhap, menu);
        MenuItem tendangnhap = menu.findItem(R.id.tendangnhap);
        SharedPreferences sharedPreferences = getSharedPreferences("taikhoan", MODE_PRIVATE);
        String name=sharedPreferences.getString("tendangnhap","");
        tendangnhap.setTitle(name);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.dangxuat:
                showDialog.show("Đăng xuất thành công!");
                SharedPreferences sharedPreferences = getSharedPreferences("taikhoan", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("khachhang_id");
                editor.remove("tendangnhap");
                editor.apply();

                return true;
            case R.id.action_donhang:
                Intent donhangIntent = new Intent(BlogActivity.this, DonhangActivity.class);
                startActivity(donhangIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
