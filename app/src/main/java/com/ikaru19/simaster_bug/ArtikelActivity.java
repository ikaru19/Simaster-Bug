package com.ikaru19.simaster_bug;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ikaru19.simaster_bug.adapters.ArtikelV2Adapter;
import com.ikaru19.simaster_bug.apihelper.ApiService;
import com.ikaru19.simaster_bug.component.LottieLoading;
import com.ikaru19.simaster_bug.generator.ServiceGenerator;
import com.ikaru19.simaster_bug.models.v2.ArtikelV2;
import com.ikaru19.simaster_bug.v2.ArtikelV2DetailActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtikelActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ApiService apiService;
    private List<ArtikelV2> rawArtikels = new ArrayList<>();
    private List<ArtikelV2> artikels = new ArrayList<>();
    private ArtikelV2Adapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View noInternetView;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar;
        actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_artikel);
        apiService = ServiceGenerator.createService(ApiService.class);
        recyclerView = findViewById(R.id.rv_artikel);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        noInternetView = findViewById(R.id.artikelNoInternet);
        getData();
        swipeRefreshLayout.setOnRefreshListener(this);

        adapter = new ArtikelV2Adapter(artikels);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ArtikelActivity.this, ArtikelV2DetailActivity.class);
                intent.putExtra("ArtikelDetail", (Parcelable) artikels.get(position));
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
        Call<List<ArtikelV2>> artikelCall = apiService.getArtikel();
        artikelCall.enqueue(new Callback<List<ArtikelV2>>() {
            @Override
            public void onResponse(Call<List<ArtikelV2>> call, Response<List<ArtikelV2>> response) {
                noInternetView.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                rawArtikels = response.body();

                if (rawArtikels == null || rawArtikels.isEmpty() ){
                    Toast.makeText(ArtikelActivity.this,"Data Kosong",Toast.LENGTH_SHORT).show();
                }else{
                    for (ArtikelV2 artikel : rawArtikels) {
                        Log.e("SIMASTER_DEBUG",artikel.getId());
                        if ((!artikel.getId().equalsIgnoreCase("-1") && (!artikel.getId().equalsIgnoreCase("-2")))) {
                            artikels.add(artikel);
                        }
                    }
                    adapter.refill(artikels);
                    adapter.notifyDataSetChanged();
                }
                lottieLoading.dismiss();

            }

            @Override
            public void onFailure(Call<List<ArtikelV2>> call, Throwable t) {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
