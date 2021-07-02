package com.example.undangan.Server;


import com.example.undangan.model.konfrimasi.Response_konfirmasi;
import com.example.undangan.model.simpan.Response_simpan;
import com.example.undangan.model.tamu.Response_tamu;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.POST;


public interface ApiRequest {



    @POST("tampil_data_hadir")
    Call<Response_konfirmasi> get_konfirmasi();


    @POST("tampil_nama_tamu")
    Call<Response_tamu> get_tamu();


    @FormUrlEncoded
    @POST("add_tamu")
    Call<Response_simpan> add_tamu(@Field("nama") String name);

    @FormUrlEncoded
    @POST("hapus_data")
    Call<Response_simpan> hapus_tamu(@Field("id") String id);

    @FormUrlEncoded
    @POST("edit_tamu")
    Call<Response_simpan> edit_tamu(@Field("nama") String name,
                                    @Field("id") String id);


}


