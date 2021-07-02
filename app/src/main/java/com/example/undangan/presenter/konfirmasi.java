package com.example.undangan.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.example.undangan.Server.ApiRequest;
import com.example.undangan.Server.Retroserver_server_AUTH;
import com.example.undangan.model.konfrimasi.IsiItem_konfirmasi;
import com.example.undangan.model.konfrimasi.Response_konfirmasi;
import com.example.undangan.model.simpan.Response_simpan;
import com.example.undangan.model.tamu.IsiItem_tamu;
import com.example.undangan.model.tamu.Response_tamu;
import com.example.undangan.view_konfirmasi;
import com.jeevandeshmukh.glidetoastlib.GlideToast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class konfirmasi {

    private Context ctx;
    private view_konfirmasi countryView;
    private Retroserver_server_AUTH countryService;
    Call<Response_simpan> sendbio;
    public konfirmasi(view_konfirmasi view, Context ctx) {
        this.countryView = view;
        this.ctx = ctx;

        if (this.countryService == null) {
            this.countryService = new Retroserver_server_AUTH();
        }
    }


    public void get_konfirmasi(){
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Call<Response_konfirmasi> call = api.get_konfirmasi();
        call.enqueue(new Callback<Response_konfirmasi>() {
            @Override
            public void onResponse(Call<Response_konfirmasi> call, Response<Response_konfirmasi> response) {

                try {

                    if (response.isSuccessful()) {
                        Response_konfirmasi data = response.body();
                        Log.i("isi_notif", "onResponse: "+data);
                        if (data != null && data.getIsi() != null) {
                            List<IsiItem_konfirmasi> result = data.getIsi();
                           // badge.setNumber(jumlah_notif);
                            countryView.konfirmasi(result);
                        }



                    } else {
                        // error case
                        switch (response.code()) {
                            case 404:
                                Toast.makeText(ctx, "not found", Toast.LENGTH_SHORT).show();
                                break;
                            case 500:
                                Toast.makeText(ctx,"server broken", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                Toast.makeText(ctx, "Notif error", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                } catch (Exception e) {
                    Log.e("onResponse", "There is an error" + e);
                    //data();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Response_konfirmasi> call, Throwable t) {
                t.printStackTrace();
                //  sliderView_bener.setBackgroundResource(R.drawable.bg_no_item_city);
                Log.i("ewkwkwkwkw", "onFailure: " + t);
                if (t instanceof IOException) {

                } else {

                }
            }
        });
    }
    public void get_tamu(){
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Call<Response_tamu> call = api.get_tamu();
        call.enqueue(new Callback<Response_tamu>() {
            @Override
            public void onResponse(Call<Response_tamu> call, Response<Response_tamu> response) {

                try {

                    if (response.isSuccessful()) {
                        Response_tamu data = response.body();
                        Log.i("ISI_TAMU", "onResponse: "+data);
                        if (data != null && data.getIsi() != null) {
                            List<IsiItem_tamu> result = data.getIsi();
                            // badge.setNumber(jumlah_notif);
                            countryView.tamu(result);
                        }



                    } else {
                        // error case
                        switch (response.code()) {
                            case 404:
                                Toast.makeText(ctx, "not found", Toast.LENGTH_SHORT).show();
                                break;
                            case 500:
                                Toast.makeText(ctx,"server broken", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                Toast.makeText(ctx, "Notif error", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }

                } catch (Exception e) {
                    Log.e("onResponse", "There is an error" + e);
                    //data();
                    e.printStackTrace();
                }

            }


            @Override
            public void onFailure(Call<Response_tamu> call, Throwable t) {
                t.printStackTrace();
                //  sliderView_bener.setBackgroundResource(R.drawable.bg_no_item_city);
                Log.i("ewkwkwkwkw", "onFailure: " + t);
                if (t instanceof IOException) {

                } else {

                }

            }
        });

    }

    public  void  action(String id, String nama,String jenis,ProgressDialog progressDialog ){
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("Simpan Data"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);

        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Log.i("Jenis", "action: "+jenis);

        if (jenis.equals("edit")){
            sendbio =    api.edit_tamu(nama,id);
        }else if (jenis.equals("hapus")){
            sendbio = api.hapus_tamu(id);
        }else if (jenis.equals("add")){
            sendbio = api.add_tamu(nama);
        }

        ProgressDialog finalProgressDialog = progressDialog;
        sendbio.enqueue(new Callback<Response_simpan>() {
            @Override
            public void onResponse(Call<Response_simpan> call, Response<Response_simpan> response) {

                String kode = response.body().getKode();
                String pesan = response.body().getMessage();
                Log.i("kode", "onResponse: " + kode);
                countryView.status(kode);

                if (kode.equals("1")) {

                    finalProgressDialog.dismiss();
                    new GlideToast.makeToast((Activity) ctx, ""+pesan, GlideToast.LENGTHLONG, GlideToast.SUCCESSTOAST, GlideToast.CENTER).show();

                } else {
                    new GlideToast.makeToast((Activity) ctx, ""+pesan, GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();

                    finalProgressDialog.dismiss();

                }

            }
            @Override
            public void onFailure(Call<Response_simpan> call, Throwable t) {
                Log.i("isi_error", "onFailure: "+t);

                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
            }
        });

    }

    }


