package com.example.appbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanhang.R;
import com.example.appbanhang.model.Item;

import java.text.DecimalFormat;
import java.util.List;

public class ChiTietDonHangAdapter extends RecyclerView.Adapter<ChiTietDonHangAdapter.MyViewHolder> {
    Context context;
    List<Item> itemlist;

    public ChiTietDonHangAdapter(Context context, List<Item> itemlist) {
        this.context = context;
        this.itemlist = itemlist;
    }

    public ChiTietDonHangAdapter(List<Item> itemlist) {
        this.itemlist = itemlist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitiet_donhang,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item  = itemlist.get(position);
        holder.txtten.setText(item.getTensp()+ "");
        holder.txtsoluong.setText("Số lượng: "+ item.getSoluong() + "");
        Glide.with(context).load(item.getHinhanh()).into(holder.imgChiTiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgia.setText("Giá: "+decimalFormat.format((item.getGia())));

    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imgChiTiet;
        TextView txtten, txtsoluong, txtgia;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgChiTiet = itemView.findViewById(R.id.item_imgchitiet_donhang);
            txtten = itemView.findViewById(R.id.item_tenspchitiet_donhang);
            txtsoluong = itemView.findViewById(R.id.item_soluongchitiet_donhang);
            txtgia = itemView.findViewById(R.id.item_giachitiet_donhang);
        }
    }
}
