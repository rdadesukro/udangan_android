package com.example.undangan.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.undangan.R;
import com.example.undangan.model.tamu.IsiItem_tamu;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class adapter_tamu extends RecyclerView.Adapter<adapter_tamu.HolderData> {
    private static CountDownTimer countDownTimer;
    String kriim;
    String lat_new,lng_new;
    String lat,lng;
    String jenis;
    private int animation_type = 0;
    public adapter_tamu(Context ctx, List<IsiItem_tamu> mList , int animation_type, OnImageClickListener onImageClickListener) {
        this.jenis = jenis;
        this.animation_type = animation_type;
        this.mList = mList;
        this.ctx = ctx;
        this.onImageClickListener = onImageClickListener;

    }
    public interface OnImageClickListener {
        void onImageClick(String id, String nama_tamu,String uuid);
    }

    private List<IsiItem_tamu> mList ;
    private Context ctx;
    private OnImageClickListener onImageClickListener;

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout;
        HolderData holder;
            layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_tamu,parent, false);
            holder = new HolderData(layout);

            return holder;
    }


    @SuppressLint("ResourceAsColor")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final HolderData holder, int position) {
        final IsiItem_tamu dm = mList.get(position);
        holder.txt_nama.setText(dm.getNama());

        holder.dm = dm;
        setAnimation(holder.itemView,position);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends  RecyclerView.ViewHolder {

        @BindView(R.id.txt_nama)
        TextView txt_nama;

        @BindView(R.id.btn_edit)
        ImageView btn_edit;



        IsiItem_tamu dm;

        public HolderData(View v) {
            super(v);
            ButterKnife.bind(this, itemView);

            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onImageClickListener.onImageClick(String.valueOf(dm.getId()), String.valueOf(dm.getNama()),dm.getUuid());
                }
            });

        }

    }

    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
           // ItemAnimation.animate(view, on_attach ? position : -1, animation_type);
            lastPosition = position;
        }
    }
}
