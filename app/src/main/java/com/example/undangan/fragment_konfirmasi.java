package com.example.undangan;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.undangan.adapter.adapter_konfirmasi;
import com.example.undangan.model.konfrimasi.IsiItem_konfirmasi;
import com.example.undangan.model.tamu.IsiItem_tamu;
import com.example.undangan.presenter.konfirmasi;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.net.ssl.SSLContext;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.TlsVersion;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_konfirmasi extends Fragment implements view_konfirmasi {

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

    private com.example.undangan.adapter.adapter_konfirmasi adapter_konfirmasi;

    public fragment_konfirmasi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_konfirmasi, container, false);
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


        konfirmasi = new konfirmasi(this, getActivity());
        konfirmasi.get_konfirmasi();

        swifeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                konfirmasi.get_konfirmasi();

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
        Log.i("isi_event", "event: " + konfirmasi.size());
        adapter_konfirmasi = new adapter_konfirmasi(getActivity(), konfirmasi, 1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        Recycler.setLayoutManager(layoutManager);
        Recycler.setAdapter(adapter_konfirmasi);
        swifeRefresh.setRefreshing(false);
        if (konfirmasi.size() == 0) {
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
    public void tamu(List<IsiItem_tamu> tamu) {

    }

    @Override
    public void status(String status) {

    }

    private void initView(View v) {
        txtTdkAda = v.findViewById(R.id.txt_tdk_ada);
        imgData2 =  v.findViewById(R.id.img_data2);
        swifeRefresh =  v.findViewById(R.id.swifeRefresh);
        rvAku =  v.findViewById(R.id.rv_aku);
        progressBar =  v.findViewById(R.id.progressBar);
    }
}
