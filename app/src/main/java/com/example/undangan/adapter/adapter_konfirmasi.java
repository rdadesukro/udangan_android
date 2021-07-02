package com.example.undangan.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.undangan.R;
import com.example.undangan.model.konfrimasi.IsiItem_konfirmasi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class adapter_konfirmasi extends RecyclerView.Adapter<adapter_konfirmasi.HolderData> {
    private static CountDownTimer countDownTimer;
    String kriim;
    private List<String> array_syarat = new ArrayList<>();
    String sts,tgl;
    private int animation_type = 0;
    public adapter_konfirmasi(Context ctx, List<IsiItem_konfirmasi> mList , int animation_type) {
        this.kriim = kriim;
        this.animation_type = animation_type;
        this.mList = mList;
        this.ctx = ctx;

    }

    private List<IsiItem_konfirmasi> mList ;
    private Context ctx;

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_konfirmasi,parent, false);
            HolderData holder = new HolderData(layout);
            return holder;
    }


    @SuppressLint("ResourceAsColor")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final HolderData holder, int position) {
        final IsiItem_konfirmasi dm = mList.get(position);
        holder.tgl.setText(dm.getCreatedAt());



        holder.judul.setText(dm.getNama());
        holder.txt_body.setText(dm.getUcapan());
        try {

        }catch (Exception e){
            Log.i("status_baca", "onBindViewHolder: "+dm.getBaca()+" "+e);
        }

//        if (!dm.getBaca().equals("1")){
//            holder.img_belum_baca.setVisibility(View.VISIBLE);
//            holder.cardView. setBackgroundResource(R.color.grey_200);
//
//
//        }else {
//            holder.img_belum_baca.setVisibility(View.GONE);
////            holder.status.setBackgroundResource(R.drawable.bg_nama);
////            holder.status.setText("News");
//        }





        holder.dm = dm;



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends  RecyclerView.ViewHolder {

        @BindView(R.id.txt_nama)
        TextView judul;

        @BindView(R.id.txt_tgl)
        TextView tgl;

        @BindView(R.id.txt_ucapan)
        TextView txt_body;

        @BindView(R.id.img_new)
        ImageView img_new;


        @BindView(R.id.img_belum_baca)
        ImageView img_belum_baca;


        @BindView(R.id.card)
        CardView cardView;


        IsiItem_konfirmasi dm;


        public HolderData(View v) {
            super(v);
            ButterKnife.bind(this, itemView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Intent goInput = new Intent(ctx, menu_detail_notif.class);
//                    Guru.putString("judul_notif", dm.getTitle());
//                    Guru.putString("body_notif", dm.getBody());
//                    Guru.putString("id_notif", String.valueOf(dm.getId()));
//                    Guru.putString("konten_notif", dm.getKonten());
//                    Guru.putString("status_notif", dm.getUserStatusRead());
//                    Guru.putString("tgl_notif", dm.getCreatedAt());
//                    ctx.startActivity(goInput);
//                    CustomIntent.customType(ctx, "bottom-to-up");
                }
            });


        }


    }
    private static void check(int[] arr, int toCheckValue)
    {
        boolean test = false;
        for (int element : arr) {
            if (element == toCheckValue) {
                test = true;
                break;
            }
        }

        // Print the result
        System.out.println("Is " + toCheckValue
                + " present in the array: " + test);
    }

}
