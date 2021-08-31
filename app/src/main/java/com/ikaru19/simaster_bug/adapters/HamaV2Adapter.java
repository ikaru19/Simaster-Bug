package com.ikaru19.simaster_bug.adapters;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ikaru19.simaster_bug.R;
import com.ikaru19.simaster_bug.models.v2.HamaV2;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.ikaru19.simaster_bug.Constant.BASE_URL_IMG;

public class HamaV2Adapter extends BaseQuickAdapter<HamaV2, BaseViewHolder> {

    public HamaV2Adapter(@Nullable List<HamaV2> data) {
        super(R.layout.item_hama,data);
    }
    public void refill(List<HamaV2> data){
        this.mData = data;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HamaV2 item) {
        helper.setText(R.id.tv_hama_judul,item.getJudul());
        ImageView imageView = helper.getView(R.id.iv_hama);
        Picasso.get()
                .load(BASE_URL_IMG+item.getThumbnail())
                .resize(1280, 720)
                .onlyScaleDown()
                .placeholder(R.drawable.img_placeholder)
                .into(imageView);
    }
}
