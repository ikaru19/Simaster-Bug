package com.ikaru19.simaster_bug.adapters;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ikaru19.simaster_bug.R;
import com.ikaru19.simaster_bug.models.v2.ArtikelV2;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.ikaru19.simaster_bug.Constant.BASE_URL_IMG;

public class ArtikelV2Adapter extends BaseQuickAdapter<ArtikelV2, BaseViewHolder> {

    public ArtikelV2Adapter(@Nullable List<ArtikelV2> data) {
        super(R.layout.item_artikel,data);
    }

    public void refill(List<ArtikelV2> data){
        this.mData = data;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ArtikelV2 item) {
        helper.setText(R.id.tv_artikel_judul,item.getJudul());
        helper.setText(R.id.tv_artikel_penulis,"Oleh: " + item.getPenulis());
        ImageView imageView = helper.getView(R.id.iv_artikel);
        Picasso.get()
                .load(BASE_URL_IMG+item.getThumbnail())
                .resize(1280, 720)
                .onlyScaleDown()
                .placeholder(R.drawable.img_placeholder)
                .into(imageView);

    }
}