package com.ikaru19.simaster_bug;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.ikaru19.simaster_bug.BTS.BtsVarietasActivity;

public class BtsActivity extends AppCompatActivity {
    CardView cv_varietas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bts);
        cv_varietas = findViewById(R.id.cv_varietas);
        cv_varietas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BtsActivity.this, BtsVarietasActivity.class);
                startActivity(intent);
            }
        });
    }
}