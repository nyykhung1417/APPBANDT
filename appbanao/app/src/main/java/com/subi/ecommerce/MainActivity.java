package com.subi.ecommerce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputEditText;

import com.subi.ecommerce.adapter.DanhmucAdapter;
import com.subi.ecommerce.adapter.SanPhamAdapter;
import com.subi.ecommerce.adapter.ShowDialog;
import com.subi.ecommerce.api.ApiService;
import com.subi.ecommerce.model.Danhmuc;
import com.subi.ecommerce.model.Sanpham;
import com.subi.ecommerce.model.Slide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {

    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    FragmentActivity fragmentActivity;
    Toolbar toolbar;
    FloatingActionButton fab;
//    private ArrayList<Sanpham> list = new Sanpham().getAll();
    SanPhamAdapter sanPhamAdapter;
    SanPhamAdapter sanPhamAdaptersale;

    DanhmucAdapter danhmucAdapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewsale;
    private RecyclerView recyclerDanhmuc;
    private TextInputEditText find;
    private ShowDialog showDialog;
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //Set tiêu đề
        setTitle("iSMart");
        //Set tìm kiếm
        setFindNews();
        ActionViewFlipper();
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        drawerLayout
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Intent mainIntent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        break;

                    case R.id.action_account:
                        Intent dangkyIntent = new Intent(MainActivity.this, DangnhapActivity.class);
                        startActivity(dangkyIntent);
                        break;

                    case R.id.action_cart:
                        Intent cartIntent = new Intent(MainActivity.this, GioHangActivity.class);
                        startActivity(cartIntent);
                        break;
                    case R.id.action_blog:
                        Intent blogIntent = new Intent(MainActivity.this, BlogActivity.class);
                        startActivity(blogIntent);
                        break;
                    case R.id.action_product:
                        Intent productIntent = new Intent(MainActivity.this, ProductActivity.class);
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
        find = findViewById(R.id.tvFind);
        showDialog = new ShowDialog(this);
        recyclerView = findViewById(R.id.rcvListNews);
        recyclerViewsale=findViewById(R.id.recent_recycler);
        recyclerDanhmuc=findViewById(R.id.recycler_danhmuc);
        callApiGetProduct();
        callApiGetProductSale();
        callApiGetDanhmuc();
        //Cài đặt layout cho list, set cố cột là 2 cột
        LinearLayoutManager layoutManager
                = new GridLayoutManager(this, 2);


        recyclerViewsale.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerDanhmuc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        recyclerView.setLayoutManager(layoutManager);

        //Set list vào adapter

        viewFlipper = findViewById(R.id.viewlipper);
        callApiGetProduct();
    }
    private void callApiGetDanhmuc(){
        ApiService.apiservice.getlistproduct_cat().enqueue(new Callback<List<Danhmuc>>() {
            @Override
            public void onResponse(Call<List<Danhmuc>> call, Response<List<Danhmuc>> response) {
                ArrayList<Danhmuc>listdanhmuc= (ArrayList<Danhmuc>) response.body();
                danhmucAdapter = new DanhmucAdapter(MainActivity.this, listdanhmuc);
                recyclerDanhmuc.setAdapter(danhmucAdapter);
            }

            @Override
            public void onFailure(Call<List<Danhmuc>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void callApiGetProduct(){
        ApiService.apiservice.getlistproduct().enqueue(new Callback<List<Sanpham>>() {
            @Override
            public void onResponse(Call<List<Sanpham>> call, Response<List<Sanpham>> response) {
                ArrayList<Sanpham>listsanpham= (ArrayList<Sanpham>) response.body();
                sanPhamAdapter = new SanPhamAdapter(MainActivity.this, listsanpham);
                recyclerView.setAdapter(sanPhamAdapter);
            }



            @Override
            public void onFailure(Call<List<Sanpham>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void callApiGetProductSale(){
        ApiService.apiservice.getlistproductSale().enqueue(new Callback<List<Sanpham>>() {
            @Override
            public void onResponse(Call<List<Sanpham>> call, Response<List<Sanpham>> response) {
                ArrayList<Sanpham>listsanpham= (ArrayList<Sanpham>) response.body();
                sanPhamAdaptersale = new SanPhamAdapter(MainActivity.this, listsanpham);
                recyclerViewsale.setAdapter(sanPhamAdaptersale);
            }

            @Override
            public void onFailure(Call<List<Sanpham>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void ActionViewFlipper(){
        ArrayList<String> mangquangcao = new ArrayList<>();
        ApiService.apiservice.getlistslide().enqueue(new Callback<List<Slide>>() {
            @Override
            public void onResponse(Call<List<Slide>> call, Response<List<Slide>> response) {
                ArrayList<Slide>listslide= (ArrayList<Slide>) response.body();
                if (listslide != null) {
                    for (Slide slide : listslide) {
                        String img = slide.getImage();
                        mangquangcao.add(slide.getImage());
                    }
                    for ( int i = 0 ; i<mangquangcao.size();i++){
                        ImageView imageView = new ImageView(getApplicationContext());
                        Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        viewFlipper.addView(imageView);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Slide>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        // set animation cho viewflip
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);

        }
        else {
            super.onBackPressed();
        }

    }

    //icon giỏ hàng
    //Set menu cho action bar
    @Override
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
                Intent donhangIntent = new Intent(MainActivity.this, DonhangActivity.class);
                startActivity(donhangIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}