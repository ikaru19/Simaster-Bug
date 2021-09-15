package com.ikaru19.simaster_bug;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ikaru19.simaster_bug.adapters.ArtikelV2Adapter;
import com.ikaru19.simaster_bug.apihelper.ApiService;
import com.ikaru19.simaster_bug.generator.ServiceGenerator;
import com.ikaru19.simaster_bug.models.v2.ArtikelV2;
import com.ikaru19.simaster_bug.v2.ArtikelV2DetailActivity;
import com.ikaru19.simaster_bug.v2.HamaV2Activity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityV2 extends AppCompatActivity {

    CircleImageView menu_tanya2,menu_artikel2,menu_wijen2,menu_tembakau2,menu_tebu2,menu_tentang2,menu_hubungi2,menu_bts2,menu_cuaca2,menu_video2;
    private ApiService apiService;
    private List<ArtikelV2> artikels = new ArrayList<>();
    private ArtikelV2Adapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private View emptyView;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_v2);
        menu_tanya2 = findViewById(R.id.menu_tanya2);
        menu_artikel2 = findViewById(R.id.menu_artikel2);
        menu_wijen2 = findViewById(R.id.menu_wijen2);
        menu_tembakau2 = findViewById(R.id.menu_tembakau2);
        menu_tebu2 = findViewById(R.id.menu_tebu2);
        menu_tentang2 = findViewById(R.id.menu_tentang2);
        menu_hubungi2  = findViewById(R.id.menu_hubungi2);
        menu_bts2 = findViewById(R.id.menu_bts2);
        menu_cuaca2 = findViewById(R.id.menu_cuaca2);
        menu_video2 = findViewById(R.id.menu_video2);
        apiService = ServiceGenerator.createService(ApiService.class);
        recyclerView = findViewById(R.id.rv_artikel_menu);
        progressBar = findViewById(R.id.progressBar);
        emptyView = findViewById(R.id.no_item_lottie);
        emptyView.setVisibility(View.INVISIBLE);
//        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        addOnClick();
        getData();
//        swipeRefreshLayout.setOnRefreshListener(this);

        adapter = new ArtikelV2Adapter(artikels);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MainActivityV2.this, ArtikelV2DetailActivity.class);
                intent.putExtra("ArtikelDetail", (Parcelable) artikels.get(position));
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivityV2.this));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public void addOnClick() {
        menu_tanya2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityV2.this,ContactListActivity.class);
                startActivity(intent);
            }
        });
        menu_artikel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityV2.this,ArtikelActivity.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivityV2.this);
            }
        });
        menu_wijen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityV2.this, HamaV2Activity.class);
                intent.putExtra("JenisHama","wijen");
                intent.putExtra("judul","Hama Wijen");
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivityV2.this);

            }
        });

        menu_tembakau2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityV2.this,HamaV2Activity.class);
                intent.putExtra("JenisHama","tembakau");
                intent.putExtra("judul","Hama Tembakau");
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivityV2.this);

            }
        });

        menu_tebu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityV2.this,HamaV2Activity.class);
                intent.putExtra("JenisHama","tebu");
                intent.putExtra("judul","Hama Tebu");
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivityV2.this);
            }
        });

        menu_tentang2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityV2.this,TentangActivity.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivityV2.this);
            }
        });

        menu_hubungi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityV2.this,HubungiActivity.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivityV2.this);
            }
        });

        menu_bts2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityV2.this,BtsActivity.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivityV2.this);
            }
        });

        menu_video2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityV2.this,VideoActivity.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivityV2.this);
            }
        });

        menu_cuaca2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent myIntent = new Intent(MainActivityV2.this,Class.forName("cz.martykan.forecastie.activities.MainActivity"));
                    startActivity(myIntent );
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        handler.removeCallbacksAndMessages(null);
        super.onStop();
    }

    private void getData(){
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        Call<List<ArtikelV2>> artikelCall = apiService.getArtikel();
        artikelCall.enqueue(new Callback<List<ArtikelV2>>() {
            @Override
            public void onResponse(Call<List<ArtikelV2>> call, Response<List<ArtikelV2>> response) {
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                artikels = response.body();

                if (artikels == null || artikels.isEmpty() ){
                    Toast.makeText(MainActivityV2.this,"Data Kosong",Toast.LENGTH_SHORT).show();
                    emptyView.setVisibility(View.VISIBLE);
                }else{
                    adapter.refill(artikels);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<ArtikelV2>> call, Throwable t) {
                recyclerView.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                emptyView.setVisibility(View.VISIBLE);
                Log.d("SIMASTER_DEBUG",t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Keluar Simaster")
                .setMessage("Apakah anda yakin akan meninggalkan aplikasi Simaster?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Tidak", null)
                .show();
    }
}