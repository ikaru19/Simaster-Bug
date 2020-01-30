package com.ikaru19.simaster_bug;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ikaru19.simaster_bug.models.Hama;
import com.squareup.picasso.Picasso;

import static com.ikaru19.simaster_bug.Constant.BASE_URL_IMG;

public class HamaDetailActivity extends AppCompatActivity {

    private Hama hama;
    private TextView tv_judul_detail , tv_isi_detail,tv_penyelesaian_hama ,tv_rekom_pesti , tv_gejala;
    private ImageView iv_hama_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hama_detail);
        hama = getIntent().getParcelableExtra("HamaDetail");
        tv_isi_detail = findViewById(R.id.tv_isi_hama_detail);
        tv_judul_detail = findViewById(R.id.tv_judul_hama_detail);
        iv_hama_detail = findViewById(R.id.iv_hama_detail);
        tv_penyelesaian_hama = findViewById(R.id.tv_isi_pengenalan_simaster);
        tv_rekom_pesti = findViewById(R.id.tv_isi_fitur);
        tv_gejala = findViewById(R.id.tv_isi_gejala);

        Picasso.get().load(BASE_URL_IMG+hama.getImg()).resize(1280, 720)
                .onlyScaleDown().into(iv_hama_detail);
        tv_judul_detail.setText(hama.getNamaHama());
        tv_isi_detail.setText(hama.getDeskipsi());
        tv_penyelesaian_hama.setText(hama.getPenyelesaianHama());
        tv_rekom_pesti.setText(hama.getRekomPesti());
        tv_gejala.setText(hama.getGejala());

    }
}
