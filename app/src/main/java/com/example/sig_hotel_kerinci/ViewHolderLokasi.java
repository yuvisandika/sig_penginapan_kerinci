package com.example.sig_hotel_kerinci;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sig_hotel_kerinci.R;

import java.util.List;


public class ViewHolderLokasi extends RecyclerView.Adapter<ViewHolderLokasi.MyViewHolder> {


    Context mContext;
    List<AdapterLokasi> mData ;

    public ViewHolderLokasi(Context mContext, List<AdapterLokasi> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.row,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textView1.setText(mData.get(position).getNama());
        holder.textView2.setText(mData.get(position).getAlamat());
        Glide.with(mContext).load(mData.get(position).getImageUrl()).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView1,textView2;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageview1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent postDetailActivity = new Intent(mContext,LokasiViewActivity.class);
                    int position = getAdapterPosition();


                    postDetailActivity.putExtra("lokasiid",mData.get(position).getLokasiid());

                    postDetailActivity.putExtra("imageUrl",mData.get(position).getImageUrl());

                    postDetailActivity.putExtra("video",mData.get(position).getVideo());

                    postDetailActivity.putExtra("nama",mData.get(position).getNama());
                    postDetailActivity.putExtra("alamat",mData.get(position).getAlamat());
                    postDetailActivity.putExtra("nomor",mData.get(position).getNomor());

                    postDetailActivity.putExtra("latitude",mData.get(position).getLatitude());
                    postDetailActivity.putExtra("longitude",mData.get(position).getLongitude());

                    postDetailActivity.putExtra("tipe1",mData.get(position).getTipe1());
                    postDetailActivity.putExtra("tipe2",mData.get(position).getTipe2());
                    postDetailActivity.putExtra("tipe3",mData.get(position).getTipe3());

                    postDetailActivity.putExtra("harga1",mData.get(position).getHarga1());
                    postDetailActivity.putExtra("harga2",mData.get(position).getHarga2());
                    postDetailActivity.putExtra("harga3",mData.get(position).getHarga3());

                    mContext.startActivity(postDetailActivity);

                }
            });

        }

    }
}