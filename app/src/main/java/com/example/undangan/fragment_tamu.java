package com.example.undangan;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.undangan.adapter.adapter_tamu;
import com.example.undangan.model.konfrimasi.IsiItem_konfirmasi;
import com.example.undangan.model.tamu.IsiItem_tamu;
import com.example.undangan.presenter.konfirmasi;
import com.example.undangan.presenter.login;
import com.github.squti.guru.Guru;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jeevandeshmukh.glidetoastlib.GlideToast;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.net.ssl.SSLContext;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.TlsVersion;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_tamu extends Fragment implements view_konfirmasi, com.example.undangan.adapter.adapter_tamu.OnImageClickListener {

    int conunter = 10;

    @BindView(R.id.rv_aku)
    RecyclerView Recycler;
    int page_new;
    konfirmasi konfirmasi;
    private TextView txtTdkAda;
    private ImageView imgData2;
    private SwipeRefreshLayout swifeRefresh;
    private RecyclerView rvAku;
    private ProgressBar progressBar;

    private com.example.undangan.adapter.adapter_tamu adapter_tamu;
    private FloatingActionButton btnPanggil;
    BottomSheetDialog dialog;
    EditText nama;
    ProgressDialog progressDialog;
    AlertDialog.Builder acion;
    private FloatingActionButton btnShare;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

    }

    public fragment_tamu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tamu, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        try {
            ProviderInstaller.installIfNeeded(getContext());
            SSLContext sslContext;
            sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, null);
            sslContext.createSSLEngine();
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException
                | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
                .tlsVersions(TlsVersion.TLS_1_0)
                .cipherSuites(
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                .allEnabledTlsVersions()
                .supportsTlsExtensions(false)
                .allEnabledCipherSuites()
                .build();

        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle("");
        konfirmasi = new konfirmasi(this, getActivity());
        konfirmasi.get_tamu();

        swifeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                konfirmasi.get_tamu();

            }
        });


        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Insert Subject here");
                String app_url = " https://play.google.com/store/apps/details?id=my.example.javatpoint";
                shareIntent.putExtra(Intent.EXTRA_TEXT, "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم,\n" +
                        "\n" +
                        "Tanpa mengurangi rasa hormat, izinkan kami mengundang Bapak/Ibu/Saudara/i untuk hadir serta memberikan do'a restu pada acara pernikahan kami.\n" +
                        "\n" +
                        "Untuk detail acara, bisa klik link dibawah ini:\n" +
                        "https://undangan.jambikota.go.id/patim&ade\n" +
                        "\n" +
                        "Merupakan suatu kehormatan dan kebahagiaan bagi kami, apabila Bapak/Ibu/Saudara/i berkenan hadir.\n" +
                        "\n" +
                        "Do'a restu Anda merupakan hadiah terindah bagi kami.\n" +
                        "\n" +
                        "Jazakumullahu Khairan, hanya kepada Allah, Tuhan Yang Maha Esa tercurah do'a sebagai ungkapan terima kasih kami.\n" +
                        "\n" +
                        "\uD83D\uDCCC Do'a untuk mempelai\n" +
                        "بَارَكَ اللهُ لَكَ وَبَارَكَ عَلَيْكَ وَجَمَعَ بَيْنَكُمَا فِي خَيْرٍ\n" +
                        "\"Semoga Allah memberkahimu di waktu bahagia dan memberkahimu di waktu susah, serta semoga Allah mempersatukan kalian berdua dalam kebaikan” (HR. Abu Dawud no. 2130).\n" +
                        "\n" +
                        "Sincerely,\n" +
                        "Patim dan Ade");
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });

        btnPanggil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = new BottomSheetDialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_tambah_tamu);
                dialog.setCancelable(true);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                dialog.getWindow().setAttributes(lp);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                dialog.getWindow().setDimAmount(0.5f);
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                nama = (EditText) dialog.findViewById(R.id.edit_nama_tamu);
                Button btn_simpan = (Button) dialog.findViewById(R.id.btn_simpan);
                ImageView btn_close = (ImageView) dialog.findViewById(R.id.btn_close);

                btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });

                btn_simpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (nama.getText().toString().equals("")) {
                            //  Toast.makeText(menu_profil_pejabat_pejabat.this, "Password lama tidak boleh kosong", Toast.LENGTH_SHORT);
                            new GlideToast.makeToast(getActivity(), "Nama Tidak Boleh Kosong", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();
                            nama.requestFocus();
                        } else {
                            konfirmasi.action("id", nama.getText().toString().trim(), "add", progressDialog);
                        }


                    }
                });

                dialog.show();

            }
        });


        return view;


    }

    @Override
    public void onResume() {
        super.onResume();


    }


    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void konfirmasi(List<IsiItem_konfirmasi> konfirmasi) {

    }

    @Override
    public void tamu(List<IsiItem_tamu> tamu) {
        Log.i("ISI_TAMU", "event: " + tamu.size());
        adapter_tamu = new adapter_tamu(getActivity(), tamu, 1, this::onImageClick);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        Recycler.setLayoutManager(layoutManager);
        Recycler.setAdapter(adapter_tamu);
        swifeRefresh.setRefreshing(false);
        if (tamu.size() == 0) {
            txtTdkAda.setVisibility(View.VISIBLE);
            imgData2.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        } else {
            txtTdkAda.setVisibility(View.GONE);
            imgData2.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);

        }
    }

    @Override
    public void status(String status) {
        if (status.equals("1")) {
            konfirmasi.get_tamu();

        } else {

        }
    }

    private void initView(View v) {
        txtTdkAda = v.findViewById(R.id.txt_tdk_ada);
        imgData2 = v.findViewById(R.id.img_data2);
        swifeRefresh = v.findViewById(R.id.swifeRefresh);
        rvAku = v.findViewById(R.id.rv_aku);
        progressBar = v.findViewById(R.id.progressBar);
        btnPanggil = v.findViewById(R.id.btn_tambah);
        btnShare = v.findViewById(R.id.btn_share);
    }

    @Override
    public void onImageClick(String id, String nama_tamu, String uuid) {
        final CharSequence[] dialogitem = {"Edit", "Delete", "Share"};
        acion = new AlertDialog.Builder(getActivity());
        acion.setCancelable(true);
        acion.setItems(dialogitem, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                switch (which) {
                    case 0:

                        edit_data(id, nama_tamu);

                        break;
                    case 1:
                        konfirmasi.action(id, "", "hapus", progressDialog);
                        break;
                    case 2:
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Insert Subject here");
                        String app_url = " https://play.google.com/store/apps/details?id=my.example.javatpoint";
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم,\n" +
                                "\n" +
                                "Dear " + nama_tamu +
                                "\n" +
                                "\n" +
                                "Tanpa mengurangi rasa hormat, izinkan kami mengundang Bapak/Ibu/Saudara/i untuk hadir serta memberikan do'a restu pada acara pernikahan kami.\n" +
                                "\n" +
                                "Untuk detail acara, bisa klik link dibawah ini:\n" +
                                "https://undangan.jambikota.go.id/patim&ade/" + uuid + "\n" +
                                "\n" +
                                "Merupakan suatu kehormatan dan kebahagiaan bagi kami, apabila Bapak/Ibu/Saudara/i berkenan hadir.\n" +
                                "\n" +
                                "Do'a restu Anda merupakan hadiah terindah bagi kami.\n" +
                                "\n" +
                                "Jazakumullahu Khairan, hanya kepada Allah, Tuhan Yang Maha Esa tercurah do'a sebagai ungkapan terima kasih kami.\n" +
                                "\n" +
                                "\uD83D\uDCCC Do'a untuk mempelai\n" +
                                "\n"+
                                "بَارَكَ اللهُ لَكَ وَبَارَكَ عَلَيْكَ وَجَمَعَ بَيْنَكُمَا فِي خَيْرٍ\n" +
                                "\"Semoga Allah memberkahimu di waktu bahagia dan memberkahimu di waktu susah, serta semoga Allah mempersatukan kalian berdua dalam kebaikan” (HR. Abu Dawud no. 2130).\n" +
                                "\n" +
                                "Sincerely,\n" +
                                "Patim dan Ade");
                        startActivity(Intent.createChooser(shareIntent, "Share via"));
                        break;
                }
            }
        }).show();
    }


    void edit_data(String id, String nama_tamu) {
        dialog = new BottomSheetDialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_tambah_tamu);
        dialog.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setDimAmount(0.5f);
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        nama = (EditText) dialog.findViewById(R.id.edit_nama_tamu);
        nama.setText(nama_tamu);
        Button btn_simpan = (Button) dialog.findViewById(R.id.btn_simpan);
        ImageView btn_close = (ImageView) dialog.findViewById(R.id.btn_close);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nama.getText().toString().equals("")) {
                    //  Toast.makeText(menu_profil_pejabat_pejabat.this, "Password lama tidak boleh kosong", Toast.LENGTH_SHORT);
                    new GlideToast.makeToast(getActivity(), "Nama Tidak Boleh Kosong", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();
                    nama.requestFocus();
                } else {
                    konfirmasi.action(id, nama.getText().toString().trim(), "edit", progressDialog);
                }


            }
        });

        dialog.show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.aksi, menu);
        MenuItem refres = menu.findItem(R.id.logout);
        refres.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE);
                pDialog.setTitleText("Apakah anda yakin ingin keluar ?");
                pDialog.setCancelable(false);
                pDialog.setConfirmText("Ya");
                pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        login countryPresenter = new login(null,getActivity());
                        countryPresenter.logout(progressDialog);

                    }
                });
                pDialog.setCancelButton("Tidak", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                });
                pDialog.setCanceledOnTouchOutside(false);
                pDialog.show();



                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

}
