package com.subi.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.subi.ecommerce.adapter.ShowDialog;
import com.subi.ecommerce.api.ApiService;
import com.subi.ecommerce.model.Slide;
import com.subi.ecommerce.model.Taikhoan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangnhapActivity extends AppCompatActivity {

    TextView txtdangky;
    private ShowDialog showDialog;
    EditText email,matkhau;
    Button btndangnhap;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showDialog = new ShowDialog(this);
        setContentView(R.layout.activity_dangnhap);
        setTitle("Đăng nhập");
        txtdangky = findViewById(R.id.txtdangky);
        txtdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dangkyIntent = new Intent(DangnhapActivity.this, DangkyActivity.class);
                startActivity(dangkyIntent);

            }
        });
        email = findViewById(R.id.email1);
        matkhau = findViewById(R.id.matkhau1);

        btndangnhap = findViewById(R.id.btndangnhap1);

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<Taikhoan>> taikhoanCall = ApiService.apiservice.dangnhap(email.getText().toString(), matkhau.getText().toString());
                taikhoanCall.enqueue(new Callback<List<Taikhoan>>() {
                    @Override
                    public void onResponse(Call<List<Taikhoan>> call, Response<List<Taikhoan>> response) {
                        ArrayList<Taikhoan> taikhoans = (ArrayList<Taikhoan>) response.body();
                        if (taikhoans != null) {
                            for (Taikhoan tk : taikhoans) {
                                SharedPreferences sharedPreferences = getSharedPreferences("taikhoan", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("khachhang_id", tk.getId());
                                editor.putString("tendangnhap", tk.getHoten());
                                editor.apply();

                            }

                        }

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        showDialog.show("Đăng nhập thành công!");
                    }

                    @Override
                    public void onFailure(Call<List<Taikhoan>> call, Throwable t) {
                        Toast.makeText(DangnhapActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}