package com.subi.ecommerce.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.subi.ecommerce.model.Blog;
import com.subi.ecommerce.model.Danhmuc;
import com.subi.ecommerce.model.Donhang;
import com.subi.ecommerce.model.GioHang;
import com.subi.ecommerce.model.Sanpham;
import com.subi.ecommerce.model.Slide;
import com.subi.ecommerce.model.Taikhoan;
//import com.subi.ecommerce.model.Slide;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiservice = new Retrofit.Builder()
            .baseUrl("https://tuananh.unitopcv.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("products")
    Call<List<Sanpham>> getlistproduct();

    @GET("product_cat")
    Call<List<Danhmuc>> getlistproduct_cat();
    @GET("product/cat/{id}")
    Call<List<Sanpham>> getlistproduct_by_cat(@Path("id") int id);
    @GET("products/sale")
    Call<List<Sanpham>> getlistproductSale();
    @GET("posts")
    Call<List<Blog>> getlistpost();

    @GET("slides")
    Call<List<Slide>> getlistslide();
    @GET("carts/{khachhang_id}")
    Call<List<GioHang>> getlistgiohang(@Path("khachhang_id") int khachhang_id);
    @FormUrlEncoded
    @POST("carts/add")
    Call<GioHang> add_giohang(@Field("idSanPham") int idSanPham, @Field("taikhoan_id") int taikhoan_id);
    @GET("carts/delete/{id}")
    Call<GioHang> deletegiohang(@Path("id") int id);
    @FormUrlEncoded
    @POST("cart/checkout")
    Call<Taikhoan> checkout(@Field("sdt") String sdt,@Field("hoten") String hoten,@Field("diachi") String diachi, @Field("taikhoan_id") int taikhoan_id);
    @FormUrlEncoded
    @POST("taikhoan/dangky")
    Call<Taikhoan> dangky( @Field("hoten") String hoten, @Field("sdt") String sdt, @Field("diachi") String diachi, @Field("email") String email,@Field("matkhau") String matkhau);

    @FormUrlEncoded
    @POST("taikhoan/dangnhap")
    Call<List<Taikhoan>> dangnhap(@Field("email") String email,@Field("matkhau") String matkhau);

    @GET("donhang/{khachhang_id}")
    Call<List<Donhang>> getlistdonhang(@Path("khachhang_id") int khachhang_id);
}