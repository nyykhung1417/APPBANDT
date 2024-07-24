package com.subi.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;
import android.widget.ViewFlipper;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputEditText;
import com.subi.ecommerce.adapter.DanhmucAdapter;
import com.subi.ecommerce.adapter.SanPhamAdapter;
import com.subi.ecommerce.adapter.ShowDialog;
import com.subi.ecommerce.api.ApiService;
import com.subi.ecommerce.model.Blog;
import com.subi.ecommerce.model.Danhmuc;
import com.subi.ecommerce.model.Sanpham;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    SanPhamAdapter sanPhamAdapter;
    private Danhmuc danhmuc;
    private RecyclerView recyclerView;
    private TextInputEditText find;
    private ShowDialog showDialog;
    TextView tendanhmuc;
    ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        danhmuc =(Danhmuc) getIntent().getSerializableExtra("danhmuc");
        init();
        //Set tiêu đề
        setTitle("iSMart");
        //Set tìm kiếm
        setFindNews();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Intent mainIntent = new Intent(ProductActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        break;

                    case R.id.action_account:
                        Intent dangkyIntent = new Intent(ProductActivity.this, DangnhapActivity.class);
                        startActivity(dangkyIntent);
                        break;

                    case R.id.action_cart:
                        Intent cartIntent = new Intent(ProductActivity.this, GioHangActivity.class);
                        startActivity(cartIntent);
                        break;
                    case R.id.action_blog:
                        Intent blogIntent = new Intent(ProductActivity.this, BlogActivity.class);
                        startActivity(blogIntent);
                        break;
                    case R.id.action_product:
                        Intent productIntent = new Intent(ProductActivity.this, ProductActivity.class);
                        startActivity(productIntent);
                        break;
                }
                return true;
            }
        });
    }
    private void setFindNews() {
        recyclerView.setFilterTouchesWhenObscured(true);
        find.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int
                    count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    sanPhamAdapter.resetData();
                }
                sanPhamAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    private void init() {
        showDialog = new ShowDialog(this);
        find = findViewById(R.id.tvFind);
        recyclerView = findViewById(R.id.rcvListNews);
        tendanhmuc=findViewById(R.id.tendanhmuc);
        getlistproductbycat();
        //Cài đặt layout cho list, set cố cột là 2 cột
        LinearLayoutManager layoutManager
                = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        //Set list vào adapter
        viewFlipper = findViewById(R.id.viewlipper);

    }
    private void getlistproductbycat(){
        int id;
        if(null != danhmuc){
            id=danhmuc.getId();
            tendanhmuc.setText(danhmuc.getTendanhmuc());
        }
        else{
            id=0;
        }
        ApiService.apiservice.getlistproduct_by_cat(id).enqueue(new Callback<List<Sanpham>>() {
            @Override
            public void onResponse(Call<List<Sanpham>> call, Response<List<Sanpham>> response) {
                ArrayList<Sanpham> listsanpham= (ArrayList<Sanpham>) response.body();
                sanPhamAdapter = new SanPhamAdapter(ProductActivity.this, listsanpham);
                recyclerView.setAdapter(sanPhamAdapter);
            }

            @Override
            public void onFailure(Call<List<Sanpham>> call, Throwable t) {

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
                Intent donhangIntent = new Intent(ProductActivity.this, DonhangActivity.class);
                startActivity(donhangIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}