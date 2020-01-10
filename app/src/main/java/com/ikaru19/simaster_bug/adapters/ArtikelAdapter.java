package com.ikaru19.simaster_bug.adapters;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ikaru19.simaster_bug.R;
import com.ikaru19.simaster_bug.models.Artikel;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.ikaru19.simaster_bug.Constant.BASE_URL_IMG;

public class ArtikelAdapter extends BaseQuickAdapter<Artikel, BaseViewHolder> {

    public ArtikelAdapter(@Nullable List<Artikel> data) {
        super(R.layout.item_artikel,data);
    }

    public void refill(List<Artikel> data){
        this.mData = data;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Artikel item) {
        helper.setText(R.id.tv_artikel_judul,item.getJudul());
        ImageView imageView = helper.getView(R.id.iv_artikel);
        Picasso.get().load(BASE_URL_IMG+item.getImg()).resize(1280, 720)
                .onlyScaleDown().into(imageView);

    }
}
