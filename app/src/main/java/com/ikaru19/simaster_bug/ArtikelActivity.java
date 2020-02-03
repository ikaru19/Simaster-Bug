package com.ikaru19.simaster_bug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ikaru19.simaster_bug.adapters.ArtikelAdapter;
import com.ikaru19.simaster_bug.apihelper.ApiService;
import com.ikaru19.simaster_bug.generator.ServiceGenerator;
import com.ikaru19.simaster_bug.models.Artikel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtikelActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ApiService apiService;
    private List<Artikel> artikels = new ArrayList<>();
    private ArtikelAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);
        apiService = ServiceGenerator.createService(ApiService.class);
        recyclerView = findViewById(R.id.rv_artikel);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        getData();
        swipeRefreshLayout.setOnRefreshListener(this);

        adapter = new ArtikelAdapter(artikels);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ArtikelActivity.this,ArtikelDetailActivity.class);
                intent.putExtra("ArtikelDetail", artikels.get(position));
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(ArtikelActivity.this));
        recyclerView.setAdapter(adapter);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideRight(this);
    }

    private void getData(){
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Mengambil Data Dari Internet");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        Call<List<Artikel>> artikelCall = apiService.getArtikel();
        artikelCall.enqueue(new Callback<List<Artikel>>() {
            @Override
            public void onResponse(Call<List<Artikel>> call, Response<List<Artikel>> response) {
                artikels = response.body();

                if (artikels.isEmpty() || artikels == null){
                    Toast.makeText(ArtikelActivity.this,"Data Kosong",Toast.LENGTH_SHORT).show();
                }else{
                    adapter.refill(artikels);
                    adapter.notifyDataSetChanged();
                }
                progress.dismiss();

            }

            @Override
            public void onFailure(Call<List<Artikel>> call, Throwable t) {
                Toast.makeText(ArtikelActivity.this, "Mohon Hubungkan Ponsel Anda Ke Internet", Toast.LENGTH_LONG).show();
                progress.dismiss();
                Log.d("SIMASTER_DEBUG",t.getMessage());
            }
        });
    }

    @Override
    public void onRefresh() {
        Toast.makeText(this, "Memuat Ulang...", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
