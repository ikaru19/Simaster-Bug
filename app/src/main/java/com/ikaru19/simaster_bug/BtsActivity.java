package com.ikaru19.simaster_bug;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.ikaru19.simaster_bug.BTS.BtsVarietasActivity;

public class BtsActivity extends AppCompatActivity {
    CardView cv_varietas,cv_stok,cv_pht,cv_budi_daya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bts);
        cv_varietas = findViewById(R.id.cv_varietas);
        cv_stok = findViewById(R.id.cv_stok);
        cv_pht = findViewById(R.id.cv_pht);
        cv_budi_daya = findViewById(R.id.cv_budi_daya);
        cv_varietas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BtsActivity.this, BtsVarietasActivity.class);
                intent.putExtra("TipeBTS","varietas");
                startActivity(intent);
            }
        });
        cv_stok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BtsActivity.this, BtsVarietasActivity.class);
                intent.putExtra("TipeBTS","stok");
                startActivity(intent);
            }
        });
        cv_pht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BtsActivity.this, BtsVarietasActivity.class);
                intent.putExtra("TipeBTS","pht");
                startActivity(intent);
            }
        });
        cv_budi_daya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BtsActivity.this, BtsVarietasActivity.class);
                intent.putExtra("TipeBTS","budiDaya");
                startActivity(intent);
            }
        });
    }
}