package com.example.undangan.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import com.example.undangan.MainActivity;
import com.example.undangan.Server.ApiRequest;
import com.example.undangan.Server.Retroserver_server_AUTH;
import com.example.undangan.menu_login;
import com.example.undangan.model.login.Response_login;
import com.example.undangan.model.simpan.Response_simpan;
import com.example.undangan.model.total.Response_total;
import com.example.undangan.view_login;
import com.github.squti.guru.Guru;
import com.jeevandeshmukh.glidetoastlib.GlideToast;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login {

    private Context ctx;
    private view_login countryView;
    private Retroserver_server_AUTH countryService;
    //private Server_umum Server_umum;
    public login(view_login view, Context ctx) {
        this.countryView = view;
        this.ctx = ctx;

        if (this.countryService == null) {
            this.countryService = new Retroserver_server_AUTH();
          // this.Server_umum = new Server_umum();
        }
    }


    public void login(String username, String password, ProgressDialog pDialog) {
        pDialog = new ProgressDialog(ctx);
        pDialog.setMessage("Logging In...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        ProgressDialog finalPDialog = pDialog;
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Call<Response_login> sendbio = api.login(username,password);

        sendbio.enqueue(new Callback<Response_login>() {
            @Override
            public void onResponse(Call<Response_login> call, Response<Response_login> response) {

                try {
                    String kode = response.body().getKode();
                    Log.i("kode", "onResponse: " + kode);

                    if (kode.equals("1")) {
                        finalPDialog.dismiss();
                        new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.SUCCESSTOAST, GlideToast.CENTER).show();
                        Guru.putString("status_loign", "true");

                        Guru.putString("auth", response.body().getAccessToken());
                        Intent intent = new Intent((Activity) ctx, MainActivity.class);
                        intent.putExtra("Fragmentone", 3); //pass zero for Fragmentone.
                        ctx.startActivity(intent);
                    } else {
                        finalPDialog.dismiss();
                        new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();
                    }
                }catch (Exception e){
                    Log.i("cek_error_login", "onResponse: "+e);
                    finalPDialog.dismiss();
                }



            }
            @Override
            public void onFailure(Call<Response_login> call, Throwable t) {

                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
            }
        });

    }
    public  void logout(ProgressDialog pDialog){
        pDialog = new ProgressDialog(ctx);
        pDialog.setMessage("Logout...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        ProgressDialog finalPDialog = pDialog;
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Call<Response_login> sendbio = api.logout();

        sendbio.enqueue(new Callback<Response_login>() {
            @Override
            public void onResponse(Call<Response_login> call, Response<Response_login> response) {

                String kode = response.body().getKode();
                Log.i("kode", "onResponse: " + kode);

                if (kode.equals("1")) {
                    finalPDialog.dismiss();
                    Guru.putString("status_loign", "false");
                    Guru.putString("auth", "");
                    Intent intent = new Intent((Activity) ctx, menu_login.class);
                    intent.putExtra("Fragmentone", 3);
                    ctx.startActivity(intent);

                } else {
                    new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();

                }

            }
            @Override
            public void onFailure(Call<Response_login> call, Throwable t) {

                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
            }
        });
    }
    public  void total(String jenis){
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Call<Response_total> sendbio = api.total(jenis);

        sendbio.enqueue(new Callback<Response_total>() {
            @Override
            public void onResponse(Call<Response_total> call, Response<Response_total> response) {

                try {
                    String total = String.valueOf(response.body().getTotal());
                    countryView.total(total);
                    Log.i("total", "onResponse: "+total);
                }catch (Exception E){

                }







            }
            @Override
            public void onFailure(Call<Response_total> call, Throwable t) {

                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
            }
        });
    }

}


