package com.ikaru19.simaster_bug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class MainActivity extends AppCompatActivity {

    CardView menu_tanya,menu_artikel,menu_wijen,menu_tembakau,menu_tebu,menu_tentang,menu_hubung;
    CardView menu_bts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu_tanya = findViewById(R.id.menu_tanya1);
        menu_artikel = findViewById(R.id.menu_artikel);
        menu_wijen = findViewById(R.id.menu_wijen);
        menu_tembakau = findViewById(R.id.menu_tembakau);
        menu_tebu = findViewById(R.id.menu_tebu);
        menu_tentang = findViewById(R.id.menu_tentang);
        menu_hubung  = findViewById(R.id.menu_tanya);
        menu_bts = findViewById(R.id.menu_bts);

        menu_tanya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ContactListActivity.class);
                startActivity(intent);
            }
        });
        menu_artikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ArtikelActivity.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivity.this);
            }
        });
        menu_wijen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HamaActivity.class);
                intent.putExtra("JenisHama","wijen");
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivity.this);

            }
        });

        menu_tembakau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HamaActivity.class);
                intent.putExtra("JenisHama","tembakau");
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivity.this);

            }
        });

        menu_tebu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HamaActivity.class);
                intent.putExtra("JenisHama","tebu");
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivity.this);
            }
        });

        menu_tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TentangActivity.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivity.this);
            }
        });

        menu_hubung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HubungiActivity.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivity.this);
            }
        });

        menu_bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BtsActivity.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivity.this);
            }
        });
    }
}
