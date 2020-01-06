package com.ikaru19.simaster_bug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ikaru19.simaster_bug.models.Artikel;
import com.squareup.picasso.Picasso;

import static com.ikaru19.simaster_bug.Constant.BASE_URL_IMG;

public class ArtikelDetailActivity extends AppCompatActivity {

    private Artikel artikel;
    private TextView tv_judul_detail , tv_isi_detail;
    private ImageView iv_artikel_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel_detail);
        artikel = getIntent().getParcelableExtra("ArtikelDetail");
        tv_isi_detail = findViewById(R.id.tv_isi_artikel_detail);
        tv_judul_detail = findViewById(R.id.tv_judul_artikel_detail);
        iv_artikel_detail = findViewById(R.id.iv_artikel_detail);

        Picasso.get().load(BASE_URL_IMG+artikel.getImg()).into(iv_artikel_detail);
        tv_judul_detail.setText(artikel.getJudul());
        tv_isi_detail.setText(artikel.getIsi());

    }
}
