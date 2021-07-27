package com.ikaru19.simaster_bug;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ikaru19.simaster_bug.adapters.ArtikelAdapter;
import com.ikaru19.simaster_bug.apihelper.ApiService;
import com.ikaru19.simaster_bug.component.LottieLoading;
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
    private View noInternetView;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);
        apiService = ServiceGenerator.createService(ApiService.class);
        recyclerView = findViewById(R.id.rv_artikel);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        noInternetView = findViewById(R.id.artikelNoInternet);
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
        handler.removeCallbacksAndMessages(null);
        super.onBackPressed();
        Animatoo.animateSlideRight(this);
    }

    @Override
    protected void onStop() {
        handler.removeCallbacksAndMessages(null);
        super.onStop();
    }

    private void getData(){
//        final ProgressDialog progress = new ProgressDialog(this);
//        progress.setTitle("Loading");
//        progress.setMessage("Mengambil Data Dari Internet");
//        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
//        progress.show();

        final LottieLoading lottieLoading = new LottieLoading(ArtikelActivity.this);
        lottieLoading.show();
        Call<List<Artikel>> artikelCall = apiService.getArtikel();
        artikelCall.enqueue(new Callback<List<Artikel>>() {
            @Override
            public void onResponse(Call<List<Artikel>> call, Response<List<Artikel>> response) {
                noInternetView.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                artikels = response.body();

                if (artikels == null || artikels.isEmpty() ){
                    Toast.makeText(ArtikelActivity.this,"Data Kosong",Toast.LENGTH_SHORT).show();
                }else{
                    adapter.refill(artikels);
                    adapter.notifyDataSetChanged();
                }
                lottieLoading.dismiss();

            }

            @Override
            public void onFailure(Call<List<Artikel>> call, Throwable t) {
                noInternetView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
                lottieLoading.dismiss();
                Log.d("SIMASTER_DEBUG",t.getMessage());
            }
        });
    }

    @Override
    public void onRefresh() {
        Toast.makeText(this, "Memuat Ulang...", Toast.LENGTH_SHORT).show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               getData();
               swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
