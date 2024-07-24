package com.subi.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;
import com.subi.ecommerce.adapter.ShowDialog;
import com.subi.ecommerce.model.Blog;

public class Detail_blogActivity extends AppCompatActivity {

    private Blog blog;

    private ImageView imageView;
    private TextView tieude, thoigian, noidung;
    private ShowDialog showDialog;

    private Button Like , Share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_blog);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        blog =(Blog)getIntent().getSerializableExtra("blog");
        init();
        setTitle("Blog");
        try {
            tieude.setText(blog.getTieude());
            thoigian.setText(blog.getNgaydang());

//            mota.setText(sanpham.getMoTa());
            noidung.setText(Html.fromHtml(blog.getMotachitiet()), TextView.BufferType.SPANNABLE);

            Picasso.get()
                    .load(blog.getImage())
                    .fit()
                    .into(imageView);
        } catch (Exception e) {

        }

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Intent mainIntent = new Intent(Detail_blogActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        break;

                    case R.id.action_account:
                        Intent dangkyIntent = new Intent(Detail_blogActivity.this, DangnhapActivity.class);
                        startActivity(dangkyIntent);
                        break;

                    case R.id.action_cart:
                        Intent cartIntent = new Intent(Detail_blogActivity.this, GioHangActivity.class);
                        startActivity(cartIntent);
                        break;
                    case R.id.action_blog:
                        Intent blogIntent = new Intent(Detail_blogActivity.this, BlogActivity.class);
                        startActivity(blogIntent);
                        break;
                    case R.id.action_product:
                        Intent productIntent = new Intent(Detail_blogActivity.this, ProductActivity.class);
                        startActivity(productIntent);
                        break;
                }
                return true;
            }
        });
    }
    private void init() {
        showDialog = new ShowDialog(this);
        imageView = findViewById(R.id.imageView);
        tieude = findViewById(R.id.TieuDe);
        noidung = findViewById(R.id.noidung);
        thoigian = findViewById(R.id.thoigian);

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
                Intent donhangIntent = new Intent(Detail_blogActivity.this, DonhangActivity.class);
                startActivity(donhangIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}