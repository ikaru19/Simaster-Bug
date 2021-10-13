package com.ikaru19.simaster_bug.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ikaru19.simaster_bug.R;
import com.ikaru19.simaster_bug.models.Bts;

import java.util.List;

public class BtsAdapter extends BaseQuickAdapter<Bts, BaseViewHolder> {

    public BtsAdapter(@Nullable List<Bts> data) {
        super(R.layout.item_bts,data);
    }

    public void refill(List<Bts> data){
        this.mData = data;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Bts item) {
        helper.setText(R.id.tv_bts_judul,item.getJudul());
        String tanggal = item.getDateCreated();
        tanggal = tanggal.replaceAll("\\s.*", "");
        String[] parts = tanggal.split("-");
        tanggal = parts[2] +"-" + parts[1] + "-" + parts[0];
        String detail = "Oleh: " + item.getPenulis() + ", Tanggal: " + tanggal;
        helper.setText(R.id.tv_bts_detail,detail);
    }
}
