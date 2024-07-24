package com.subi.ecommerce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;
import com.subi.ecommerce.adapter.ShowDialog;
import com.subi.ecommerce.api.ApiService;
import com.subi.ecommerce.model.GioHang;
import com.subi.ecommerce.model.Sanpham;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private Sanpham sanpham;
    private ImageView imageView;
    private TextView name, price,price_sale, mota;
    private Button add;

    private ShowDialog showDialog;
    private GioHang gioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        sanpham = (Sanpham) getIntent().getSerializableExtra("sp");
        //Khai báo
        init();


        //Set tiêu đề
        setTitle("CHI TIẾT SẢN PHẨM");
        //set Data
        try {
            name.setText(sanpham.getTenSanPham());
            String discount= sanpham.getGiaSanPhamSale();
            if(discount.equals("0đ")){
                price.setText(sanpham.getGiaSanPham());
            }
            else{
                price.setText(sanpham.getGiaSanPhamSale());
            }
//            price.setText(sanpham.getGiaSanPham());

//            mota.setText(sanpham.getMoTa());
            mota.setText(Html.fromHtml(sanpham.getMoTa()), TextView.BufferType.SPANNABLE);

            Picasso.get()
                    .load(sanpham.getImage())
                    .fit()
                    .into(imageView);
        } catch (Exception e) {

        }

        //Khi bấm nút để thêm vào giỏ hàng
        add.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences("taikhoan", MODE_PRIVATE);
            int khachhang_id=sharedPreferences.getInt("khachhang_id",0);
            if(khachhang_id !=0) {
                Call<GioHang> giohang = ApiService.apiservice.add_giohang(sanpham.getId(), khachhang_id);
                giohang.enqueue(new Callback<GioHang>() {
                    @Override
                    public void onResponse(Call<GioHang> call, Response<GioHang> response) {
                        showDialog.show("Thêm vào giỏ hàng thành công!");
                    }

                    @Override
                    public void onFailure(Call<GioHang> call, Throwable t) {

                    }
                });
            }
            else{
                showDialog.show("Bạn phải đăng nhập trước khi thêm giỏ hàng!");
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Intent mainIntent = new Intent(DetailActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        break;

                    case R.id.action_account:
                        Toast.makeText(DetailActivity.this,"My Account",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_cart:
                        Intent cartIntent = new Intent(DetailActivity.this, GioHangActivity.class);
                        startActivity(cartIntent);
                        break;
                }
                return true;
            }
        });
    }

    private void init() {
        imageView = findViewById(R.id.ivNewsList);
        name = findViewById(R.id.one_name);
        price = findViewById(R.id.one_price);
//        price_sale = findViewById(R.id.one_price1);

        mota = findViewById(R.id.tv_mota);
        add = findViewById(R.id.btn_add);

        showDialog = new ShowDialog(this);
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
                Intent donhangIntent = new Intent(DetailActivity.this, DonhangActivity.class);
                startActivity(donhangIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}