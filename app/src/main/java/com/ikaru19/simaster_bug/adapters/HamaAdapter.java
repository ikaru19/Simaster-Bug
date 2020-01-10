package com.ikaru19.simaster_bug.adapters;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ikaru19.simaster_bug.R;
import com.ikaru19.simaster_bug.models.Artikel;
import com.ikaru19.simaster_bug.models.Hama;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.ikaru19.simaster_bug.Constant.BASE_URL_IMG;

public class HamaAdapter extends BaseQuickAdapter<Hama, BaseViewHolder> {

    public HamaAdapter(@Nullable List<Hama> data) {
        super(R.layout.item_hama,data);
    }
    public void refill(List<Hama> data){
        this.mData = data;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Hama item) {
        helper.setText(R.id.tv_hama_judul,item.getNamaHama())
                .setText(R.id.tv_hama_preview,"Baca Disini");
        ImageView imageView = helper.getView(R.id.iv_hama);
        Picasso.get().load(BASE_URL_IMG+item.getImg()).resize(1280, 720)
                .onlyScaleDown().into(imageView);
    }
}
