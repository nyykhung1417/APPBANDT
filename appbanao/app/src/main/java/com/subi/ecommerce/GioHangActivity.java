package com.subi.ecommerce;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputEditText;
import com.subi.ecommerce.adapter.GioHangAdapter;
import com.subi.ecommerce.adapter.ShowDialog;
import com.subi.ecommerce.api.ApiService;
import com.subi.ecommerce.model.GioHang;
import com.subi.ecommerce.model.Taikhoan;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GioHangActivity extends AppCompatActivity {
//    private ArrayList<GioHang> list = new ArrayList<>();
    GioHangAdapter gioHangAdapter;

    private RecyclerView recyclerView;
    public static int sum = 0;
    public static Button pay;
    private ShowDialog showDialog;
    public static LinearLayout emty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gio_hang);
        init();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Intent mainIntent = new Intent(GioHangActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        break;

                    case R.id.action_account:
                        Intent dangkyIntent = new Intent(GioHangActivity.this, DangnhapActivity.class);
                        startActivity(dangkyIntent);
                        break;

                    case R.id.action_cart:
                        Intent cartIntent = new Intent(GioHangActivity.this, GioHangActivity.class);
                        startActivity(cartIntent);
                        break;
                    case R.id.action_blog:
                        Intent blogIntent = new Intent(GioHangActivity.this, BlogActivity.class);
                        startActivity(blogIntent);
                        break;
                    case R.id.action_product:
                        Intent productIntent = new Intent(GioHangActivity.this, ProductActivity.class);
                        startActivity(productIntent);
                        break;
                }
                return true;
            }
        });

        //Set tiêu đề
        setTitle("GIỎ HÀNG");

        //Khi ấn thanh toán
        pay.setOnClickListener(v -> {
            Dialog dialog = new Dialog(GioHangActivity.this);
            dialog.setContentView(R.layout.payment);
            dialog.setCancelable(true);
            Window window = dialog.getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (dialog != null && dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            //Khai báo biến
            TextInputEditText name = dialog.findViewById(R.id.edt_name);
            TextInputEditText address = dialog.findViewById(R.id.edt_address);
            TextInputEditText sdt = dialog.findViewById(R.id.edt_sdt);
            Button add = dialog.findViewById(R.id.btnThem);
            Button cancle = dialog.findViewById(R.id.btnHuy);

            //Khi ấn thnah toán
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nameText = name.getText().toString();
                    String sdtText = sdt.getText().toString();
                    String addressText = address.getText().toString();
                    if (nameText.isEmpty() || sdtText.isEmpty() || addressText.isEmpty()) {
                        showDialog.show("Không được để trống!");
                    } else if (sdtText.length() != 10) {
                        showDialog.show("Số điện thoại không chính xác");
                    } else {
                        NumberFormat format = NumberFormat.getCurrencyInstance();

                        SharedPreferences sharedPreferences = getSharedPreferences("taikhoan", MODE_PRIVATE);
                        int khachhang_id=sharedPreferences.getInt("khachhang_id",0);
                        ApiService.apiservice.checkout(sdtText,nameText,addressText,khachhang_id).enqueue(new Callback<Taikhoan>() {
                            @Override
                            public void onResponse(Call<Taikhoan> call, Response<Taikhoan> response) {
                                //showDialog.show("Đã thanh toán thành công!");
                                Intent intent = new Intent(GioHangActivity.this, GioHangActivity.class);
                                GioHangActivity.this.startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<Taikhoan> call, Throwable t) {

                            }
                        });
                    }
                }
            });

            //Khi ấn nút hủy
            cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        });

    }
    private void init() {
        recyclerView = findViewById(R.id.rv_list);
        pay = findViewById(R.id.btn_pay);
        emty = findViewById(R.id.ln_emty);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //Set list vào adapter
       getlistgiohang();
        showDialog = new ShowDialog(this);
    }

    private void getlistgiohang(){
        SharedPreferences sharedPreferences = getSharedPreferences("taikhoan", MODE_PRIVATE);
        int khachhang_id=sharedPreferences.getInt("khachhang_id",0);
        ApiService.apiservice.getlistgiohang(khachhang_id).enqueue(new Callback<List<GioHang>>() {
            @Override
            public void onResponse(Call<List<GioHang>> call, Response<List<GioHang>> response) {
                ArrayList<GioHang>listgiohang= (ArrayList<GioHang>) response.body();
                gioHangAdapter = new GioHangAdapter(GioHangActivity.this, listgiohang);
                recyclerView.setAdapter(gioHangAdapter);

            }

            @Override
            public void onFailure(Call<List<GioHang>> call, Throwable t) {

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
                Intent donhangIntent = new Intent(GioHangActivity.this, DonhangActivity.class);
                startActivity(donhangIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}