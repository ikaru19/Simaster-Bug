package com.ikaru19.simaster_bug.adapters;

import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ikaru19.simaster_bug.R;
import com.ikaru19.simaster_bug.models.VideoResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.ikaru19.simaster_bug.Constant.BASE_URL;

public class VideoAdapter extends BaseQuickAdapter<VideoResponse, BaseViewHolder> {

    public VideoAdapter(@Nullable List<VideoResponse> data) {
        super(R.layout.item_video,data);
    }

    public void refill(List<VideoResponse> data){
        this.mData = data;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, VideoResponse item) {
        helper.setText(R.id.tv_artikel_judul,item.getJudul());
        String tanggal = item.getDateCreated();
        tanggal = tanggal.replaceAll("\\s.*", "");
        String[] parts = tanggal.split("-");
        tanggal = parts[2] +"-" + parts[1] + "-" + parts[0];
        helper.setText(R.id.tv_artikel_penulis,"Di Unggah Pada: " + tanggal);
        ImageView imageView = helper.getView(R.id.iv_artikel);
        Log.e("SIMASTER BUG",item.getThumbnail());
        String thumbnail = item.getThumbnail();
        if (thumbnail.isEmpty()) {
            Picasso.get()
                    .load(BASE_URL)
                    .resize(1280, 720)
                    .onlyScaleDown()
                    .placeholder(R.drawable.img_placeholder)
                    .into(imageView);
        } else {
            Picasso.get()
                    .load(item.getThumbnail())
                    .resize(1280, 720)
                    .onlyScaleDown()
                    .placeholder(R.drawable.img_placeholder)
                    .into(imageView);
        }
    }
}
