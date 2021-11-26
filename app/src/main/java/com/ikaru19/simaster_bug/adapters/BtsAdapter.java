package com.ikaru19.simaster_bug.adapters;

import static com.ikaru19.simaster_bug.Constant.BASE_URL_IMG;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ikaru19.simaster_bug.R;
import com.ikaru19.simaster_bug.models.Bts;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BtsAdapter extends BaseQuickAdapter<Bts, BaseViewHolder> {

    public BtsAdapter(@Nullable List<Bts> data) {
        super(R.layout.item_artikel,data);
    }

    public void refill(List<Bts> data){
        this.mData = data;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Bts item) {
        helper.setText(R.id.tv_artikel_judul,item.getJudul());
        String tanggal = item.getDateCreated();
        tanggal = tanggal.replaceAll("\\s.*", "");
        String[] parts = tanggal.split("-");
        tanggal = parts[2] +"-" + parts[1] + "-" + parts[0];
        helper.setText(R.id.tv_artikel_penulis,"Tangal: " + tanggal + ",\nOleh: " + item.getPenulis());
        ImageView imageView = helper.getView(R.id.iv_artikel);
        Picasso.get()
                .load(BASE_URL_IMG+item.getThumbnail())
                .resize(1280, 720)
                .onlyScaleDown()
                .placeholder(R.drawable.img_placeholder)
                .into(imageView);
//        helper.setText(R.id.tv_bts_judul,item.getJudul());
//        String tanggal = item.getDateCreated();
//        tanggal = tanggal.replaceAll("\\s.*", "");
//        String[] parts = tanggal.split("-");
//        tanggal = parts[2] +"-" + parts[1] + "-" + parts[0];
//        String detail = "Tanggal: " +  tanggal + ", Oleh: " + item.getPenulis();
//        helper.setText(R.id.tv_bts_detail,detail);
    }
}
