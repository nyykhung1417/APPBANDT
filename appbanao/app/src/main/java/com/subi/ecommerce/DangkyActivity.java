package com.subi.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.subi.ecommerce.adapter.ShowDialog;
import com.subi.ecommerce.api.ApiService;
import com.subi.ecommerce.model.Taikhoan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangkyActivity extends AppCompatActivity {
    TextView txtdangnhap;
    EditText hoten,sdt,diachi,email,matkhau;
    Button btndangky;
    private ShowDialog showDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Đăng ký");
        setContentView(R.layout.activity_dangky);
        txtdangnhap = findViewById(R.id.txtdangnhap);
        txtdangnhap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DangnhapActivity.class);
                startActivity(intent);
            }
        });

        hoten=findViewById(R.id.hoten);
        sdt=findViewById(R.id.sdt);
        diachi=findViewById(R.id.diachi);
        email=findViewById(R.id.email);
        matkhau=findViewById(R.id.matkhau);
        btndangky=findViewById(R.id.btndangky);

        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Call<Taikhoan> taikhoan= ApiService.apiservice.dangky(hoten.getText().toString(),sdt.getText().toString(),diachi.getText().toString(),email.getText().toString(),matkhau.getText().toString());
                taikhoan.enqueue(new Callback<Taikhoan>() {
                    @Override
                    public void onResponse(Call<Taikhoan> call, Response<Taikhoan> response) {
//                        showDialog.show("Đăng ký thành công!");
                        Intent intent = new Intent(getApplicationContext(), DangnhapActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Taikhoan> call, Throwable t) {

                    }
                });
            }
        });
    }
}